/*
 * =====================================================================================
 *
 *       Filename:  udp_prototype.c
 *
 *    Description:  A test UDP file for the IP project
 *
 *        Version:  1.0
 *        Created:  09/30/2012 08:31:50 AM
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  YOUR NAME (Shu Zhang), 
 *   Organization:  
 *
 * =====================================================================================
 */

#include <sys/types.h>
#include <sys/socket.h>
#include <sys/select.h>
#include <netdb.h>
#include <stdlib.h>
#include <errno.h>
#include <stdio.h>
#include <string.h>
#include <memory.h>
#include <inttypes.h>
#include <pthread.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <unistd.h>
#include <signal.h>
#include <sys/stat.h>
#include <dirent.h>
#include <fcntl.h>
#include <sys/time.h>

fd_set fd_list, fd_list_temp;

void* receiving_thread_func(void* args){
    char* receive_buffer = malloc(2000);
    memset(receive_buffer, 0, 2000);
    struct addrinfo hints, *addr_res;
    memset(&hints, 0, sizeof(hints));
    hints.ai_family = AF_INET;
    hints.ai_socktype = SOCK_DGRAM;
    
    int rv = getaddrinfo("localhost", "8083", &hints, &addr_res);
    if(rv == -1){
        printf("Error in getting addrinfo()\n");
        exit(-1);
    }

    int sockfd = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP);
    if(sockfd == -1){
        printf("Error in socketing\n");
        exit(-1);
    }

    rv = bind(sockfd, addr_res->ai_addr, addr_res->ai_addrlen);
    if(rv == -1){
        printf("Error in binding\n");
        exit(-1);
    }
    
    struct sockaddr from;
    socklen_t fromlen = sizeof(from);

    int fd_max = sockfd;
    FD_ZERO(&fd_list);
    FD_ZERO(&fd_list_temp);
    FD_SET(sockfd, &fd_list);
    FD_SET(sockfd, &fd_list_temp);
    struct timeval tv;
    tv.tv_sec = 0;
    tv.tv_usec = 0;
    int bytes_count;
    while(1){
     /*    bytes_count = recvfrom(sockfd, receive_buffer, 2000, 0, &from, &fromlen);
        if(bytes_count == -1)
            printf("Error in receiving UDP package\n");
        printf("%d bytes received\n", bytes_count);
     */
        fd_list_temp = fd_list;
        rv = select(fd_max + 1, &fd_list_temp, NULL, NULL, &tv);
        if(rv == -1){
            printf("Selecting error.\n");
            exit(-1);
        }
        int i;
        for(i = 0; i <= fd_max; i ++ ){
            if(FD_ISSET(i, &fd_list_temp)){
                int sock_select = i;
                bytes_count = recvfrom(sock_select, receive_buffer, 2000,0,&from,&fromlen);
                if(bytes_count == -1)
                    printf("Error in receiving UDP package\n");
                printf("%d bytes received\n", bytes_count);
           
            }
        }
     }
    
}

void* sending_thread_func(void* args){
    int sockfd = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP);
    //send to a hard-coded ip address.
    struct addrinfo hints;
    memset(&hints,0, sizeof(hints));
    hints.ai_family = AF_INET;
    hints.ai_socktype = SOCK_DGRAM;
    struct addrinfo *addr_res;
    //port: 8083
    int rv = getaddrinfo("localhost", "8083", &hints, &addr_res);
    if(rv == -1){
        printf("Error in getaddrinfo()\n");
        exit(-1);
    }
    char* data = (char*)malloc(1024);
    memset(data,'a', 1024);
    data[1023] = '\0';
    int bytes_send = sendto(sockfd,data, 1024, 0,  addr_res->ai_addr, addr_res->ai_addrlen);
    if(bytes_send == -1){
        printf("UDP sending error\n");
        exit(-1);
    }
    return;
}


int main(){
 
    //TODO: establish a UDP socket..
    pthread_t sending_thread;
    pthread_t receiving_thread;
    pthread_attr_t attr;
    pthread_attr_init(&attr);
   
    pthread_create(&receiving_thread, &attr, receiving_thread_func, NULL);
    sleep(2);
    pthread_create(&sending_thread, &attr,sending_thread_func,NULL);
   
    pthread_join(sending_thread, 0);

    return 0;
}



