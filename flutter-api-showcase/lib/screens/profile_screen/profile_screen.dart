import 'package:flutter/material.dart';
import 'package:ibm_instana_flutter_agent_demo/constants/constants.dart';
import 'package:ibm_instana_flutter_agent_demo/utilities/Utils.dart';
import 'package:ibm_instana_flutter_agent_demo/utilities/preferences.dart';
import 'package:ibm_instana_flutter_agent_demo/views/common_widgets/custom_instana_text_field.dart';
import 'package:instana_agent/instana_agent.dart';

import '../../views/common_widgets/ibm_instana_app_bar.dart';
import '../../views/common_widgets/instana_button.dart';

class ProfileScreen extends StatelessWidget {
  ProfileScreen({super.key});

  var userNameController = TextEditingController();
  var userIdController = TextEditingController();
  var emailController = TextEditingController();


  Future<void> loadUserData() async {
    String? userName = await Preferences.getPrefString(Constant.USER_NAME);
    String? userId = await Preferences.getPrefString(Constant.USER_ID);
    String? userEmail = await Preferences.getPrefString(Constant.USER_EMAIL);

    userNameController.text = userName ?? '';
    userIdController.text = userId ?? '';
    emailController.text = userEmail ?? '';

  }
  @override
  Widget build(BuildContext context) {

    loadUserData();

    return Scaffold(
      appBar: IBMInstanaAppBar(),
      body: Center(
        child: SingleChildScrollView(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: <Widget>[
              CustomInstanaTextField(hintText: "User Name", controller: userNameController),
              CustomInstanaTextField(hintText: "USer Id", controller: userIdController),
              CustomInstanaTextField(hintText: "Email Id", controller: emailController),
              SizedBox(height: 30,),
              CustomInstanaButton(text: 'Save User Data',onPressed: () async {
                var userName = userNameController.text.toString();
                var userId = userIdController.text.toString();
                var email = emailController.text.toString();
                await Preferences.savePrefString(Constant.USER_NAME, userName);
                await Preferences.savePrefString(Constant.USER_ID, userId);
                await Preferences.savePrefString(Constant.USER_EMAIL, email);
                await InstanaAgent.setUserID(userId);
                await InstanaAgent.setUserName(userName);
                await InstanaAgent.setUserEmail(email);
                Utilities.showToastMessage("User Details Saved, you may view the details in dashboard within few seconds");
              },),
            ],
          ),
        ),
      ),
    );
  }
}
