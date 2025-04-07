#include <stdio.h>
#define SIZE 5

void reverse(int *, int);
void stampaVettore(int *, int);
void sort(int *, int);
void qsort(int *, int, int, int);
int partition(int *, int, int, int);
void swap(int *, int, int, int);

int main(){
	int v[SIZE];
	int t[SIZE];
	printf("Inserisci elementi vettore: ");
	for(int i=0; i<SIZE; i++){
		scanf("%d", &v[i]);
	}
	reverse(v, SIZE);
	stampaVettore(v, SIZE);
	for(int x=0; x<SIZE; x++){
		t[x]=v[x];
	}
	sort(v, SIZE);
	stampaVettore(v, SIZE);
	qsort(t, SIZE, 0, SIZE-1);
	stampaVettore(t, SIZE);
}

void stampaVettore(int *v, int dim){
	for(int i=0; i<dim; i++){
		printf("%d ", v[i]);
	}
	printf("\n");
}

void reverse(int *v, int dim){
	int i=0;
	int j=dim-1;
	int a[dim];
	while(i<dim && j>=0){
		a[i]=v[j];
		i++;
		j--;
	}
	for(int k=0; k<dim; k++){
		v[k]=a[k];
	}
}

void sort(int *v, int dim){
	int k, j;
	for(int i=1; i<dim; i++){
		k=v[i];
		j=i-1;
		while(j>=0 && v[j]>k){
			v[j+1]=v[j];
			j--;
		}
		v[j+1]=k;
	}
}

void qsort(int *v, int dim, int p, int q){
	int r;
	if(p < q){
		r=partition(v, dim, p, q);
		printf("r= %d\n", r);
		qsort(v, dim, p, r-1);
		qsort(v, dim, r+1, q);
	}
}

int partition(int *v, int dim, int p, int q){
	int x=v[q];
	int i=p-1;
	for(int j=p; j<q-1; j++){
		if(v[j]<=x){
			i++;
			swap(v, dim, i, j);
		}
	}
	return i;
}

void swap(int *v, int dim, int i, int j){
	int temp=v[i];
	v[i]=v[j];
	v[j]=temp;
}
