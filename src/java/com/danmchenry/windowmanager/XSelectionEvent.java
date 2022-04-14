package com.danmchenry.windowmanager;

import com.sun.jna.Pointer;
import com.sun.jna.NativeLong;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

@FieldOrder({"type", "serial", "send_event", "display", "requestor", "selection", "target", "property", "time"})
public class XSelectionEvent extends Structure {
    public int type;
    public NativeLong serial;	/* # of last request processed by server */
    public boolean send_event;	/* true if this came from a SendEvent request */
    public Pointer display;	/* Display the event was read from */
    public NativeLong requestor;
    public NativeLong selection;
    public NativeLong target;
    public NativeLong property;		/* ATOM or None */
    public NativeLong time;
}
