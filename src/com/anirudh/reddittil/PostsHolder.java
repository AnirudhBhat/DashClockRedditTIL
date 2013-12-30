package com.anirudh.reddittil;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

/*
 * This class is used to hold the top rated TIL post from Reddit.
 */

public class PostsHolder {
	private final String URL_TEMPLATE = "http://www.reddit.com/r/todayilearned/.json?after=AFTER";
	String url, title;
	String after;
	int score;

	PostsHolder() {
		after = "";
	}

	/*
	 * This method returns top rated post from subreddit TIL.
	 */

	String fetchPosts() {
		String raw = HTTPData.readContents(URL_TEMPLATE);
		try {
			JSONObject data = new JSONObject(raw).getJSONObject("data");
			JSONArray children = data.getJSONArray("children");
			after = data.getString("after");
			JSONObject cur = children.getJSONObject(0).getJSONObject("data");
			title = cur.optString("title");
			Log.d("TITLE", title);
			url = cur.optString("url");
			score = cur.optInt("score");
			Log.d("SCORE: ", "score is " + score);
			if (title != null)
				return title;
		} catch (Exception e) {
			Log.e("fetchPosts()", e.toString());
		}
		return title;
	}
}