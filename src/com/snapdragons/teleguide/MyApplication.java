package com.snapdragons.teleguide;

import android.app.Application;

public class MyApplication extends Application{
	private static String uid;

    public String getuid() {
        return uid;
    }

    public void setuid(String uid) {
        this.uid = uid;
    }
}
