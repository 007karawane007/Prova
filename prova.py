def input_arrey():
    return [int(x) for x in input().split(" ") if x]
def mergeSort(a, p, q): #Teta(n log n)
    if p<q:
        r=(p+q)//2
        mergeSort(a, p, r)
        mergeSort(a, r+1, q)
        merge(a, p, r, q)

def merge(a, p, r, q):
    


a=input_arrey()
print(a)
print(mergeSort(a,0, len(a)-1))