import 'dart:developer';

import 'package:flutter/material.dart';
import 'package:ibm_instana_flutter_agent_demo/utilities/Utils.dart';
import 'package:ibm_instana_flutter_agent_demo/utilities/preferences.dart';
import 'package:instana_agent/instana_agent.dart';

import '../../constants/constants.dart';
import '../../views/common_widgets/custom_instana_text_field.dart';
import '../../views/common_widgets/instana_button.dart';
import '../home_screen/home_screen.dart';

class CredentialsScreen extends StatefulWidget {
  const CredentialsScreen({super.key});

  @override
  State<CredentialsScreen> createState() => _CredentialsScreenState();
}

class _CredentialsScreenState extends State<CredentialsScreen> {
  var keyController = TextEditingController();
  var urlController = TextEditingController();

  @override
  void initState() {
    super.initState();
    keyController.text = "";
    urlController.text = "";
    setupInstana();
  }

  Future<void> setupInstana() async {
    var options = SetupOptions();
    Future.wait([
      Preferences.getPrefString(Constant.INSTANA_KEY),
      Preferences.getPrefString(Constant.INSTANA_REPORTING_URL),
      Preferences.getPrefBool(Constant.COLLECTION_ENABLED)
    ]).then(
      (results) async => {
        if (results[0] != null)
          {
            await Preferences.getPrefBool(Constant.COLLECTION_ENABLED).then((value) async => {
              if(value == null){
                options.collectionEnabled = true,
                await Preferences.savePrefBool(Constant.COLLECTION_ENABLED, true),
              }else{
                options.collectionEnabled = value
              }
            }),
            options.captureNativeHttp = true,
            await InstanaAgent.setup(
                    key: results[0].toString(),
                    reportingUrl: results[1].toString(),
                    options: options)
                .catchError((e) =>
                    log("Captured PlatformException during Instana setup: $e")),
            await InstanaAgent.setCollectionEnabled(options.collectionEnabled),
            await Utilities.updateUserDataIfAvailable(),
            _navigateToHome()
          },
      },
    );
  }

  void _navigateToHome() {
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => HomeScreen(),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Container(
                  width: 100,
                  height: 70,
                  child: Image.asset(
                    "assets/images/eye_bee_m.png",
                    color: base_blue,
                  ),
                ),
                Container(
                  width: 100,
                  height: 70,
                  child: Image.asset(
                    "assets/images/instana_logo.webp",
                    color: base_blue,
                  ),
                )
              ],
            ),
            SizedBox(
              height: 50,
            ),
            CustomInstanaTextField(
              controller: keyController,
              hintText: "Instana Key",
            ),
            CustomInstanaTextField(
              controller: urlController,
              hintText: "Instana Reporting Url",
            ),
            SizedBox(
              height: 20,
            ),
            CustomInstanaButton(
              text: "Save & Reload",
              onPressed: () async {
                await Preferences.savePrefString(Constant.INSTANA_REPORTING_URL,
                    urlController.text.toString());
                await Preferences.savePrefString(
                    Constant.INSTANA_KEY, keyController.text.toString());
                setupInstana();
              },
            )
          ],
        ),
      ),
    );
  }
}
