if ! test $# -eq 1
then
	echo "Inserire il nome del file"
fi
if ! test -f $1 -a -r $1
then
	echo "Il file non esiste o non è leggibile"
fi
nlinee=‘cat $1 | wc -l‘
i=1
sum = 0
while test i -le nlinee
do
	linea=‘cat $1 | head -n +$i | tail -1‘
	for n in ‘echo $linea‘
	do
		sum=$[$sum + $n]
	done
done
echo $sum
exit 0
