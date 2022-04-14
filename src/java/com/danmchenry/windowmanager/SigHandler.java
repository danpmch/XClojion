package com.danmchenry.windowmanager;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;

public interface SigHandler extends Callback {
    public void callback(int sig);
}
