import 'package:flutter/material.dart';
import 'package:ibm_instana_flutter_agent_demo/utilities/Utils.dart';
import 'package:instana_agent/instana_agent.dart';

import '../../views/common_widgets/custom_instana_text_field.dart';
import '../../views/common_widgets/ibm_instana_app_bar.dart';
import '../../views/common_widgets/instana_button.dart';

class CustomEventsScreen extends StatelessWidget {
  CustomEventsScreen({super.key});

  var eventNameController = TextEditingController();
  var viewNameController = TextEditingController();
  var metaKeyController = TextEditingController();
  var metaValueController = TextEditingController();

  Future<void> _makeCustomEvent(String eventName, String metaKey,
      String metaValue, String viewName) async {
    await InstanaAgent.reportEvent(
        name: eventName,
        options: EventOptions()
          ..viewName = viewName
          ..startTime = DateTime.now().millisecondsSinceEpoch
          ..duration = 3 * 1000
          ..meta = {
            metaKey: metaValue,
          });
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
                  hintText: "Event Name", controller: eventNameController),
              CustomInstanaTextField(
                  hintText: "View Name", controller: viewNameController),
              CustomInstanaTextField(
                  hintText: "Meta key", controller: metaKeyController),
              CustomInstanaTextField(
                  hintText: "Meta Value", controller: metaValueController),
              SizedBox(
                height: 30,
              ),
              CustomInstanaButton(
                text: 'Create Event',
                onPressed: () async {
                  _makeCustomEvent(
                      eventNameController.text.toString(),
                      metaKeyController.text.toString(),
                      metaValueController.text.toString(),
                      viewNameController.text.toString());
                  Utilities.showToastMessage("Custom Event Created");
                },
              ),
            ],
          ),
        ),
      ),
    );
  }
}
