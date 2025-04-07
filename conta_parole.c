#include <stdio.h>

int main(){
	int c = getchar();
	int i = 0;
	int k = c;
	while(c != EOF){
		if(c == ' ' && k != ' '){
			i++;
		}else if(c == '\n' && k != '\n'){
			i++;
		}
		k = c;
		c = getchar();
	}
	printf("%d\n", i);
	return 0;
}
