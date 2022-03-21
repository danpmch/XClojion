
objs:
	gcc -c -o wmshims.o shim.c

lib: objs
	gcc -shared -o libwmshims.so wmshims.o

clean:
	rm -f libwmshims.so wmshims.o

install:
	cp libwmshims.so ../resources/linux-x86-64/libwmshims.so

