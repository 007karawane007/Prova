#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <pthread.h>
#include <time.h>
#include <unistd.h>

long accumulatore = 0;
int soglia = 0;
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;

void *dice(void *arg){
    while (1){
        if(accumulatore>soglia){
            printf("%ld\n", accumulatore);
            return NULL;
        }
        sleep(5);
        srandom(time(NULL));
        long r = random()%6;
        printf("r=%ld\n", r);
        pthread_mutex_lock(&mutex);
        accumulatore = accumulatore + r;
        pthread_mutex_unlock(&mutex);
    }
}

int main(int argc, char **argv){
    int nthread;
    printf("Inserisci numero di thread\n");
    scanf("%d", &nthread);
    printf("Inserisci la soglia\n");
    scanf("%d", &soglia);
    pthread_t threads[nthread];
    for(int i = 0; i < nthread; i++){
        pthread_create(&threads[i], NULL, dice, NULL);
    }
    for(int i = 0; i < nthread; i++){
        pthread_join(threads[i], NULL);
    }
}