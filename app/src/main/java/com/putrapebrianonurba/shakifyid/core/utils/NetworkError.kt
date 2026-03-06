package com.putrapebrianonurba.shakifyid.core.utils

enum class NetworkError(val message: String) {
    NO_INTERNET("No Internet connection available"),
    NO_LOCATION_PERMISSION_GRANTED("No Location Permission Granted"),
    UNSTABLE_CONNECTION("Connectivity Error. Check Internet Connection"),
    HTTP_ERROR("Unsuccessful Server Request"),
    UNKNOWN("Unknown Error")
}