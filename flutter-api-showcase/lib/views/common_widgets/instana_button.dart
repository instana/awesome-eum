import 'package:flutter/material.dart';

class CustomInstanaButton extends StatelessWidget {
  final String text;
  bool isEditable;
  final VoidCallback onPressed;

  CustomInstanaButton({super.key, required this.text, required this.onPressed,this.isEditable=true});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.symmetric(vertical: 10,horizontal: 20),
      child: ElevatedButton(
        onPressed: isEditable?onPressed:null,
        style: ElevatedButton.styleFrom(
          primary: isEditable?Colors.blue[900]:Colors.grey, // Background color
          onPrimary: Colors.white, // Text color
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(50),
          ),
          padding: EdgeInsets.symmetric(horizontal: 20, vertical: 10),
        ),
        child: Text(
          text,
          style: TextStyle(fontSize: 16),
        ),
      ),
    );
  }
}
