package com.example.tabhostfragmenttest;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        
        final FragmentTabHost host = (FragmentTabHost) findViewById(R.id.tabhost);
        host.setup(this, getSupportFragmentManager(), R.id.content);
        host.setOnTabChangedListener(new OnTabChangeListener(){
            @Override
            public void onTabChanged(String tabId) {
                //このメソッドはActivity起動時のタブ設定時もコールされる
                Log.d(TAG,"onTabChanged tabId = "+tabId);
                View tabView = host.getCurrentTabView();
                View view = host.getCurrentView();
                Log.d(TAG,"onTabChanged tabView = "+tabView);
                Log.d(TAG,"onTabChanged view = "+view);
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content);
                Log.d(TAG,"onTabChanged fragment = "+fragment);
            }});

        //以下のメソッドでタブ位置を変更できる
//        host.setCurrentTab(0);


        String[] titles = {"tab1","tab2","tab3","tab4","tab5"};
        
        LayoutInflater inflater = LayoutInflater.from(this.getApplicationContext());
        for(int i = 0;i< titles.length;i++){
            TabSpec tabSpec = host.newTabSpec(titles[i]);

            View v = inflater.inflate(R.layout.tab_item, null);
            //TODO タブのアイコンを設定する
//            ImageView image = (ImageView)v.findViewById(R.id.tab_item_image);
//            image.setImageDrawable(drawable);

            //タブのタイトルを設定する
            TextView text = (TextView)v.findViewById(R.id.tab_item_text);
            text.setText(titles[i]);

            tabSpec.setIndicator(v);

            Bundle bundle = new Bundle();
            bundle.putString("name", titles[i]);
            host.addTab(tabSpec, BaseFragment.class, bundle);
        }

        TabWidget widget = host.getTabWidget();
        //タブの境界線の長さを調整
        widget.setDividerPadding(0);

	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
	public void onAttachFragment(Fragment fragment) {
        Log.d(TAG,"onAttachFragment fragment = "+fragment);
		super.onAttachFragment(fragment);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content);
		if(fragment instanceof OnKeyDownCallback){
			if(((OnKeyDownCallback)fragment).onKeyDown(keyCode, event)){
				return true;
			}
		}

		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onSupportNavigateUp() {
		Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content);
		if(fragment instanceof PutFragmentStackCallback){
			((PutFragmentStackCallback)fragment).popBackStack();
			return true;
		}
		return super.onSupportNavigateUp();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
