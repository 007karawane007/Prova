#include <string.h>
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv){
    if (argc < 2){
        perror("Inserire il Path del file\n");
    }
    FILE *f = fopen(argv[1], "r");
    if(!f){
        perror("Errore nell'apertura del file\n");
    }
    int somma=0;
    int x;
    while(fscanf(f, "%d", &x) != EOF){
        somma=somma+x;
    }
    printf("%d\n", somma);
    fclose(f);
}