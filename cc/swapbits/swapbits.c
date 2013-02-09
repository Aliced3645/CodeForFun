/*
 * =====================================================================================
 *
 *       Filename:  swapbits.c
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  02/08/2013 10:37:20 AM
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  YOUR NAME (), 
 *   Organization:  
 *
 * =====================================================================================
 */

#include <stdio.h>

//swap odd bits and even bits

int swapbits(int toSwap){
    //get two masks
    int mask1 = 0x55555555;
    int mask2 = 0xAAAAAAAA;

    return (toSwap & mask1) << 1 | (toSwap & mask2) >> 1;
}

int main(){
    
    printf("%d\n", swapbits(8));

    return 0;
}

