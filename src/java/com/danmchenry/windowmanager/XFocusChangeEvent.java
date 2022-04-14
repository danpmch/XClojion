package com.danmchenry.windowmanager;

import com.sun.jna.Pointer;
import com.sun.jna.NativeLong;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

@FieldOrder({"type", "serial", "send_event", "display", "window", "mode", "detail"})
public class XFocusChangeEvent extends Structure {
    public int type;		/* FocusIn or FocusOut */
    public NativeLong serial;	/* # of last request processed by server */
    public boolean send_event;	/* true if this came from a SendEvent request */
    public Pointer display;	/* Display the event was read from */
    public NativeLong window;		/* window of event */
    public int mode;		/* NotifyNormal, NotifyWhileGrabbed, NotifyGrab, NotifyUngrab */
    public int detail;
    /*
     * NotifyAncestor, NotifyVirtual, NotifyInferior,
     * NotifyNonlinear,NotifyNonlinearVirtual, NotifyPointer,
     * NotifyPointerRoot, NotifyDetailNone
     */
}
