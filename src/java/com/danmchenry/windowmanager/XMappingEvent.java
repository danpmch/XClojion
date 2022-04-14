package com.danmchenry.windowmanager;

import com.sun.jna.Pointer;
import com.sun.jna.NativeLong;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

@FieldOrder({"type", "serial", "send_event", "display", "window", "request", "first_keycode", "count"})
public class XMappingEvent extends Structure {
    public int type;
    public NativeLong serial;	/* # of last request processed by server */
    public boolean send_event;	/* true if this came from a SendEvent request */
    public Pointer display;	/* Display the event was read from */
    public NativeLong window;		/* unused */
    public int request;		/* one of MappingModifier, MappingKeyboard, MappingPointer */
    public int first_keycode;	/* first keycode */
    public int count;		/* defines range of change w. first_keycode*/
}
