package com.anirudh.reddittil;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.google.android.apps.dashclock.api.DashClockExtension;
import com.google.android.apps.dashclock.api.ExtensionData;


public class TIL extends DashClockExtension{

	PostsHolder post = new PostsHolder();
	String url;
	@Override
	protected void onUpdateData(int UPDATE_REASON_SCREEN_ON) {
		// TODO Auto-generated method stub
		setUpdateWhenScreenOn(true);
		post.fetchPosts();
		Intent i = new Intent(Intent.ACTION_VIEW);
		Uri u = Uri.parse(post.url);
		i.setData(u);
		Log.d("entered onupdate", "work");
		publishUpdate(new ExtensionData()
				.visible(true)
				.icon(R.drawable.ic_reddit)
				.status(post.title)
				.expandedTitle("Today I Learned")
				.expandedBody(post.title)
				.clickIntent(i));
		
	}

}
