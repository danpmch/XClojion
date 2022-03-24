# XClojion

An X11 window manager written (mostly) in Clojure. That description is aspirational at this point,
currently it's a very partial reimplementation of [dwm](https://dwm.suckless.org/) that can make a
handful of the XLib calls required to register the WM with the X server and that's it.

It depends on the XLib native C library, and calling out to it from Clojure requires some supporting
C and Java code. The java code lives in a conventional `src/java` directory. The C code lives in the
`shim/` directory, and `make install` copies the library over into `resources/linux-x86-64` where it
can be picked up by JNA. It's highly likely that the build process is not portable to other
machines/distros than I'm currently using.


## License

Copyright © 2022

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
