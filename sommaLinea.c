#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
    if (argc != 3) {
        printf("Usage: %s file_path line_number\n", argv[0]);
        return 1;
    }

    char *file_path = argv[1];
    int line_number = atoi(argv[2]);

    if (line_number < 1) {
        printf("Il numero della linea deve essere un intero maggiore o uguale a 1\n");
        return 1;
    }

    FILE *file = fopen(file_path, "r");
    if (file == NULL) {
        printf("Il file non esiste o non è leggibile\n");
        return 1;
    }

    char line[256];
    int i = 0;
    while (fgets(line, sizeof(line), file)) {
        i++;
        if (i == line_number) {
            break;
        }
    }

    if (i < line_number) {
        printf("Il file ha meno di %d linee\n", line_number);
        return 1;
    }

    int sum = 0;
    int num;
    char *ptr = line;
    while (sscanf(ptr, "%d", &num) == 1) {
        sum += num;
        while (*ptr != ' ' && *ptr != '\t' && *ptr != '\0') {
            ptr++;
        }
        while (*ptr == ' ' || *ptr == '\t') {
            ptr++;
        }
    }

    printf("La somma dei numeri nella linea %d è: %d\n", line_number, sum);

    fclose(file);
    return 0;
}
