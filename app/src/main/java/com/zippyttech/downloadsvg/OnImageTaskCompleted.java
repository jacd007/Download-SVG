package com.zippyttech.downloadsvg;

import android.graphics.drawable.Drawable;

import org.json.JSONException;

/**
 * Created by Darwin C on 5/9/19.
 */
public interface OnImageTaskCompleted {
    void OnDrawableTaskCompleted(String response) throws JSONException;
}
