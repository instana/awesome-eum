import 'package:flutter/material.dart';
import 'package:ibm_instana_flutter_agent_demo/screens/profile_screen/profile_screen.dart';

import '../../views/common_widgets/ibm_instana_app_bar.dart';
import '../../views/common_widgets/instana_button.dart';
import '../configuration_view_update/configuration_view_update_screen.dart';
import '../create_all_at_once/create_all_at_once.dart';
import '../create_custom_events/custom_events_screen.dart';
import '../generate_crash/generate_crash_screen.dart';
import '../multi_view/view_one_test_screen.dart';
import '../network_call/make_network_call_screen.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {

    void _navigationHelper(Widget screen) {
      Navigator.of(context).push(
        MaterialPageRoute(
          builder: (context) => screen,
        ),
      );
    }

    return Scaffold(
      appBar: IBMInstanaAppBar(),
      body: Center(
        child: SingleChildScrollView(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: <Widget>[
              CustomInstanaButton(
                text: 'Create User Data',
                onPressed: () {
                  _navigationHelper(ProfileScreen());
                },
              ),
              CustomInstanaButton(
                text: 'Update Configuration',
                onPressed: () {
                  _navigationHelper(ConfigurationUpdateScreen());
                },
              ),
              CustomInstanaButton(
                text: 'Make Network Call',
                onPressed: () {
                  _navigationHelper(NetworkCallScreen());
                },
              ),
              CustomInstanaButton(
                text: 'Add Views',
                onPressed: () {
                  _navigationHelper(ViewOneTestScreen());
                },
              ),
              CustomInstanaButton(
                text: 'Create Crash',
                onPressed: () {
                  _navigationHelper(GenerateCrashScreen());
                },
                isEditable: false,
              ),
              CustomInstanaButton(
                text: 'Create Test Logs',
                onPressed: () {},
                isEditable: false,
              ),
              CustomInstanaButton(
                text: 'Create Custom Events',
                onPressed: () {
                  _navigationHelper(CustomEventsScreen());
                },
              ),
              CustomInstanaButton(
                text: 'Run All At Once',
                onPressed: () {
                  _navigationHelper(CreateAllAtOnce());
                },
              ),
            ],
          ),
        ),
      ),
    );
  }
}
