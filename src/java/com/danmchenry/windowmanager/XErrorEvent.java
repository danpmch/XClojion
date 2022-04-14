package com.danmchenry.windowmanager;

import com.sun.jna.Pointer;
import com.sun.jna.NativeLong;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

@FieldOrder({"type", "display", "resourceid", "serial", "error_code", "request_code", "minor_code"})
public class XErrorEvent extends Structure {
    public int type;
    public Pointer display;	/* Display the event was read from */
    public NativeLong resourceid;		/* resource id */
    public NativeLong serial;	/* serial number of failed request */
    public char error_code;	/* error code of failed request */
    public char request_code;	/* Major op-code of failed request */
    public char minor_code;	/* Minor op-code of failed request */
}
