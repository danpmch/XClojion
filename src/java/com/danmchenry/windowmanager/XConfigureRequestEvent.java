package com.danmchenry.windowmanager;

import com.sun.jna.Pointer;
import com.sun.jna.NativeLong;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

@FieldOrder({"type", "serial", "send_event", "display", "parent", "window", "x", "y", "width", "height", "border_width", "above", "detail", "value_mask"})
public class XConfigureRequestEvent extends Structure {
    public int type;
    public NativeLong serial;	/* # of last request processed by server */
    public boolean send_event;	/* true if this came from a SendEvent request */
    public Pointer display;	/* Display the event was read from */
    public NativeLong parent;
    public NativeLong window;
    public int x, y;
    public int width, height;
    public int border_width;
    public NativeLong above;
    public int detail;		/* Above, Below, TopIf, BottomIf, Opposite */
    public NativeLong value_mask;
}
