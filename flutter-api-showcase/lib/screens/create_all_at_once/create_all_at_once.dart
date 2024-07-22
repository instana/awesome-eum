import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:ibm_instana_flutter_agent_demo/views/common_widgets/instana_button.dart';
import 'package:instana_agent/instana_agent.dart';

import '../../utilities/Utils.dart';
import '../../views/common_widgets/ibm_instana_app_bar.dart';

class CreateAllAtOnce extends StatefulWidget {
  const CreateAllAtOnce({super.key});

  @override
  State<CreateAllAtOnce> createState() => _CreateAllAtOnceState();
}

class _CreateAllAtOnceState extends State<CreateAllAtOnce> {
  bool progressIndicatorVisible = false;
  bool isStartButtonEnabled = true;
  String resultText = "";
  String titleText = "";

  /**
   * Event related test values
   */
  final String eventName = "TEST_EVENT_NAME";
  final String viewName = "TEST_VIEW_NAME";
  final String metaKey = "META_KEY_TEST";
  final String metaValue = "META_VALUE_TEST";

  /**
   * User related test values
   */
  final String testUserName = "Test User Name";
  final String testUserid = "TEST007123";
  final String testUserEmail = "test@test.com";

  /**
   * Time related variables
   */
  String eventTime = "";
  String userCreationTime = "";
  String networkCallTime = "";
  String setViewTime = "";

  int delay = 4;

  /**
   * For all 4 functions below a Delay is added, Its for the ease of user to view whats happening in the background.
   */
  Future<void> _updateProfileData() async {
    setState(() {
      isStartButtonEnabled = false;
      titleText = "Updating User Details";
      resultText =
          "\n\nUSER NAME : $testUserName \n\nUSER ID : $testUserid \n\nUSER EMAIL : $testUserEmail";
    });
    await InstanaAgent.setUserName(testUserName);
    await InstanaAgent.setUserID(testUserid);
    await InstanaAgent.setUserEmail(testUserEmail);
    userCreationTime = Utilities.getDateTime();
    await Future.delayed(Duration(seconds: delay));
  }

  Future<void> _networkCall() async {
    var random = Random(1000);
    int randomNumber = random.nextInt(100) + 1;
    var url = "https://dog-api.kinduff.com/api/facts?number=$randomNumber";
    setState(() {
      titleText = "Making Network Call";
      resultText = "\n\nURL: $url \n\nredactHTTPQuery: number";
    });
    await InstanaAgent.redactHTTPQuery(regex: ['number']);
    await Utilities.fetchRequest(url, "GET");
    networkCallTime = Utilities.getDateTime();
    await Future.delayed(Duration(seconds: delay));
  }

  Future<void> _setViewName() async {
    setState(() {
      titleText = "Setting View Name";
      resultText = "Setting View Name as TEST_VIEW_NAME";
    });
    await InstanaAgent.setView("TEST_VIEW_NAME");
    setViewTime = Utilities.getDateTime();
    await Future.delayed(Duration(seconds: delay));
  }

  Future<void> _setCustomEvent() async {
    setState(() {
      titleText = "Setting Custom Event";
      resultText =
          "Event name: $eventName\nView name: $viewName\nMetaKey: $metaKey\nMetaValue: $metaValue";
    });
    await InstanaAgent.reportEvent(
      name: eventName,
      options: EventOptions()
        ..viewName = viewName
        ..startTime = DateTime.now().millisecondsSinceEpoch
        ..duration = 3 * 1000
        ..meta = {
          metaKey: metaValue,
        },
    );
    eventTime = Utilities.getDateTime();
    await Future.delayed(Duration(seconds: delay));
  }

  /**
   * The Expected result section flags update
   */
  void _expectedResult() {
    setState(() {
      progressIndicatorVisible = false;
      isStartButtonEnabled = true;
      titleText = "Expected Results In Log";
      resultText = '''..''';
    });
  }

  // Function to execute all tasks in sequence and display results.
  Future<void> _executeAllTasks() async {
    await _updateProfileData();
    await _networkCall();
    await _setViewName();
    await _setCustomEvent();
    _expectedResult();
  }

  // Function to show the progress dialog and execute tasks.
  void _executeTasksAndShowProgressDialog() {
    WidgetsBinding.instance.addPostFrameCallback((_) {
      _showProgressDialog();
    });
  }

  void _showProgressDialog() {
    _executeAllTasks();
  }

  Widget _getRichTextResult() {
    if (userCreationTime == "") {
      return Container();
    }
    return RichText(
      textAlign: TextAlign.start,
      text: TextSpan(
        children: [
          Utilities.buildTitle(
            title: 'User Details Expected: @ $userCreationTime\n\n',
          ),
          Utilities.buildBulletPoint('USER NAME', testUserName),
          Utilities.buildBulletPoint('  USER ID', testUserid),
          Utilities.buildBulletPoint('  USER EMAIL', testUserEmail),
          Utilities.buildTitle(
            title: '\n\nNetwork Log Should show @ $networkCallTime:\n\n',
          ),
          Utilities.buildBulletPoint('URL',
              'https://dog-api.kinduff.com/api/facts?number= \n  (here the value for \'number\' should be encrypted)'),
          Utilities.buildTitle(
            title: '\n\nView Name @ $setViewTime:\n\n',
          ),
          Utilities.buildBulletPoint(
              'The view name should be shown as', viewName),
          Utilities.buildTitle(
            title: '\n\nEvent Details @ $eventTime:\n\n',
          ),
          Utilities.buildBulletPoint('Event name', eventName),
          Utilities.buildBulletPoint('  View name', viewName),
          Utilities.buildBulletPoint('  MetaKey', metaKey),
          Utilities.buildBulletPoint('  MetaValue', metaValue),
        ],
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: IBMInstanaAppBar(),
      body: Center(
        child: SingleChildScrollView(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: <Widget>[
              Padding(
                padding: const EdgeInsets.all(15.0),
                child: Text(
                  titleText,
                  textAlign: TextAlign.center,
                  style: TextStyle(
                    fontSize: 19,
                    color: Colors.blue[900],
                    fontWeight: FontWeight.bold,
                  ),
                ),
              ),
              Container(
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: [
                    progressIndicatorVisible
                        ? Padding(
                            padding: const EdgeInsets.all(8.0),
                            child: CircularProgressIndicator(
                              strokeWidth: 6,
                            ),
                          )
                        : Container(),
                    SizedBox(width: 10),
                    Expanded(
                      child: progressIndicatorVisible
                          ? Padding(
                              padding: const EdgeInsets.all(8.0),
                              child: Text(
                                resultText,
                                softWrap: true,
                              ),
                            )
                          : _getRichTextResult(),
                    ),
                  ],
                ),
              ),
              CustomInstanaButton(
                text: "START",
                onPressed: () {
                  setState(() {
                    progressIndicatorVisible = true;
                  });
                  _executeTasksAndShowProgressDialog();
                },
                isEditable: isStartButtonEnabled,
              ),
            ],
          ),
        ),
      ),
    );
  }
}
