package com.danmchenry.windowmanager;

import com.sun.jna.Pointer;
import com.sun.jna.NativeLong;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

@FieldOrder({"type", "serial", "send_event", "display", "extension", "evtype"})
public class XGenericEvent extends Structure {
    public int            type;         /* of event. Always GenericEvent */
    public NativeLong  serial;       /* # of last request processed */
    public boolean           send_event;   /* true if from SendEvent request */
    public Pointer display;     /* Display the event was read from */
    public int            extension;    /* major opcode of extension that caused the event */
    public int            evtype;       /* actual event type. */
}
