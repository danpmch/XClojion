package com.danmchenry.windowmanager;

import com.sun.jna.Pointer;
import com.sun.jna.NativeLong;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

@FieldOrder({"type", "serial", "send_event", "display", "event", "window", "x", "y", "width", "height", "border_width", "above", "override_redirect"})
public class XConfigureEvent extends Structure {
    public int type;
    public NativeLong serial;	/* # of last request processed by server */
    public boolean send_event;	/* true if this came from a SendEvent request */
    public Pointer display;	/* Display the event was read from */
    public NativeLong event;
    public NativeLong window;
    public int x, y;
    public int width, height;
    public int border_width;
    public NativeLong above;
    public boolean override_redirect;
}
