package com.zippyttech.downloadsvg.common;

import android.graphics.drawable.Drawable;

import org.json.JSONException;

/**
 * Created by Darwin C on 5/9/19.
 */
public interface OnImageTaskCompleted {
    void OnDrawableTaskCompleted(String response) throws Exception;
}
