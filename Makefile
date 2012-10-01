#CC = gcc
#DEBUGFLAGS = -g -Wall
#CFLAGS = -D_REENTRANT -D_XOPEN_SOURCE=500 -lpthread
all:
	gcc -o udp_prototype udp_prototype.c -lpthread
#all: udp_prototype

#udp_prototype : udp_prototype.c
