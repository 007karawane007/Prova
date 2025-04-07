#idea: Sposto a destra ciò che sta in a[1...i-1] ed è più grande di k utilizzando un indice j, inserisco k a destra di j
insertionSort(a){ #Teta(n^2) nel caso peggiore, Teta(n) caso migliore (vettore già ordinato) 
    for(i=2 to a.length){
        k=a[i]
        j=i-1
        while(j>0 && a[j]<k){
            a[j+1]=a[j]
            j--
        }
        a[j+1]=k
    }
}

#idea: se a[p...q] ha un solo elemento è già ordinato altrimenti faccio la chiamata ricorsiva sulle due porzioni di vettore
mergeSort(a, p, q){ #Teta(n log n)
    if(p<q){
        r=(p+q)//2
        mergeSort(a, p, r)
        mergeSort(a, r+1, q)
        merge(a, p, r, q)
    }
}
merge(a, p, r, q){ #Teta(n)
    c=p
    b[]=new int[q+1]
    for(i=p, j=r+1; i<=r && j<=q; c++){
        if(v[i]<=v[j]){
            b[c]=v[i++]
        }else{
            b[c]=v[j++]
        }
    }
    while(i<=r){
        b[c++]=v[i++]
    }
    while(j<=q){
        b[c++]=v[j++]
    }
    for(i=p; i<=q; i++){
        v[i]=b[i]
    }
}

#idea: se y=x ritorna n, se y<x cerca r+1 e q, se y>x cerca p e r-1
binarySearch(a, p, q, x){ #Teta(n)
    if(p==q){
        if(a[p]==x){
            return p
        }else{
            return -1
        }
    }else{
        r=(p+q)//2
        if(a[r]==x){
            return r
        }else if(a[r]>x){
            return binarySearch(a, p, r-1, x)
        }else{
            return binarySearch(a, r+1, q, x)
        }
    }
}

#MaxHeap=albero binario quasi completo in cui ogni chiave ha valore <= del genitore
parent(x){
    return x//2
}
left(x){
    return 2*x
}
right(x){
    return (2*x)+1
}
#idea: inserisco in fondo e confronto/scambio con il genitore
maxheapImplement(h, k){ #O(log n) nel caso peggiore, Teta(1) caso migliore
    if(h.heapsize < h.length){
        h.heapsize++
        h[h.heapsize]=k
        i=h.heapsize
        while(i>1 && h[i]>h[parent(i)]){
            swap(h, i, parent(i))
            i=parent(i)
        }
    }
}
heapify(h, i){ #O(log s)
    l=left(i)
    r=right(i)
    if(l<=h.heapsize && h[l]>h[i]){
        m=l
    }else{
        m=i
    }
    if(r<=h.heapsize && h[r]>h[m]){
        m=r
    }
    if(i!=m){
        swap(h, i, m)
        heapify(h, m)
    }
}
#idea: cancello e restituisco la chiave nella radice la sostituisco con l'ultima chiave inserita cancellando il nodo e correggo l'albero dall'alto verso il basso
maxheapExtreat(h){ #O(log n)
    if(h.heapsize>0){
        k=h[1]
        swap(h, 1, h.heapsize)
        h.heapsize--
        heapify(h, 1)
        return k
    }
}
#idea: richiamo heapify dal basso verso l'alto da metà vettore
buildHeap(a){ #Teta(n)
    a.heapsize=a.length
    for(i=a.heapsize//2; i>1; i--){
        heapify(a, i)
    }
}
#idea: trasformo il vettore in una MaxHeap, scambio la radice con l'ultimo elemento e accorcio heapsize e correggo
heapSort(a){ #Teta(n log n) caso peggiore, Teta(n) caso migliore con tutti gli elementi uguali
    buildHeap(a)
    for(i=a.length; i>2; i--){
        swap(a, 1, i)
        a.heapsize--
        heapify(a, 1)
    }
}

#idea: prendo un elemento pivotale e metto tutti gli elementi minori a sinistra e a destra quelli maggiori e faccio la chiamata ricorsiva a dx e sx
quickSort(a, p, q){ #Teta(n log n) caso medio, Teta(n^2) caso peggiore vettore ordinato crescente/decrescente
    if(p<q){
        r=partiton(a, p, q)
        quickSort(a, p, r-1)
        quickSort(a, r+1, q)
    }
}
partition(a, p, q){ #Teta(n)
    x=a[q]
    i=p-1
    for(j=p to q){
        if(a[j]<=x){
            i++
            swap(a, i, j)
        }
    }
    return i
}

#idea:conto le occorrenze di p, se compare almeno n/2 volte concludo altrimenti decremento le non occorrenze di p
pmc(a){ #Teta(n)
    c=0
    for(i=1, to a.length){
        if(c==0){
            m=a[i]
            c++
        }else{
            if(a[i]==m){
                c++
            }else{
                c--
            }
        }
    }
    return m
}

#idea:scandisco A, conto in C[i] quante volte i compare in A, copio in B scandendo A usando C
countingSort(a, k, b){ #Teta(n) se k£O(n), Teta(n+k)
    c = new int[k+1]
    for(i=0 to k){
        c[i]=0
    }
    for(j=1 to a.length){
        c[a[j]]++
    }
    for(i=1 to k){
        c[i]=c[i-1]+c[i]
    }
    for(j=a.length to 1){
        b[c[a[j]]]=a[j]
        c[a[j]]--
    }
}

#idea: divido gli elementi di A in blocchi da 5 elementi, ordino ogni blocco, per ogni blocco prendo il mediano e lo copio in B, cerco il mediano dei mediani, richiamo partition con perno m
select(a, p, q, i){
    if(p==q==1){
        return a[p]
    }else{
        m=cercamediano(a, p, q)
        r=partition(a, p, q, m)
        if(i==r) return a[r]
        if(i<r) return select(a, p, r-1, i)
        if(i>r) return select(a, r+1, q, i)
    }
}

hash_open_add_insert(T, x){ #Teta(m)
    i=0
    j=h(x.key, 0)
    while(i<m && T[j]!=null && T[j]!=del){
        i++
        j=h(x.key, i)
    }
    if(i==m){
        return exception
    }else{
        T[j]=x
        return j
    }
}

#BST alberi binari di ricerca, ogni nodo x £ T ogni nodo y £ x.left y.key < x.key, ogni nodo x £ T ogni nodo y £ x.right y.key > x.key
preOrder(x){ #O(n)
    if(x!=null){
        print(x)
        preOrder(x.left)
        preOrder(x.right)
    }
}
postOrder(x){ #O(n)
    if(x!=null){
        postOrder(x.left)
        postOrder(x.right)
        print(x)
    }
}
inOrder(x){ #O(n)
    if(x!=null){
        inOrder(x.left)
        print(x)
        inOrder(x.right)
    }
}
search_node(x, k){ #O(h), caso peggiore Teta(h)=Teta(n)
    if(x.key == k || x==null){
        return x
    }else if(k<x.key){
        return search_node(x.left, k)
    }else{
        return search_node(x.right, k)
    }
}
sucessor(x){
    if(x.right!=null){
        return min_node(x.right)
    }else{
        y=x.parent
        while(y!=null && x==y.right){
            x=y
            y=x.parent
        }
        return y
    }
}
#idea: scendo in base a k con due puntatori, quando x arriva a null aggiungo z
BST_insert(T, z){ #Teta(h)=Teta(n)
    x=T.root
    y=null
    while(x!=null){
        y=x
        if(z.key<x.key){
            x=x.left
        }else{
            x=x.right
        }
    }
    if(y==null){
        T.root=z
        return T
    }else{
        z.parent=y
        if(z.key<y.key){
            y.left=z
        }else{
            y.right=z
        }
    }
    return T
}
#idea: se z è foglia elimino z, se z ha un figlio null aggancio il figlio non null al genitore, se z ha due figli non null trovo sucessore di z cancello s e scrivo k' al posto di k
BST_delite(T, z){
    if(z.left == null || z.right==null){
        x=z
    }else{
        x=z.BST.sucessore(z)
    }
    if(x.left!=null){
        v=x.left
    }else{
        v=x.right
    }
    if(v!=null){
        v.parent=x.parent
    }
    if(x.parent!=null){
        if(x==x.parent.left){
            x.parent.left=v
        }else{
            x.parent.right=v
        }
    }else{
        T.root=v
    }
    if(x!=z){
        z.key=x.key
    }
    return T
}

#BTree
Btree_search(x, k){ #Teta(logt n) Read/Write, Teta(logt n) CPU
    i=1
    while(i<=x.n && x.key[i]<k){
        i++
    }
    if(i<=x.n && x.key[i]==k){
        return(x, i)
    }else if(x.leaf()){
        return null
    }else{
        y=discRead(x, i)
        Btree_search(y, k)
    }
}
split(x, y, i, t){
    z=new Nodo(t)
    z.leaf=y.leaf
    for(j=1 to t-1){
        z.key[j]=y.key[t+1]
    }
    if(!y.leaf){
       for(j=1 to t){
           z.c[j]=y.c[t+j]
       }
    }
    y.n=t-1
    z.n=t-1
    for(j=x.n down to i){
        x.key[j+1]=y.key[j]
    }
    x.key[i]=y.key[t]
    for(j=x.n+1 down to i+1){
        x.c[j+1]=x.c[j]
    }
    x.c[i+1]=z
    x.n=x.n+1
    diskWrite(x)
    diskWrite(y)
    diskWrite(z)
}
split_root(T, r, t){ #Teta(1) R/W, O(t) CPU
    s=new Nodo(t)
    s.n=0
    s.c[1]=r
    split(s, r, 1, t)
    T.root=s
}
Btree_insert(T, t, k){ #Teta(logt n) R/W, O(t * logt n) CPU
    r=diskRead(T.root)
    if(r.n==2t-1){
        split_root(r, t)
    }
    r=diskRead(T.root)
    Btree_insertnotfull(r, t, k)
}
Btree_insertnotfull(x, t, k){
    if(x.leaf){
        j=x.n
        while(x.key > k){
            x.key[j+1]=x.key[j]
            j--
        }
        x.key[j+1]=k
        x.n=x.n+1
        diskWrite(x)
    }else{
        j=1
        while(j<=x.n && x.key[j]<k){
            j++
        }
        y=diskRead(x.c[j])
        if(y.n<2t-1){
            Btree_insertnotfull(y, t, k)
        }else{
            split(x, y, j, t)
            if(k<x.key[j]){
                Btree_insertnotfull(y, t, k)
            }else{
                z=diskRead(x.c[j+1])
                Btree_insertnotfull(z, t, k)
            }
        }
    }
}

#Insiemi disgiunti liste
make(x){ #Teta(1)
    x.next=null
    x.rap=x
    x.last=x
    x.length=1
    return x
}
find(x){ #Teta(1)
    return x.rap
}
union(x, y){} #Teta(|x| + |y|)
#costo di m m.u.f. di cui n make O(m + n^2) senza euristiche
link(r, s){
    r.length=r.length+s.length
    r.last.next=s
    r.last=s.last
    while(s.next!=null){
        s.rap=r
        s=s.next
    }
}
union(x, y){ #Waighted Union Teta(n)
    z=find(x)
    v=find(y)
    if(z!=v){
        if(z.length>v.length){
            link(z, v)
            return z
        }else{
            link(v, z)
            return v
        }
    }
}
#costo di m m.u.f. di cui n make O(m + n log n) con waighted union
#Insiemi disgiunti alberi
make(x){ #Teta(1)
    x.parent=x
    return x
}
find(x){ #O(n)
    if(x==x.parent){
        return x
    }else{
        return find(x.parent)
    }
}
union(x, y){ #O(n)
    z=find(x)
    v=find(y)
    if(z!=v){
        v.parent=z
    }
}
#costo di m m.u.f. di cui n make O(n * m) senza euristiche
make(x){ #Teta(1)
    x.parent=x
    x.rank=0
    return x
}
find(x){ #O(log n)
    if(x==x.parent){
        return x
    }else{
        return find(x.parent)
    }
}
union(x, y){ #O(log n) Union by rank
    z=find(x)
    v=find(y)
    if(z!=v){
        if(z.rank>v.rank){
            v.parent=z
            return z
        }else{
            z.parent=v
            if(v.rank==z.rank){
                v.rank++
                return v
            }
        }
    }else{
        return z
    }
}
#costo di m m.u.f. di cui n make O(m log n) con Union by Rank
make(x){ #Teta(1)
    x.parent=x
    x.rank=0
    return x
}
find(x){ #O(log n) Path Compression
    if(x==x.parent){
        return x
    }else{
        x.parent=find(x.parent)
    }
}
union(x, y){ #O(log n) Union by rank
    z=find(x)
    v=find(y)
    if(z!=v){
        if(z.rank>v.rank){
            v.parent=z
            return z
        }else{
            z.parent=v
            if(v.rank==z.rank){
                v.rank++
                return v
            }
        }
    }else{
        return z
    }
}
#costo di m m.u.f. di cui n make O(m * alpha(n, m)) con Union by Rank e Path Compression

#Grafi
BFS(G, s){ #O(|V|^2) con matrici di adiacenza, O(|V| + |E|) con liste di adiacenza
    foreach(v£V){
            color[v]=bianco
            d[v]=+inf
            pi[v]=null
    }
    Q=emptyQueue()
    color[s]=grigio
    d[s]=0
    EnQueue(Q, s)
    while(Q!=null){
        u=head(Q)
        foreach(v£Adj[u]){
                if(color[v]==bianco){
                    color[v]=grigio
                    pi[v]=u
                    d[v]=1+d[u]
                    EnQueue(Q, v)
                }
        }
        Dequeue(Q)
        color[u]=nero
    }
}

DFS(G){
    foreach(v£V){
            color[v]=bianco
            pi[v]=null
    }
    time=0
    foreach(v£V){
            if(color[v]==bianco){
                DFS_visit(G, v)
            }
    }
}
DFS_visit(G, v){ #O(|V|^2) con matrici di adiacenza, O(|V| + |E|) con liste di adiacenza
    color[v]=grigio
    time++
    i[v]=time
    foreach(u£Adj[v]){
            if(color[u]==bianco){
                pi[u]=v
                DFS_visit(G, u)
            }
    }
    time++
    f[v]=time
    color[v]=nero
}

Kruskal(G){ #Teta(|E| log |V|)
    sort(E)
    A=vuoto
    foreach(v£V){
            make(v)
    }
    foreach({u, v}£E){
            if(find(u)!=find(v)){
                A=Au{u, v}
                union(u, v)
            }
    }
    return A
}

Prim(G, r){ #Teta(|E| log |V|) con minHeap, Teta(|V|^2) senza
    foreach(v£V){
            pi[v]=null
            key[v]=+inf
    }
    key[r]=0
    Q=V
    buildMinHeap(Q, key)
    while(Q!=0){
        u=Extractmin(Q)
        foreach(v£Adj[u]){
                if(v£Q and key[v]>W({u, v})){
                    pi[v]=u
                    key[v]=W({u, v})
                    DecriseKey(Q, v)
                }
        }
    }
}

Dijktra(G, s){ #Teta(|E| log |V|) con minHeap, Teta(|V|^2) senza
    foreach(v£V){
            pi[v]=null
            key[v]=+inf
    }
    d[s]=0
    Q=V
    buildMinHeap(Q, key)
    while(Q!=0){
        u=Extractmin(Q)
        foreach(v£Adj[u]){
                if(d[u]+W({u, v})>d[v]){
                    pi[v]=u
                    key[v]=W({u, v})
                    DecriseKey(Q, v)
                }
        }
    }
}

FloydWarshall(G){ #Teta(|V|^3)
    D=Wg
    for(k=1 to |V|){
        for(i=1 to |V|){
            for(j=1 to |V|){
                D[i, j]=min(D[i, j], D[i, k]+D[k, j])
            }
        }
    }
}