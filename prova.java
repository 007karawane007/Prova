public class prova{
    public static void main (String arc[]){
        int []a={5, 4, 3, 2, 1};
        mergeSort(a, 0, a.length-1);
        for(int i=0; i<a.length; i++){
            System.out.print(a[i]);
        }
    }
    public static void mergeSort(int a[], int p, int q){
        int r;
        if(p<q){
            r=(p+q)/2;
            mergeSort(a, p, r);
            mergeSort(a, r+1, q);
            merge(a, p, r, q);
        }
    }
    public static void merge(int a[], int p, int r, int q){
        int i, j, c=p;
        int b[]=new int[q+1];
        for(i=p, j=r+1; i<=r && j<=q; c++){
            if(a[i]<=a[j]){
                b[c]=a[i++];
            }else{
                b[c]=a[j++];
            }
        }
        while(i<=r){
            b[c++]=a[i++];
        }
        while(j<=q){
            b[c++]=a[j++];
        }
        for(i=p; i<=q; i++){
            a[i]=b[i];
        }
    }
}