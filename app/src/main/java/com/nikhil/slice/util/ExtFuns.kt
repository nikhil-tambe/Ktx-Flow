package com.nikhil.slice.util

import timber.log.Timber

fun String.checkTwitterUrl() : String{
    val s = if (this.contains("twitter", true)) {
        when {
            this.contains("planet", true) -> {
                "https://pbs.twimg.com/profile_images/750115930022219776/LIAQyXiG_400x400.jpg"
            }
            this.contains("jupiter", true) -> {
                "https://pbs.twimg.com/profile_images/750115930022219776/LIAQyXiG_400x400.jpg"
            }
            this.contains("mars", true) -> {
                "https://pbs.twimg.com/profile_images/1251630544317583360/Y1o_N898_400x400.jpg"
            }
            this.contains("earth", true) -> {
                "https://pbs.twimg.com/profile_images/865587427162767361/-YOJW6Zp_400x400.jpg"
            }
            this.contains("moon", true) -> {
                "https://pbs.twimg.com/profile_images/1397355315/eclipse31_400x400.jpg"
            }
            else -> {
                "https://vignette.wikia.nocookie.net/rickandmorty/images/a/a6/Rick_Sanchez.png"
            }
        }
    } else {
        this
    }
    Timber.d("New Url ==> $s")
    return s
}