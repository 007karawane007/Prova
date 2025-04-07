class Node(object):
	value = None
	parent = None
	left = None
	right = None
	string = None
	height = None

	def __init__(self, value, string, height, left, right):
		self.left = left
		if self.left != None: self.left.parent = self
		self.right = right
		if self.right != None: self.right.parent = self
		self.value = value
		self.parent = None
		self.height = height
		self.string = string

def insert(avl, k, s):
	if avl == None:
		avl = Node(k, s, 1, None, None)
	else:
		if avl.value > k:
			if avl.left == None:
				avl.left = Node(k, s, 1, None, None)
				avl.left.parent = avl
			else:
				avl.left = insert(avl.left, k, s)
		else:
			if avl.right == None:
				avl.right = Node(k, s, 1, None, None)
				avl.right.parent = avl
			else:
				avl.right = insert(avl.right, k, s)

		left = height_calculation(avl.left)
		right = height_calculation(avl.right)
		if abs(left-right) == 2:
			if left > right:
				avl = right_rotation(avl)

			else:
				avl = left_rotation(avl)

		avl.height = height_calculation(avl)

	return avl_correction(avl)

def avl_correction(avl):
	if avl.left != None:
		left = height_calculation(avl.left)
	else:
		left = 0
	if avl.right != None:
		right = height_calculation(avl.right)
	else:	
		right = 0
	if abs(left-right) == 2:
		if left > right:
			avl = right_rotation(avl)
		else:
			avl = left_rotation(avl)

	avl.height = height_calculation(avl)
	return avl

def right_rotation(avl):
	n = avl 
	p = avl.left
	x = p.right

	p.parent = n.parent
	n.left = x 
	n.parent = p 
	if x != None:
		x.parent = n
	p.right = n

	n.height = height_calculation(n)

	return p

def left_rotation(avl):
	n = avl 
	p = avl.right 
	x = avl.right.left

	p.parent = n.parent
	n.right = x
	n.parent = p 
	if x != None:
		x.parent = n
	p.left = n 

	n.height = height_calculation(n)

	return p
	

def height_calculation(avl):
	if avl == None:
		return 0
	else:
		if avl.left == None:
			left = 0
		else:
			left = avl.left.height
		if avl.right == None:
			right = 0
		else:
			right = avl.right.height

	return max(right, left) + 1

def find(avl, k):
	if avl == None:
		return "non presente"
	else:
		if avl.value == k:
			return avl.string
		elif avl.value > k:
			return find(avl.left, k)
		else:
			return find(avl.right, k)

def clear(avl):
	avl = None
	return avl

def show(avl):
	if avl != None:
		print(str(avl.value)+":"+avl.string+":"+str(avl.height), end=' ')
		show(avl.left)
		show(avl.right)
	else:
		print("NULL", end=" ")

def remove(root, avl, k):

	if avl.value == k:
		avl = delete_node(root, avl)
	elif avl.value < k:
		avl.right = remove(root, avl.right, k)
	else:
		avl.left = remove(root, avl.left, k) 

	if avl != None:
		return avl_correction(avl)
	else:
		return None


def delete_node(root, node):
	assert node != None
	if node.left == None or node.right == None:
		x = node 
	else:
		x = avl_successor(node)

	if x.left != None:
		v = x.left 
	else:
		v = x.right

	p = x.parent
	if p == None:
		root = v
	else:
		if x == p.left:
			p.left = v 
		else:
			p.right = v  

	if v != None:
		v.parent = p  

	if node != x:
		node.value = x.value
		node.string = x.string

	return node

def avl_successor(x):
	if x.right != None:
		return avl_min(x.right)

	p = x.parent
	while p != None and x.left != None:
		x = p
		p = p.parent
	return x

def avl_min(x):
	while x.left != None:
		x = x.left
	return x


avl = None
s = input().split(" ")
while True:
	if s[0] == 'insert':
		avl = insert(avl, int(s[1]), s[2])
	elif s[0] == 'remove':
		avl = remove(avl, avl, int(s[1]))
	elif s[0] == 'show':
		show(avl)
		print()
	elif s[0] == 'clear':
		avl = clear(avl)
	elif s[0] == 'find':
		print(find(avl, int(s[1])))
	else:
		break
	s = input().split(" ")