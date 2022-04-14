package com.danmchenry.windowmanager;

import com.sun.jna.NativeLong;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

/*
 * Data structure for setting window attributes.
 */
@FieldOrder({
        "background_pixmap",
            "background_pixel",
            "border_pixmap",
            "border_pixel",
            "bit_gravity",
            "win_gravity",
            "backing_store",
            "backing_planes",
            "backing_pixel",
            "save_under",
            "event_mask",
            "do_not_propagate_mask",
            "override_redirect",
            "colormap",
            "cursor"
            })
public class XSetWindowAttributes extends Structure {
    public NativeLong background_pixmap;	/* background or None or ParentRelative */
    public NativeLong background_pixel;	/* background pixel */
    public NativeLong border_pixmap;	/* border of the window */
    public NativeLong border_pixel;	/* border pixel value */
    public int bit_gravity;		/* one of bit gravity values */
    public int win_gravity;		/* one of the window gravity values */
    public int backing_store;		/* NotUseful, WhenMapped, Always */
    public NativeLong backing_planes;/* planes to be preserved if possible */
    public NativeLong backing_pixel;/* value to use in restoring planes */
    public boolean save_under;		/* should bits under be saved? (popups) */
    public NativeLong event_mask;		/* set of events that should be saved */
    public NativeLong do_not_propagate_mask;	/* set of events that should not propagate */
    public boolean override_redirect;	/* boolean value for override-redirect */
    public NativeLong colormap;		/* color map to be associated with window */
    public NativeLong cursor;		/* cursor to be displayed (or None) */
}
