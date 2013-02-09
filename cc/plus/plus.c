/*
 * =====================================================================================
 *
 *       Filename:  plus.c
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  02/08/2013 02:40:13 PM
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  YOUR NAME (), 
 *   Organization:  
 *
 * =====================================================================================
 */

#include <stdio.h>


int main(){

    volatile int i = 5;
    printf("%d\n",i++ + ++i);
    printf("%d\n",i++ + ++i + i++ + i++);
    printf("%d\n",++i + i++ + ++i + i++);
    return 0;
}
