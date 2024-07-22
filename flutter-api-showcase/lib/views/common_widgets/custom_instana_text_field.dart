import 'package:flutter/material.dart';

class CustomInstanaTextField extends StatelessWidget {
  final String hintText;
  final TextEditingController controller;

  const CustomInstanaTextField({
    Key? key,
    required this.hintText,
    required this.controller,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.symmetric(vertical: 10,horizontal: 0),
      child: Container(
        margin: EdgeInsets.symmetric(horizontal: 20,vertical: 0),
        decoration: BoxDecoration(
          border: Border.all(color: Colors.grey),
          borderRadius: BorderRadius.circular(8.0),
        ),
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 12.0),
          child: TextField(
            controller: controller,
            style: TextStyle(color: Colors.black),
            decoration: InputDecoration(
              hintText: hintText,
              border: InputBorder.none,
            ),
          ),
        ),
      ),
    );
  }
}
