#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_SIZE 20

struct matrix
{
    int rows;
    int cols;
    int **data;
}matrix;

int **alloca_matrice(int rows, int cols){
    int **m=(int **)malloc(sizeof(int *)*rows);
    for(int i=0; i<rows; i++){
        m[i]=(int *)malloc(sizeof(int)*cols);
    }
    return m;
}

void libera_matrice(int rows, int cols, int **m){
    for(int i=0; i<rows; i++){
        free(m[i]);
    }
    free(m);
}

int main(int argc, char **argv){
    int righe, colonne, dato;
    printf("Inserisci numero di righe: \n");
    scanf("%d",&righe);
    printf("Inserisci numero di colonne: \n");
    scanf("%d",&colonne);
    if(righe<=MAX_SIZE && colonne<=MAX_SIZE){
        struct matrix m;
        m.rows = righe;
        m.cols = colonne;
        m.data = alloca_matrice(righe,colonne);
        for(int i=0; i<righe; i++){
            for(int j=0; j<colonne; j++){
                printf("Inserisci dato: \n");
                scanf("%d", &dato);
                m.data[i][j] = dato;
            }
        }
        for(int i=0; i<righe; i++){
            for(int j=0; j<colonne; j++){
                printf("%d", m.data[i][j]);
            }
            printf("\n");
        }
        printf("\n");
        libera_matrice(righe,colonne, m.data);
    }else{
        printf("nrighe o ncolonne > MAX_SIZE\n");
        return 0;
    }
}