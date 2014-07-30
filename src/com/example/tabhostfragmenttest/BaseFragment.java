package com.example.tabhostfragmenttest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaseFragment extends Fragment implements OnKeyDownCallback ,PutFragmentStackCallback{
    private static final String TAG = BaseFragment.class.getSimpleName();
    private static final String CURRENT_FRAGMENT_TAG = "Container";
    private FragmentManager mFM;

	@Override
	public void onAttach(Activity activity) {
		// TODO 自動生成されたメソッド・スタブ
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		this.mFM = this.getChildFragmentManager();
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_container, container, false);
//		// TODO 自動生成されたメソッド・スタブ
//		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		Log.d(TAG,"onViewCreated "+this);
		
		if(savedInstanceState == null && mFM.getBackStackEntryCount() == 0){
			Fragment newFragment = new SampleFragment(); 
			newFragment.setArguments(this.getArguments());

			mFM.beginTransaction()
			.replace(R.id.container, newFragment,CURRENT_FRAGMENT_TAG)
			.addToBackStack(null)
			.commit();
		}
		ActionBarActivity act = (ActionBarActivity)this.getActivity();
		if(mFM.getBackStackEntryCount() > 1){
			act.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		} else {
			act.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		}

		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onDestroy() {
		// TODO 自動生成されたメソッド・スタブ
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(this.mFM.getBackStackEntryCount() > 1){
			this.popBackStack();
			return true;
		}

		return false;
	}

	@Override
	public void putStack(Fragment fragment, Bundle bundle) {
		
		//TODO testcode 
		int index = mFM.getBackStackEntryCount();
		if(mFM.getBackStackEntryCount() == 1){
			Log.d(TAG,"getBackStackEntryCount() == 2");
			//1タブに画面が2個以上スタックしていれば、アクションバーのバックボタンを有効にする
			//2以降は常に有効にしていれば、いいので判定は2かどうかで判定する
			ActionBarActivity act = (ActionBarActivity)this.getActivity();
			act.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}

		bundle.putInt("index", index++);
		fragment.setArguments( bundle );

		mFM.beginTransaction()
		.replace(R.id.container, fragment,CURRENT_FRAGMENT_TAG)
		.addToBackStack(null)
		.commit();
		
		this.setDisplayHomeAsUpEnabled();
	}
	
	public void setDisplayHomeAsUpEnabled(){

//		if(mFM.getBackStackEntryCount() > 1){
//			Log.d(TAG,"getBackStackEntryCount() == 2");
//			//1タブに画面が2個以上スタックしていれば、アクションバーのバックボタンを有効にする
//			//2以降は常に有効にしていれば、いいので判定は2かどうかで判定する
//			ActionBarActivity act = (ActionBarActivity)this.getActivity();
//			act.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//		} else if(mFM.getBackStackEntryCount() <= 1){
//			Log.d(TAG,"getBackStackEntryCount() < 2");
//			//1タブに画面のスタックが1つであればアクションバーのバックボタンを無効にする
//			ActionBarActivity act = (ActionBarActivity)this.getActivity();
//			act.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//		}
		
	}

	@Override
	public void popBackStack() {
		if(mFM.getBackStackEntryCount() == 2){
			Log.d(TAG,"getBackStackEntryCount() < 2");
			//1タブに画面のスタックが1つであればアクションバーのバックボタンを無効にする
			ActionBarActivity act = (ActionBarActivity)this.getActivity();
			act.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		}

		this.mFM.popBackStack();
		this.setDisplayHomeAsUpEnabled();
	
	}


	//バックキー押下の戻り onKeyDownでひらう
	//縦管理

}
