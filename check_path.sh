PATH="/bin:/usr/local/bin"
if test -n '$1'
then
	if test PATH = '$1'
	then
		echo "OK"
	else if echo :$PATH: | grep ":$1:"
		then
			echo "OK"
	fi
	fi
fi
