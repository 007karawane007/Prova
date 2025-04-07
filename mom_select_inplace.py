def input_arrey():
    return [int(x) for x in input().split(" ") if x]

def select(a, p, r, i):
    while ((r-p+1) % 5 != 0):
        for j in range(p+1, r+1):
            if a[p]>a[j]:
                a[p], a[j] = a[j], a[p]
        if i==1:
            return a[p]
        p=p+1
        i=i-1
    g=(r-p+1)//5
    for j in range(p, p+g):
        a=sorted([a[j], a[j+g], a[j+2*g], a[j+3*g], a[j+4*g]])
    x=select(a, p+2*g, p+3*g-1, g//2 + 1)
    q=partition(a, p, r, x)
    k=q-p+1
    if i== k:
        return a[q]
    elif i<k:
        return select(a, p, q-1, i)
    else:
        return select(a, q+1, r, i-k)
    
def partition(a,low,high,m):
    r=0
    for i in range(low,high):
        if a[i] == m:
            r = i
    a[r], a[high-1] = a[high-1], a[r]
    p = a[high-1]
    i = low
    for j in range(low,high-1):
        if a[j] <= p:
            #scambio a[i] con a[j] e incremento i
            a[i], a[j] = a[j], a[i]
            i += 1
    a[i], a[high-1] = a[high-1], a[i]
    return i
    
a=input_arrey()
i=int(input())
print(select(a, 0, len(a), i))