package com.danmchenry.windowmanager;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

@FieldOrder({
        "x"
        , "y"
        , "width"
        , "height"
        , "border_width"
        , "depth"
        , "visual"
        , "root"
        , "clazz"
            , "bit_gravity"
            , "win_gravity"
            , "backing_store"
            , "backing_planes"
            , "backing_pixel"
            , "save_under"
            , "colormap"
            , "map_installed"
            , "map_state"
            , "all_event_masks"
            , "your_event_mask"
            , "do_not_propagate_mask"
            , "override_redirect"
            , "screen"
            })
public class XWindowAttributes extends Structure {
    /* location of window */
    public int x;
    public int y;

		/* width and height of window */
    public int width;
    public int height;

    public int border_width;		/* border width of window */
    public int depth;          	/* depth of window */
    public Pointer visual;		/* the associated visual structure */
    public NativeLong root;        	/* root of screen containing window */
    public int clazz;			/* InputOutput, InputOnly*/
    public int bit_gravity;		/* one of bit gravity values */
    public int win_gravity;		/* one of the window gravity values */
    public int backing_store;		/* NotUseful, WhenMapped, Always */
    public NativeLong backing_planes;/* planes to be preserved if possible */
    public NativeLong backing_pixel;/* value to be used when restoring planes */
    public boolean save_under;		/* boolean, should bits under be saved? */
    public NativeLong colormap;		/* color map to be associated with window */
    public boolean map_installed;		/* boolean, is color map currently installed*/
    public int map_state;		/* IsUnmapped, IsUnviewable, IsViewable */
    public NativeLong all_event_masks;	/* set of events all people have interest in*/
    public NativeLong your_event_mask;	/* my event mask */
    public NativeLong do_not_propagate_mask; /* set of events that should not propagate */
    public boolean override_redirect;	/* boolean value for override-redirect */
    public Pointer screen;		/* back pointer to correct screen */
}
