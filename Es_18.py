def input_tree(tokens, pos):
    assert len(tokens) > pos
    tok = tokens[pos]
    if tok == "NULL":
        return (None, pos+1)
    value = int(tok)
    (left, newpos) = input_tree(tokens, pos+1)
    (right, newnewpos) = input_tree(tokens, newpos)
    root = Node(value, left, right)
    return (root, newnewpos)
    
class Node:
    value =  None
    parent = None
    left = None
    right = None

    def __init__(self, value, left, right):
        self.value = value
        self.left=left
        if left != None:
            self.parent=self
        self.right=right
        if right != None:
            self.parent=self
        self.parent = None

def check_BST(T, low, high):
    if T == None:
        return 1
    if T.value <= low:
        return 0
    if T.value >= high:
        return 0
    return check_BST(T.left, low, T.value) and check_BST(T.right, T.value, high)
            
            

        

tokens = input().split(" ")
(root, _) = input_tree(tokens, 0)
print(check_BST(root, float("-inf"), float("+inf")))