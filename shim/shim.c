
#include <X11/Xlib.h>

int
get_default_screen(Display *dpy) {
  return DefaultScreen(dpy);
}

int
get_display_width(Display *dpy, int scr) {
  return ScreenOfDisplay(dpy, scr)->width;
}

int
get_display_height(Display *dpy, int scr) {
  return ScreenOfDisplay(dpy, scr)->height;
}

Window
get_root_window(Display *dpy, int scr) {
  return RootWindow(dpy, scr);
}

int get_default_depth(Display *dpy, int scr) { return DefaultDepth(dpy, scr); }

int
call_error_handler(XErrorHandler handler,  Display *dpy, XErrorEvent *event) {
  return handler(dpy, event);
}

int
set_window_attributes(Display *dpy, Window w, long valuemask, long event_mask, Cursor cur) {
  XSetWindowAttributes wa;
  wa.cursor = cur;
  wa.event_mask = event_mask;
  return XChangeWindowAttributes(dpy, w, valuemask, &wa);
}
