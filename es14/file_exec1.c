#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>

#include "freadline.h"

int main(int argc, char **argv){
    if(argc < 3){
        perror("Numeri di argomenti insufficente\n");
    }
    char *filename = argv[1];
    char *cmd=argv[2];
    FILE *file = fopen(filename, "r");
    if(!file){
        perror("Impossibile aprire il file\n");
    }
    char *line = malloc(sizeof(char)*100);
    do{
        line = freadline(file, line);
        if(strlen(line) == 0)
            continue;
        pid_t pid = fork();
        switch (pid)
        {
        case -1:
            perror("Errore nella fork()\n");
            break;
        case 0:
            for(int j=0; j<argc; j++){
                if(strcmp(argv[j], "@") == 0){
                    argv[j] = line;
                }
            }
            int result = execvp(cmd, argv+2);
            if (result == -1){
                perror("Errore nella execvp\n");
            }
            exit(1);
        default:
            wait(NULL);
        }
    }while(line != NULL);
    fclose(file);
    free(line);
    free(cmd);
    free(filename);
    return 0;
}