
import 'package:flutter/material.dart';

class IBMInstanaAppBar extends StatelessWidget implements PreferredSizeWidget {
  @override
  Size get preferredSize => Size.fromHeight(kToolbarHeight);

  @override
  Widget build(BuildContext context) {
    return AppBar(
      backgroundColor: Colors.blue[900],
      elevation: 0, //// Remove the shadow if you don't need it
      title: Row(
        children: [
          SizedBox(
            width: 60,
            height: 40,
            child: Image.asset('assets/images/eye_bee_m.png',color: Colors.white,),
          ),
          SizedBox(width: 8),
          SizedBox(
            width: 40,
            height: 40,
            child: Image.asset('assets/images/instana_logo.webp',color: Colors.white,),
          ),
        ],
      ),
      centerTitle: true, // Center the title (icons will be aligned to the center vertically)
    );
  }
}