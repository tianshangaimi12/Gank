package com.example.gank.main;



import com.example.gank.presenter.ActivityCollector;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.text.TextUtils;
import android.text.style.TtsSpan;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity{
	private DrawerLayout mDrawerLayout;
	private Toolbar mToolbar;
	private FragmentManager fm;
	private FragmentTransaction transaction;
	
	public final String TAG="MainActivity";
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	public void initView()
	{
		fm=getSupportFragmentManager();
		transaction=fm.beginTransaction();
		NewsFragment newsFragment=new NewsFragment();
		transaction.add(R.id.framlayout_news, newsFragment,"NewsFragment");
		transaction.commit();
		mToolbar=(Toolbar)findViewById(R.id.toolbar_main);
		mToolbar.setTitle("");
		final TextView textViewTitle=(TextView)findViewById(R.id.txt_title);
		textViewTitle.setText(getResources().getString(R.string.main_title));
		setSupportActionBar(mToolbar);
		mToolbar.setNavigationIcon(R.drawable.navigation);
		mToolbar.setNavigationOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mDrawerLayout.openDrawer(Gravity.LEFT);
			}
		});
		mToolbar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem arg0) {
				switch (arg0.getItemId()) {
				case R.id.btn_exit:
					ActivityCollector.finishAll();
					break;
				case R.id.btn_back:
					finishWebFragment();
					break;
				default:
					break;
				}
				return true;
			}
		});
		mDrawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
		mDrawerLayout.setDrawerListener(new DrawerListener() {
			
			@Override
			public void onDrawerStateChanged(int arg0) {
			}
			
			@Override
			public void onDrawerSlide(View arg0, float arg1) {
				float angle=arg1*360;
				ObjectAnimator animator=ObjectAnimator.ofFloat(textViewTitle, "rotationX", angle,arg1*360);
				animator.setDuration(1);
				animator.start();
			}
			
			@Override
			public void onDrawerOpened(View arg0) {
			}
			
			@Override
			public void onDrawerClosed(View arg0) {
			
			}
		});
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	public void loadUrl(String url)
	{
		if(!TextUtils.isEmpty(url))
		{
			transaction=fm.beginTransaction();
			transaction.add(R.id.framlayout_news, new WebViewFragment(this,url), "WebViewFragment");
			transaction.addToBackStack(null);
			transaction.commit();
		}
	}
	
	public void finishWebFragment()
	{
		WebViewFragment webViewFragment=(WebViewFragment) fm.findFragmentByTag("WebViewFragment");
		if(webViewFragment!=null)
		{
			transaction=fm.beginTransaction();
			transaction.remove(webViewFragment);
			transaction.commit();
		}
	}
	
	public void removeNewsFragment()
	{
		NewsFragment newsFragment=(NewsFragment) fm.findFragmentByTag("NewsFragment");
		if(newsFragment!=null)
		{
			transaction=fm.beginTransaction();
			transaction.remove(newsFragment);
			transaction.commit();
		}
	}
	
	public void showTypeNewsFragment(int type)
	{
		removeNewsFragment();
		TypeNewsFragment typeNewsFragment=new TypeNewsFragment();
		Bundle bundle=new Bundle();
		bundle.putInt("type", type);
		typeNewsFragment.setArguments(bundle);
		transaction=fm.beginTransaction();
		transaction.add(R.id.framlayout_news, typeNewsFragment, "TypeNewsFragment");
		transaction.commit();
		mDrawerLayout.closeDrawer(Gravity.LEFT);
	}
	
	public void returnNewsFragment()
	{
		NewsFragment newsFragment=(NewsFragment) fm.findFragmentByTag("NewsFragment");
		if(newsFragment==null)
		{
			newsFragment=new NewsFragment();
			transaction=fm.beginTransaction();
			transaction.replace(R.id.framlayout_news, newsFragment,"NewsFragment");
			transaction.commit();
		}
		else {
			Toast.makeText(this, getResources().getString(R.string.rightnow_toast), Toast.LENGTH_SHORT).show();
		}
		mDrawerLayout.closeDrawer(Gravity.LEFT);
	}
}
