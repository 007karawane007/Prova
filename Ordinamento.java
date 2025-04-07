public class Ordinamento{
    public static void main (String []arg){
        int v[]={1, 3, 2, 4, 7, 5, 6};
        //InsertionSort(v);
        MergeSort(v, 0, v.length-1);
        for(int i=0; i<v.length; i++){
            System.out.println(v[i] + " ");
        }
    }
    private static void InsertionSort(int []v){
        for(int i=1; i<v.length; i++){
            int k=v[i];
            int j=i-1;
            while(j>0 && v[j]>k){
                v[j+1]=v[j];
                j--;
            }
            v[j+1]=k;
        }
    }
    private static void MergeSort(int []v, int p, int q){
        if(p<q){
            int r=(p+q)/2;
            MergeSort(v, p, r);
            MergeSort(v, r+1, q);
            Merge(v, p, r, q);
        }
    }
    private static void Merge(int []v, int p, int r, int q){
        int i, j,c=p;
        int b[]=new int[q+1];
        for(i = p,j = r+1; i<=r && j<=q; c++){
            if(v[i] <= v[j])
                b[c] = v[i++];
            else
                b[c] = v[j++];
        }
        while(i <= r )    
            b[c++] = v[i++];

        while(j<=q)   
            b[c++] = v[j++];
            
        for(i = p ; i <= q; i++)
            v[i] = b[i]; 
    }
}