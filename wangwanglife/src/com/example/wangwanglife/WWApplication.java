package com.example.wangwanglife;

import org.xutils.x;

import com.example.wangwanglife.utils.WWToast;

import android.app.Application;

public class WWApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		x.Ext.init(this);
		x.Ext.setDebug(true);
		WWToast.init(this);
	}
}
