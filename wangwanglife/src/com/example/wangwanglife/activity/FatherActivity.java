package com.example.wangwanglife.activity;

import com.example.wangwanglife.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

/**
 * activity基类
 * 
 * @author xl
 * @date:2016-7-24下午3:06:09
 * @description
 */
public abstract class FatherActivity extends FragmentActivity {
	protected Bundle mSavedInstanceState;
	protected InputMethodManager inputMethodManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mSavedInstanceState = savedInstanceState;
		setContentView(getLayoutId());
		initValues();
		initView();
		doOperate();
	}

	/** 设置布局ID */
	protected abstract int getLayoutId();

	/** 初始值 */
	protected abstract void initValues();

	/** 初始化控件 */
	protected abstract void initView();

	/** 进行操作 */
	protected abstract void doOperate();

	/**
	 * 初始默认头部
	 * 
	 * @author xl
	 * @date:2016-7-24下午3:11:37
	 * @description 左侧按钮返回,中间title文本
	 * @param titleId
	 * @param withLeft
	 *            看UI图好像有些界面左侧没返回按钮
	 */
	protected void initDefautHead(int titleId, boolean withLeft) {
		TextView center = (TextView) findViewById(R.id.tv_head_center);
		if (center != null) {
			center.setText(titleId);
		}
		if (withLeft) {
			View left = findViewById(R.id.rl_head_left);
			if (left != null) {
				left.findViewById(R.id.tv_head_left).setBackgroundResource(
						R.drawable.ic_launcher);
				left.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						finish();
					}
				});
			}
		}

	}

	/** 隐藏软键盘 */
	public void hideSoftKeyboard() {
		if (inputMethodManager == null) {
			inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		}
		if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
			if (getCurrentFocus() != null)
				inputMethodManager.hideSoftInputFromWindow(getCurrentFocus()
						.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	/** 改变背景色 */
	public void changeBackgroundAlpha(float alpha) {
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.alpha = alpha;
		if (alpha == 1) {
			getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);// 不移除该Flag的话,在有视频的页面上的视频会出现黑屏的bug
		} else {
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);// 此行代码主要是解决在华为手机上半透明效果无效的bug
		}
		getWindow().setAttributes(lp);
	}
}
