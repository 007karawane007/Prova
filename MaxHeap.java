public class MaxHeap{
    /*private int length;
    private int heapsize;*/
    private int h[];

    public MaxHeap(int maxdim){
        h = new int[2^maxdim];
        length = v.length;
        heapsize = v.heapsize;
    }

    private int left(int k){
        return 2*k;
    }

    private int right(int k){
        return 2*k+1;
    }

    private int parent(int k){
        return k/2;
    }

    public void swap(int[] v, int i, int j){
        int temp = v[i];
        v[i] = v[j];
        v[j] = temp;
    }

    public void MaxHeapImplement(int k){
        if(h.heapsize < h.length){
            h.heapsize++;
            h[h.heapsize] = k;
            i = h.heapsize;
            while(i>1 && h[i]>h[parent(i)]){
                swap(h, i, parent(i));
            }
        }
    }

    public void Heapify(int i){
        int l = left(i);
        int r = right(i);
        int m = 0;
        if(l<=h.heapsize && h[l]>h[i]){
            m = l;
        }else{
            m = i;
        }
        if(r<=h.heapsize && h[r]>h[m]){
            m = r;
        }
        if(i != m){
            swap(h, i, m);
            Heapify(h, m);
        }
    }

    public int MaxHeapExtreat(){
        int k = 0;
        if(h.heapsize > 0){
            k = h[0];
            swap(h, 1, h.heapsize);
            h.heapsize--;
            Heapify(1);
            return k;
        }
    }
}