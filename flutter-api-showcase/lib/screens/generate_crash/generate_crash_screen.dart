import 'package:flutter/material.dart';
import 'package:ibm_instana_flutter_agent_demo/utilities/Utils.dart';
import 'package:instana_agent/instana_agent.dart';

import '../../views/common_widgets/ibm_instana_app_bar.dart';
import '../../views/common_widgets/instana_button.dart';

class GenerateCrashScreen extends StatelessWidget {
  const GenerateCrashScreen({super.key});

  Future<void> initView() async {
    await InstanaAgent.setView('Generate crash screen');
  }



  @override
  Widget build(BuildContext context) {
    initView();
    return Scaffold(
      appBar: IBMInstanaAppBar(),
      body: Center(
        child: SingleChildScrollView(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: <Widget>[
              CustomInstanaButton(
                text: 'Divide by Zero Exception',
                onPressed: () {
                  int numerator = 10;
                  int denominator = 0;
                  int result = numerator ~/ denominator;
                  print(result);
                },
              ),
              CustomInstanaButton(
                text: 'Custom Runtime Exception',
                onPressed: () {
                  Utilities.showTextFieldDialog(
                    context,
                    "Custom Runtime Exception Message",
                    "Enter Custom Crash Message",
                    "Create Crash",
                      (enteredText){
                        Utilities.showToastMessage("Exception $enteredText will be logged");
                        throw Exception(enteredText);
                      }
                  );
                },
              ),
              CustomInstanaButton(
                text: 'Null Pointer Exception',
                onPressed: () {
                  try{
                    String? x = null;
                    x!.length;
                  }catch (exception){
                    throw Exception("Exception occured @ $exception");
                  }
                  String? x = null;
                  x!.length;
                },
              ),
              CustomInstanaButton(
                text: 'Generate ANR',
                onPressed: () async {
                  //TODO: This section needs to get updated once the crash logging feature is enabled from Agent
                },
              ),
            ],
          ),
        ),
      ),
    );
  }
}
