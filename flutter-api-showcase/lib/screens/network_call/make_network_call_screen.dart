
import 'dart:io';
import 'dart:math';

import 'package:flutter/material.dart';
import 'package:instana_agent/instana_agent.dart';

import '../../utilities/Utils.dart';
import '../../views/common_widgets/ibm_instana_app_bar.dart';
import '../../views/common_widgets/instana_button.dart';

class NetworkCallScreen extends StatelessWidget {
  const NetworkCallScreen({super.key});

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
              CustomInstanaButton(
                text: 'Call Random API ',
                onPressed: () {
                  InstanaAgent.redactHTTPQuery(regex: [
                    'number',
                  ]);
                  var random = Random(1000);
                  int randomNumber = random.nextInt(100) + 1;
                  var url = "https://dog-api.kinduff.com/api/facts?number=$randomNumber";
                  Utilities.fetchRequest(url, "GET");
                  if(Platform.isAndroid){
                    Utilities.showToastMessage("$url");
                  }else{
                    Utilities.showToastMessage("GET API CALL MADE TO \n\n $url");
                  }
                },
              ),
              CustomInstanaButton(
                text: 'Custom Network Call',
                onPressed: () {
                  Utilities.showTextFieldNetworkDialog(
                      context,
                          (enteredText){
                            InstanaAgent.redactHTTPQuery(regex: [
                              enteredText[1]
                            ]);
                            var url = enteredText[0];
                            Utilities.fetchRequest(url, "GET");
                            if(Platform.isAndroid){
                              Utilities.showToastMessage("$url");
                            }else{
                              Utilities.showToastMessage("GET API CALL MADE TO \n\n $url");
                            }
                      }
                  );
                },
              ),
            ],
          ),
        ),
      ),
    );
  }
}
