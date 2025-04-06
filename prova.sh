#!/bin/bash

# Controllo del numero di argomenti
if test "$#" -ne 2 ; then
    echo "Usage: $0 file_path line_number"
    exit 1
fi

file_path=$1
line_number=$2
n_linee=`cat $file_path | wc -l`

# Controllo se il file esiste e se è leggibile
if  ! test -f "$file_path" -a  -r "$file_path" ; then
    echo "Il file non esiste o non è leggibile"
    exit 1
fi

# Controllo se il numero della linea è un intero maggiore o uguale a 1
if ! test "$line_number" -ge 1 -a "$line_number" -lt "$n_linee"; then
    echo "Il numero della linea deve essere un intero maggiore o uguale a 1 $n_linee"
    exit 1
fi

# Estrazione della linea specificata dal file
line=`cat $file_path | head -n $line_number | tail -1`

# Calcolo della somma dei numeri nella linea
sum=0
for num in $line; do
    sum=$((sum + num))
done

echo "La somma dei numeri nella linea $line_number è: $sum"
