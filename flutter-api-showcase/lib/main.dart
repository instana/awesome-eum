import 'dart:io';

import 'package:flutter/material.dart';
import 'package:ibm_instana_flutter_agent_demo/screens/credentials_screen/credentials_screen.dart';
import 'package:instana_agent/http_overrides.dart';

void main() {
  HttpOverrides.global = InstanaHttpOverrides();
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.blue),
        useMaterial3: true,
      ),
      home: const CredentialsScreen(),
    );
  }
}
