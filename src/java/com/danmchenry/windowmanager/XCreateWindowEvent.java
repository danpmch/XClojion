package com.danmchenry.windowmanager;

import com.sun.jna.Pointer;
import com.sun.jna.NativeLong;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

@FieldOrder({"type", "serial", "send_event", "display", "parent", "window", "x", "y", "width", "height", "border_width", "override_redirect"})
public class XCreateWindowEvent extends Structure {
    public int type;
    public NativeLong serial;	/* # of last request processed by server */
    public boolean send_event;	/* true if this came from a SendEvent request */
    public Pointer display;	/* Display the event was read from */
    public NativeLong parent;		/* parent of the window */
    public NativeLong window;		/* window id of window created */
    public int x, y;		/* window location */
    public int width, height;	/* size of window */
    public int border_width;	/* border width */
    public boolean override_redirect;	/* creation should be overridden */
}
