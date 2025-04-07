#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <pthread.h>
#include <time.h>

int sum=0;

void *somma(void* arg){
    int *a=(int *)arg;
    int j=4;
    for(int i=0; i<=j; i++){
        sum=sum+a[i];
    }
    return arg;
}

int main(int argc, char **argv){
    pthread_t threads[2];
    int a[]={1, 2, 3, 4, 5, 6, 7, 8, 9};
    pthread_create(&threads[0], NULL, somma, (void*)a);
    pthread_create(&threads[1], NULL, somma, (void*)(a+5));
    pthread_join(threads[0], NULL);
    pthread_join(threads[1], NULL);
    printf("%d\n", sum);
    return 0;
}