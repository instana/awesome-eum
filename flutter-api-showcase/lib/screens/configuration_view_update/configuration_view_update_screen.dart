import 'package:flutter/material.dart';

import '../../constants/constants.dart';
import '../../utilities/Utils.dart';
import '../../utilities/preferences.dart';
import '../../views/common_widgets/custom_instana_text_field.dart';
import '../../views/common_widgets/ibm_instana_app_bar.dart';
import '../../views/common_widgets/instana_button.dart';
import '../../views/common_widgets/instana_switch_widget.dart';

/**
 * The developer can update the sections as new features are added to Agent
 */
class ConfigurationUpdateScreen extends StatefulWidget {
  ConfigurationUpdateScreen({super.key});
  @override
  State<ConfigurationUpdateScreen> createState() => _State();

}
class _State extends State<ConfigurationUpdateScreen> {

  var instanaKeyController = TextEditingController();
  var instanaUrlController = TextEditingController();
  bool? defaultCollectionEnabled = false;

  Future<void> setupUI() async {
    String? key = await Preferences.getPrefString(Constant.INSTANA_KEY);
    String? url = await Preferences.getPrefString(Constant.INSTANA_REPORTING_URL);
    defaultCollectionEnabled = await Preferences.getPrefBool(Constant.COLLECTION_ENABLED);
    defaultCollectionEnabled ??= false;
    if (key != null) {
      setState(() {
        instanaKeyController.text = key;
      });
    }
    if (url != null) {
      setState(() {
        instanaUrlController.text = url;
      });
    }
  }

  Future<void> _updateConfiguration() async {
    await Preferences.savePrefString(Constant.INSTANA_KEY, instanaKeyController.text.toString());
    await Preferences.savePrefString(Constant.INSTANA_REPORTING_URL, instanaUrlController.text.toString());
    await Preferences.savePrefBool(Constant.COLLECTION_ENABLED, defaultCollectionEnabled!);
  }

  @override
  void initState() {
    super.initState();
    setupUI();
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
              CustomInstanaTextField(
                  hintText: "Instana Key", controller: instanaKeyController),
              CustomInstanaTextField(
                  hintText: "Instana Reporting URL",
                  controller: instanaUrlController),
              SizedBox(
                height: 30,
              ),
              InstanaSwitchWidget(
                value: defaultCollectionEnabled!,
                onChanged: (bool value) {
                  setState(() {
                    defaultCollectionEnabled = value;
                  });
                },
                labelText: 'Collection Enabled',
              ),
              SizedBox(
                height: 40,
              ),
              CustomInstanaButton(
                text: 'Update Configurations',
                onPressed: () {
                  _updateConfiguration();
                  Utilities.showToastMessage(
                      "Configurations Updated");
                },
              ),
            ],
          ),
        ),
      ),
    );
  }
}



