package com.danmchenry.windowmanager;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;

public interface XErrorHandler extends Callback {
    public int callback(Pointer display, Pointer error);
}
