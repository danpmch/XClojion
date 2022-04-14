package com.danmchenry.windowmanager;

import com.sun.jna.Pointer;
import com.sun.jna.NativeLong;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

@FieldOrder({"type", "serial", "send_event", "display", "window", "root", "subwindow", "time", "x", "y", "x_root", "y_root", "state", "button", "same_screen"})
public class XMotionEvent extends Structure {
    public int type;		/* of event */
    public NativeLong serial;	/* # of last request processed by server */
    public boolean send_event;	/* true if this came from a SendEvent request */
    public Pointer display;	/* Display the event was read from */
    public NativeLong window;	        /* "event" window reported relative to */
    public NativeLong root;	        /* root window that the event occurred on */
    public NativeLong subwindow;	/* child window */
    public NativeLong time;		/* milliseconds */
    public int x, y;		/* pointer x, y coordinates in event window */
    public int x_root, y_root;	/* coordinates relative to root */
    public int state;	/* key or button mask */
    public char is_hint;		/* detail */
    public boolean same_screen;	/* same screen flag */
}
