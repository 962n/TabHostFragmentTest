package com.example.tabhostfragmenttest;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public interface PutFragmentStackCallback {
	public void putStack(Fragment fragment, Bundle bundle);
	public void popBackStack();
}
