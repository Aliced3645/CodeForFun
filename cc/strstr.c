/*
 * =====================================================================================
 *
 *       Filename:  strstr.c
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  02/08/13 00:33:52
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  YOUR NAME (), 
 *   Organization:  
 *
 * =====================================================================================
 */


//implement strstr in C with brute force
//

#define NULL 0
#include <stdio.h>


//dont need to explicitly compare the first char!!

int strStr(const char* haystack, const char* needle){
    
    //traverse from haystack
    if(needle == NULL || haystack == NULL)
        return -1;

    int haystackPointer = 0;

    while(haystack[haystackPointer] != '\0'){
        
        int tempHayPointer = haystackPointer;
        int needlePointer = 0;
        while(needle[needlePointer] != '\0' && haystack[tempHayPointer] != '\0' && needle[needlePointer] == haystack[tempHayPointer]){
                    needlePointer ++;
                    tempHayPointer ++;
        }

        if(needle[needlePointer] == '\0'){ // full match happens
               return haystackPointer;
        }

        haystackPointer ++;
    }

    return -1;
}


int main(){

    const char* haystack = "iloveyou";
    const char* needle = "love";

    printf("haystack: %s, needle: %s, result: %d", haystack, needle, strStr(haystack, needle));

    return 0;
}
