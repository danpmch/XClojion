package com.danmchenry.windowmanager;

import com.sun.jna.Union;
import com.sun.jna.NativeLong;

/*
 * this union is defined so Xlib can always use the same sized
 * event structure internally, to avoid memory fragmentation.
 */
public class XEvent extends Union {
  public int type;		/* must not be changed; first element */
	public XAnyEvent xany;
	public XKeyEvent xkey;
	public XButtonEvent xbutton;
	public XMotionEvent xmotion;
	public XCrossingEvent xcrossing;
	public XFocusChangeEvent xfocus;
	public XExposeEvent xexpose;
	public XGraphicsExposeEvent xgraphicsexpose;
	public XNoExposeEvent xnoexpose;
	public XVisibilityEvent xvisibility;
	public XCreateWindowEvent xcreatewindow;
	public XDestroyWindowEvent xdestroywindow;
	public XUnmapEvent xunmap;
	public XMapEvent xmap;
	public XMapRequestEvent xmaprequest;
	public XReparentEvent xreparent;
	public XConfigureEvent xconfigure;
	public XGravityEvent xgravity;
	public XResizeRequestEvent xresizerequest;
	public XConfigureRequestEvent xconfigurerequest;
	public XCirculateEvent xcirculate;
	public XCirculateRequestEvent xcirculaterequest;
	public XPropertyEvent xproperty;
	public XSelectionClearEvent xselectionclear;
	public XSelectionRequestEvent xselectionrequest;
	public XSelectionEvent xselection;
	public XColormapEvent xcolormap;
	public XClientMessageEvent xclient;
	public XMappingEvent xmapping;
	public XErrorEvent xerror;
	public XKeymapEvent xkeymap;
	public XGenericEvent xgeneric;
	public XGenericEventCookie xcookie;
	public NativeLong[] pad = new NativeLong[24];
}
