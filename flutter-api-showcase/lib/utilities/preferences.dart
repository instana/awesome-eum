import 'package:shared_preferences/shared_preferences.dart';

class Preferences {

  static final Future<SharedPreferences> _prefs = SharedPreferences.getInstance();

  static savePrefString(String key,String value) async {
    SharedPreferences pref = await _prefs;
    pref.setString(key, value);
  }

  static Future<String?> getPrefString(String key) async {
    SharedPreferences pref = await _prefs;
    return pref.getString(key);
  }

  static savePrefBool(String key,bool value) async {
    SharedPreferences pref = await _prefs;
    pref.setBool(key, value);
  }

  static Future<bool?> getPrefBool(String key) async {
    SharedPreferences pref = await _prefs;
    return pref.getBool(key);
  }

}