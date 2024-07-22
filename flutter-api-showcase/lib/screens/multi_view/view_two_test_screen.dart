import 'package:flutter/material.dart';
import 'package:instana_agent/instana_agent.dart';

import '../../constants/constants.dart';
import '../../utilities/preferences.dart';
import '../../views/common_widgets/custom_instana_text_field.dart';
import '../../views/common_widgets/ibm_instana_app_bar.dart';
import '../../views/common_widgets/instana_button.dart';

class ViewTwoTestScreen extends StatefulWidget {
  const ViewTwoTestScreen({super.key});

  @override
  State<ViewTwoTestScreen> createState() => _ViewTwoTestScreenState();
}

class _ViewTwoTestScreenState extends State<ViewTwoTestScreen> {
  var screenName = "";

  Future<void> setupInstanaView() async {
    String? value = await Preferences.getPrefString(Constant.VIEW_TWO);
    if (value != null) {
      setState(() {
        screenName = value;
      });
      InstanaAgent.setView(value);
    }
  }

  @override
  void initState() {
    super.initState();
    setupInstanaView();
  }

  @override
  Widget build(BuildContext context) {
    var screenNameController = TextEditingController(text: screenName);
    return Scaffold(
      appBar: IBMInstanaAppBar(),
      body: Center(
        child: SingleChildScrollView(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: <Widget>[
              Padding(
                  padding: EdgeInsets.all(40),
                  child: Text(
                    screenName,
                    style: TextStyle(fontSize: 18,),
                    textAlign: TextAlign.center,
                  )),
              CustomInstanaTextField(
                  hintText: "Enter Screen 2 Name",
                  controller: screenNameController),
              SizedBox(
                height: 30,
              ),
              CustomInstanaButton(
                text: 'Save Name for View',
                onPressed: () {
                  setState(() {
                    Preferences.savePrefString(Constant.VIEW_TWO, screenNameController.text.toString());
                    screenName = screenNameController.text.toString();
                    InstanaAgent.setView(screenName);
                  });
                },
              ),
            ],
          ),
        ),
      ),
    );
  }
}
