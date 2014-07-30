package com.example.tabhostfragmenttest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class SampleFragment extends Fragment {
    private static final String TAG = SampleFragment.class.getSimpleName();

    private PutFragmentStackCallback callback;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate");
        String name = getArguments().getString("name");
        Log.d(TAG,name);
        this.setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView "+this);


        final String name = getArguments().getString("name");
        int index = getArguments().getInt("index", 0);

        Button button = new Button(this.getActivity());
        button.setGravity(Gravity.CENTER);
        String title = name + " index = "+index;
        button.setText(title);
        button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO 自動生成されたメソッド・スタブ
				if(callback != null){
					Bundle bundle = new Bundle();
					bundle.putString("name", name);
					callback.putStack(new SampleFragment(), bundle);
				}
			}});
        Log.d(TAG,name);
        
        ActionBarActivity act = (ActionBarActivity)this.getActivity();
        act.getSupportActionBar().setTitle(title);

        return button;
    }
	@Override
	public void onDestroy() {
		Log.d(TAG,"onDestroy "+this);
		super.onDestroy();
	}
	@Override
	public void onDestroyView() {
		Log.d(TAG,"onDestroyView "+this);
		super.onDestroyView();
	}
	@Override
	public void onSaveInstanceState(Bundle outState) {
		Log.d(TAG,"onSaveInstanceState "+this);
		super.onSaveInstanceState(outState);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.d(TAG,"onActivityCreated "+this);
		super.onActivityCreated(savedInstanceState);
	}
	@Override
	public void onAttach(Activity activity) {
		Log.d(TAG,"onAttach "+this);
		Fragment parent = this.getParentFragment();
		while(parent != null){
			if(parent instanceof PutFragmentStackCallback){
				callback = (PutFragmentStackCallback)parent;
				break;
			}
			parent = parent.getParentFragment();
		}
		super.onAttach(activity);
	}
	@Override
	public void onDetach() {
		Log.d(TAG,"onDetach "+this);
		super.onDetach();
	}
	@Override
	public void onPause() {
		Log.d(TAG,"onPause "+this);
		super.onPause();
	}
	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		Log.d(TAG,"onPrepareOptionsMenu "+this);
		super.onPrepareOptionsMenu(menu);
	}
	@Override
	public void onResume() {
		Log.d(TAG,"onResume "+this);
		super.onResume();
	}
	@Override
	public void onStart() {
		Log.d(TAG,"onStart "+this);
		super.onStart();
	}
	@Override
	public void onStop() {
		Log.d(TAG,"onStop "+this);
		super.onStop();
	}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		Log.d(TAG,"onViewCreated "+this);
		super.onViewCreated(view, savedInstanceState);
	}
	

}
