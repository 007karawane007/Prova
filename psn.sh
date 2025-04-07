while true
do
	nprocessi=$(ps -e --no-headers | wc -l)
	echo "Numero processi: $nprocessi"
	if test -f "stop"
	then
		break
		exit 0
	else
		sleep 5
	fi
done
