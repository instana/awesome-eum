import 'package:flutter/material.dart';
import 'package:ibm_instana_flutter_agent_demo/screens/multi_view/view_two_test_screen.dart';
import 'package:instana_agent/instana_agent.dart';

import '../../constants/constants.dart';
import '../../utilities/preferences.dart';
import '../../views/common_widgets/custom_instana_text_field.dart';
import '../../views/common_widgets/ibm_instana_app_bar.dart';
import '../../views/common_widgets/instana_button.dart';

class ViewOneTestScreen extends StatefulWidget {
  @override
  State<ViewOneTestScreen> createState() => _State();
}

class _State extends State<ViewOneTestScreen> {
  var screenName = "";

  Future<void> setupInstanaView() async {
    String? value = await Preferences.getPrefString(Constant.VIEW_ONE);
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
                  hintText: "Enter Screen 1 Name",
                  controller: screenNameController),
              SizedBox(
                height: 30,
              ),
              CustomInstanaButton(
                text: 'Save Name for View',
                onPressed: () async {
                  await Preferences.savePrefString(Constant.VIEW_ONE, screenNameController.text.toString());
                  setState(()  {
                    screenName = screenNameController.text.toString();
                    InstanaAgent.setView(screenName);
                  });
                },
              ),
              CustomInstanaButton(
                text: 'Next Screen',
                onPressed: () {
                  Navigator.of(context).push(
                    MaterialPageRoute(
                      builder: (context) => ViewTwoTestScreen(),
                    ),
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
