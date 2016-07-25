package com.example.wangwanglife.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.example.wangwanglife.R;
import com.example.wangwanglife.fragment.FatherFragment;
import com.example.wangwanglife.fragment.HomePageFragment;
import com.example.wangwanglife.fragment.OrderFragment;
import com.example.wangwanglife.fragment.PersonCenterFragment;
import com.example.wangwanglife.fragment.ShareFragment;
import com.example.wangwanglife.utils.WWToast;

/**
 * 开工
 * 
 * @author xl
 * @date:2016-7-24下午3:14:45
 * @description
 */
public class MainActivity extends FatherActivity {

	private View[] mTabs;
	private FatherFragment[] mFragments;

	private HomePageFragment mHomePageFragment;
	private OrderFragment mOrderFragment;
	private PersonCenterFragment mPersonCenterFragment;
	private ShareFragment mShareFragment;

	private final static int TAB_HOMEPAGE = 0;
	private final static int TAB_ORDER = 1;
	private final static int TAB_PERSONCENTER = 2;
	private final static int TAB_SHARE = 3;
	/** 欲选中tab */
	private int mIndex;
	/** 当前的选中的tab */
	private int mCurrentTabIndex;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	protected void initValues() {
		initDefautHead(R.string.app_name, false);
	}

	@Override
	protected void initView() {
		mHomePageFragment = new HomePageFragment();
		mOrderFragment = new OrderFragment();
		mPersonCenterFragment = new PersonCenterFragment();
		mShareFragment = new ShareFragment();
		mFragments = new FatherFragment[] { mHomePageFragment, mOrderFragment,
				mPersonCenterFragment, mShareFragment };

		getSupportFragmentManager().beginTransaction()
				.add(R.id.rl_container, mHomePageFragment)
				.add(R.id.rl_container, mOrderFragment).hide(mOrderFragment)
				.show(mHomePageFragment).commit();
		mTabs = new View[4];
		mTabs[TAB_HOMEPAGE] = findViewById(R.id.rl_tab_homepage);
		mTabs[TAB_ORDER] = findViewById(R.id.rl_tab_order);
		mTabs[TAB_PERSONCENTER] = findViewById(R.id.rl_tab_person_center);
		mTabs[TAB_SHARE] = findViewById(R.id.rl_tab_share);
	}

	@Override
	protected void doOperate() {

	}

	/**
	 * 底部tab点击事件
	 * 
	 * @author xl
	 * @date:2016-7-25下午1:00:06
	 * @description
	 * @param view
	 */
	public void onTabClick(View view) {
		switch (view.getId()) {
		case R.id.rl_tab_homepage:
			mIndex = TAB_HOMEPAGE;
			break;
		case R.id.rl_tab_order:
			mIndex = TAB_ORDER;
			break;
		case R.id.rl_tab_person_center:
			mIndex = TAB_PERSONCENTER;
			break;
		case R.id.rl_tab_share:
			mIndex = TAB_SHARE;
			break;
		default:
			break;
		}
		if (mCurrentTabIndex != mIndex) {
			FragmentTransaction trx = getSupportFragmentManager()
					.beginTransaction();
			trx.hide(mFragments[mCurrentTabIndex]);
			if (!mFragments[mIndex].isAdded()) {
				trx.add(R.id.rl_container, mFragments[mIndex]);
			}
			trx.show(mFragments[mIndex]).commit();
		}
		mTabs[mCurrentTabIndex].setSelected(false);
		mTabs[mIndex].setSelected(true);
		mCurrentTabIndex = mIndex;
	}
}
