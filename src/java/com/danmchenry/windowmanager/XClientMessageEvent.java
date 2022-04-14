package com.danmchenry.windowmanager;

import com.sun.jna.Pointer;
import com.sun.jna.NativeLong;
import com.sun.jna.Union;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

@FieldOrder({"type", "serial", "send_event", "display", "window", "message_type", "format", "data"})
public class XClientMessageEvent extends Structure {
    public int type;
    public unsigned long serial;	/* # of last request processed by server */
    public Bool send_event;	/* true if this came from a SendEvent request */
    public Display *display;	/* Display the event was read from */
    public Window window;
    public Atom message_type;
    public int format;
    public Data data;

    public class Data extends Union {
        public char[] b = new char[20];
        public short[] s = new short[10];
        public NativeLong[] l = new NativeLong[5];
		}
}
