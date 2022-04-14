package com.danmchenry.windowmanager;

import com.sun.jna.Pointer;
import com.sun.jna.NativeLong;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

@FieldOrder({"type", "serial", "send_event", "display", "drawable", "major_code", "minor_code"})
public class XNoExposeEvent extends Structure {
    public int type;
    public NativeLong serial;	/* # of last request processed by server */
    public boolean send_event;	/* true if this came from a SendEvent request */
    public Pointer display;	/* Display the event was read from */
    public NativeLong drawable;
    public int major_code;		/* core is CopyArea or CopyPlane */
    public int minor_code;		/* not defined in the core */
}
