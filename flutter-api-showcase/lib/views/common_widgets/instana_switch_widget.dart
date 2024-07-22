import 'package:flutter/material.dart';

class InstanaSwitchWidget extends StatefulWidget {
  final String labelText;
  final bool value;
  final Function(bool) onChanged;

  InstanaSwitchWidget({
    required this.labelText,
    required this.value,
    required this.onChanged,
  });

  @override
  _InstanaSwitchWidgetState createState() => _InstanaSwitchWidgetState();
}

class _InstanaSwitchWidgetState extends State<InstanaSwitchWidget> {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: Row(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Text(widget.labelText),
          SizedBox(width: 10),
          Switch(
            value: widget.value,
            onChanged: (value) {
              setState(() {
                widget.onChanged(value);
              });
            },
          ),
        ],
      ),
    );
  }
}
