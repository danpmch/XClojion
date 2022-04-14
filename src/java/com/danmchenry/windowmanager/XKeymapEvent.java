package com.danmchenry.windowmanager;

import com.sun.jna.Pointer;
import com.sun.jna.NativeLong;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

/* generated on EnterWindow and FocusIn  when KeyMapState selected */
@FieldOrder({"type", "serial", "send_event", "display", "window", "key_vector"})
public class XKeymapEvent extends Structure {
    public int type;
    public NativeLong serial;	/* # of last request processed by server */
    public boolean send_event;	/* true if this came from a SendEvent request */
    public Pointer display;	/* Display the event was read from */
    public NativeLong window;
    public char[] key_vector = new char[32];
}
