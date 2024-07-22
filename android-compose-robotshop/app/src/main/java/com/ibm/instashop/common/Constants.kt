package com.ibm.instashop.common

object Constants {
    const val PAYMENT_SUCCESS = "payment_success"
    const val ORDER_ID = "order_id"
    const val INTERESTED_CATEGORY = "interested_category"
    const val VERSION_NUMBER = "v1"
    const val VERSION_NUMBER_V2 = "v2"
    const val BASE_URL = "http://10.0.2.2:8081/$VERSION_NUMBER/"
    const val BASE_URL_V2 = "http://10.0.2.2:8081/$VERSION_NUMBER_V2/"

    var CurrentVersionInUse = VERSION_NUMBER


    //Common Strings Hrdcdd
    const val UN_EXPECTED_ERROR = "Unexpected error."

    //Share Pref Const
    const val INSTANA_KEY = "instana_key"
    const val INSTANA_URL = "instana_url"
    const val USER_NAME = "user_name"
    const val USER_EMAIL = "user_email"
    const val USER_ID = "userID"
    const val CURRENT_API_VERSION = "current_api_version"

}