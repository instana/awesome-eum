import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:http/http.dart' as http;
import 'package:ibm_instana_flutter_agent_demo/utilities/preferences.dart';
import 'package:instana_agent/instana_agent.dart';

import '../constants/constants.dart';
import 'http_client.dart';

class Utilities {
  static void showToastMessage(String message) {
    Fluttertoast.showToast(
      msg: message,
      toastLength: Toast.LENGTH_LONG,
      gravity: ToastGravity.BOTTOM,
      backgroundColor: Colors.black,
      textColor: Colors.white,
      fontSize: 14.0,
    );
  }

  static Future<void> showTextFieldDialog(BuildContext context, String title,
      String hint, String buttonText, Function(String) callback) async {
    var _textEditingController = TextEditingController();
    return showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text(title),
          content: TextField(
            controller: _textEditingController,
            decoration: InputDecoration(hintText: hint),
          ),
          actions: [
            TextButton(
              onPressed: () {
                Navigator.of(context).pop();
              },
              child: Text('Cancel'),
            ),
            ElevatedButton(
              onPressed: () {
                String enteredText = _textEditingController.text;
                Navigator.of(context).pop();
                callback(enteredText);
              },
              child: Text(buttonText),
            ),
          ],
        );
      },
    );
  }

  static Future<void> updateUserDataIfAvailable() async {
    var userName = await Preferences.getPrefString(Constant.USER_NAME);
    var userId = await Preferences.getPrefString(Constant.USER_ID);
    var email = await Preferences.getPrefString(Constant.USER_EMAIL);
    if (userName != null) {
      InstanaAgent.setUserName(userName);
    }
    if (userId != null) {
      InstanaAgent.setUserID(userId);
    }
    if (email != null) {
      InstanaAgent.setUserEmail(email);
    }
  }

  static Future<String> fetchRequest(
      String requestUrl, String requestType) async {
    final InstrumentedHttpClient httpClient =
        InstrumentedHttpClient(http.Client());
    var url = requestUrl;
    final http.Request request = http.Request(requestType, Uri.parse(url));

    final response = await httpClient.send(request);
    if (response.statusCode == 200) {
      var responseBody = await response.stream.bytesToString();
      return (responseBody);
    } else {
      throw Exception('Failed to load album');
    }
  }

  static Future<void> showTextFieldNetworkDialog(
      BuildContext context, Function(List<String>) callback) async {
    var _textEditingUrlController = TextEditingController();
    var _textEditingParamsController = TextEditingController();
    return showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text("Make Your Own API Call"),
          content: Column(children: [
            TextField(
              controller: _textEditingUrlController,
              decoration: InputDecoration(hintText: "Type URL here"),
            ),
            Padding(
                padding: EdgeInsets.fromLTRB(10,30,10,10),
                child: Text(
                    "Type redactHTTPQuery params from above url - comma separated, this will hide the querry params from tracking")),
            TextField(
              controller: _textEditingParamsController,
              decoration: InputDecoration(hintText: "Type params to hide"),
            ),
          ]),
          actions: [
            TextButton(
              onPressed: () {
                Navigator.of(context).pop();
              },
              child: Text('Cancel'),
            ),
            ElevatedButton(
              onPressed: () {
                String url = _textEditingUrlController.text;
                String params = _textEditingParamsController.text;
                Navigator.of(context).pop();
                callback([url, params]);
              },
              child: Text("Make Call"),
            ),
          ],
        );
      },
    );
  }

  static String getDateTime(){
    DateTime now = DateTime.now();
    String formattedDateTime = "${now.year}-${_twoDigits(now.month)}-${_twoDigits(now.day)} ${_twoDigits(now.hour)}:${_twoDigits(now.minute)}:${_twoDigits(now.second)}";
    return formattedDateTime;
  }

  static String _twoDigits(int n) {
    if (n >= 10) return "$n";
    return "0$n";
  }

  static TextSpan buildBulletPoint(String title, String content) {
    return TextSpan(
      children: [
        TextSpan(
          text: '',
          style: TextStyle(color: Colors.blue), // Change the color here as per your requirement
        ),
        TextSpan(
          text: '$title : ',
          style: TextStyle(color: Colors.blue),
        ),
        TextSpan(
          text: content+'\n',
          style: TextStyle(fontWeight: FontWeight.bold,color: Colors.blue),
        ),
      ],
    );
  }

  static TextSpan buildTitle({required String title}) {
    return TextSpan(
      children: [
        TextSpan(
          text: '$title  ',
          style: TextStyle(fontWeight: FontWeight.bold,color: Colors.blue[900]),
        ),
      ],
    );
  }

}
