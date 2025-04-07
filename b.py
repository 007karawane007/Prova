import time
import math
import random
import matplotlib.pyplot as plt

#####################################################################################################################################################################
#####################################################################################################################################################################
#       MEDIANS   
#####################################################################################################################################################################
#####################################################################################################################################################################

def medianOfMedians(array, p, q):
    quantita_intervalli = int((q-p+1)/5)

    if quantita_intervalli <= 1:
        array[p:q+1] = sorted(array[p:q+1])
        return p+int((q-p+1)/2)

    for i in range(0, quantita_intervalli, 1):
        array[i*5:(i*5)+5] = sorted(array[i*5:(i*5)+5])
        array[i], array[(i*5)+2] = array[(i*5)+2], array[i]

    if (q-p+1) % 5 != 0:
        array[(quantita_intervalli-1)*5:q+1] = sorted(array[(quantita_intervalli-1)*5:q+1])
        array[quantita_intervalli-1], array[((quantita_intervalli-1)*5)+int(((q+1)%5)/2)] = array[((quantita_intervalli-1)*5)+int(((q+1)%5)/2)], array[quantita_intervalli-1]

    return medianOfMedians(array, p, p+quantita_intervalli-1)


def select(array, p, q, k):
    if p > k-1 or q-1 < k-1:
        return -1
    if q-1 > p:
        pivot = medianOfMedians(array, p, q-1)
        r = partition1(array, p, q-1, pivot)   
            
        if r > k - 1:
            return select(array, p, r, k)
        elif r < k-1:
            return select(array, r+1, q, k)
        else:
            return r
    elif q-1 == p:
        return q-1

def partition1(arr, low, high, p):
    arr[p], arr[high] = arr[high], arr[p]

    # Partiziona l'array attorno al pivot
    i = low
    pivot = arr[high]
    for j in range(low, high):
        if arr[j] <= pivot:
            arr[i], arr[j] = arr[j], arr[i]
            i += 1
    arr[i], arr[high] = arr[high], arr[i]
    return i

#####################################################################################################################################################################
#####################################################################################################################################################################
#       QUICKSELECT
#####################################################################################################################################################################
#####################################################################################################################################################################


def partition(a, low, high):
    p = a[high -1]
    i = low
    for j in range(low, high-1):  # high-1 perchè l'ultimo è il pivot
        if a[j] <= p:
            a[i] , a[j] = a[j] , a[i]
            i += 1
            
    # in questo momento il pivot si trova ancora alla fine
    # i rappresenta l'indice che separa i minori del pivot a[j] ed i maggiori
    a[i] , a[high-1] = a[high-1] , a[i]
    return i

# costo O(n) nel caso medio
# costo teta n^2 nel caso peggiore
        
def quickSelect(a, i, j, k):
    if i == j == k:
        return a[i]
    else:
        x = partition(a,i,j)
        if x == k:
            return a[x]
        if k<x:
            return quickSelect(a,i,x,k)
        elif k>x:
            return quickSelect(a,x,j,k)

#####################################################################################################################################################################
#####################################################################################################################################################################
#####################################################################################################################################################################
#   HEAP SELECT
#####################################################################################################################################################################
#####################################################################################################################################################################

def heapify(arr, i):
    heapsize = len(arr)
    m = i
    left   = (i * 2) + 1
    right  = (i * 2) + 2
    
    if left<heapsize and arr[left]<arr[m]:
        m = left
    if right<heapsize and arr[right]<arr[m]:
        m = right
    
    if i != m:
        arr[i],arr[m] = arr[m],arr[i]
        heapify(arr,m)
        
def build_heap(arr):
    heapsize = len(arr)-1
    for i in range(heapsize//2,-1,-1):
        heapify(arr,i)

def move_up(heap, i):
    if i == 0:
        return
    
    p = (i + 1) // 2 - 1
    if heap[i][0] < heap[p][0]:
        # Scambio gli elementi
        heap[i], heap[p] = heap[p], heap[i]
        move_up(heap, p)

def heapify1(arr, i):
    heapsize = len(arr)
    m = i
    left = (i * 2) + 1
    right = (i * 2) + 2

    if left < heapsize and arr[left][0] < arr[m][0]:
        m = left
    if right < heapsize and arr[right][0] < arr[m][0]:
        m = right

    if i != m:
        arr[i], arr[m] = arr[m], arr[i]
        heapify1(arr, m)

def heap_select(arr, i, j, k):
    build_heap(arr)
    h2 = [(arr[0],0)]

    for i in range(1, k):
        rootH2 = h2[0]

        left = rootH2[1] * 2 + 1
        right = rootH2[1] * 2 + 2
        
        if left < len(arr):
            h2.append((arr[left],left))
            move_up(h2, len(h2)-1)
            
        if right < len(arr):
            h2.append((arr[right],right))
            move_up(h2, len(h2)-1)
            
        h2[0], h2[-1] = h2[-1], h2[0]
        h2.pop()
        heapify1(h2,0)
        
    risultato = h2[0][0]
    return risultato

###################################################################################################
###################################################################################################
###################################################################################################

#risoluzione del clock
def resolution():
    start = time.monotonic() # salva il valore del tempo all'inizio
    while time.monotonic() == start:
        pass
    stop = time.monotonic() # salva il valore del tempo quando c'è una variazione da start
    return stop - start  # differenza tra il tempo all'inizio e alla fine del ciclo, 
                         # che rappresenta il tempo minimo 
                         # che può trascorrere tra due chiamate consecutive a time.monotonic()

def generate_input(n,maxv): # genera n numeri con valore massimo maxv
    a = [0] * n
    for i in range(n):
        a[i] = random.randint(0,maxv)
    return a

def generate_index(maxv): # genera un indice con valore massimo maxv
    return int(random.randint(0,maxv))

ris = [resolution() for i in range(100)]

def benchmark(n, maxv, func, resolution, max_rel_error=0.01):
    tmin = resolution * (1 + (1 / max_rel_error))
    count = 0
    start = time.monotonic()
    
    while time.monotonic() - start < tmin:
        a = generate_input(n, maxv)
        i = generate_index(n)
        count += 1

    init_duration = (time.monotonic() - start) / count
    count = 0
    start = time.monotonic()

    while time.monotonic() - start < tmin:
        a = generate_input(n, maxv)
        i = generate_index(n)
        
        func(a,0,len(a),i)
        
        count += 1
        
    initexec_duration = (time.monotonic() - start) / count
    return initexec_duration - init_duration

for i in range(0,10):
    print(resolution())

# 𝑇min=𝑅⋅(1/𝐸+1)
resolution_val = resolution()
nmin = 100
nmax = 100000
iters = 100 # numero di punti
base = 2 ** ((math.log2(nmax) - math.log2(nmin)) / (iters - 1))

points = [(None, None)] * iters  # vettore di coppie (n, tempo) con n dimensione dell'input 

for i in range(iters):
    print(f"\r{i}", end='')
    n = int(nmin * (base ** i))
    points[i] = (n, benchmark(n, 100, quickSelect, resolution_val),
                    benchmark(n, 100, heap_select, resolution_val),
                    benchmark(n, 100, select, resolution_val))

xs,ys1,ys2,ys3 = zip(*points)

plt.scatter(xs,ys1, label='quickselect')
plt.scatter(xs,ys2, label='heapselect')
plt.scatter(xs,ys3, label='medianOfMedians')
plt.xscale('log')
plt.yscale('log')
plt.title('confronto tempi di esecuzione')
plt.xlabel('dimensione input')
plt.ylabel('tempo di esecuzione medio (sec)')
plt.legend()

plt.show()
