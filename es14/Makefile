all: file_exec1

file_exec1: file_exec1.o freadline.o
	clang file_exec1.o freadline.o -o file_exec1

%.o: %.c freadline.h
	clang -Wall -c -g $<

clean:
	rm -f file_exec1 *.o 
