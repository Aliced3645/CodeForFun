/*
 * =====================================================================================
 *
 *       Filename:  sizeof.c
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  01/31/13 13:18:01
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  YOUR NAME (), 
 *   Organization:  
 *
 * =====================================================================================
 */


#include <memory.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>


int main(){
    
    int b[] = {1,2,3};
    int* a = b;
    int* c = (int*)malloc(3);
    printf("a: %d, b: %d, c: %d\n", sizeof a, sizeof b,sizeof c);

    int fd = open("a.py", O_RDWR);
    printf("fd: %d\n", fd);
    sleep(100000);
    return 0;
}
