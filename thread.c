#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include <signal.h>

long int buffer[4];
pthread_t thread1, thread2;
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;

void* leggi(void* arg){
    FILE *file = fopen("/dev/urandom", "rb");
    long int n;
    fscanf(file, "%ld", &n);
    for(int i=0; i<4; i++){
        if(buffer[i]==0){
            pthread_mutex_lock(&mutex);
            buffer[i]=n;
            pthread_mutex_unlock(&mutex);
        }
    }
    sleep(5);
}

void* stampa(void* arg){
    for(int i=0; i<4; i++){
        if(buffer[i]!=0){
            printf("%ld, ", buffer[i]);
        }
    }
    sleep(20);
}

void sigint(){
    pthread_join(thread1, NULL);
    pthread_join(thread2, NULL);
}

int main(){
    while(1){
        signal(SIGINT, sigint);
        pthread_create(&thread1, NULL, leggi, NULL);
        pthread_create(&thread2, NULL, stampa, NULL);
    }
}