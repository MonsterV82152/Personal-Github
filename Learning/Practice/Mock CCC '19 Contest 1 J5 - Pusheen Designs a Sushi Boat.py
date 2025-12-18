pot = input().split(' ')
suhsu = int(pot[0])
needed = int(pot[1])
types = input().split(' ')
N = []
ones = 0
for i in range(len(types)):
    types[i] = int(types[i])
for i in range(suhsu):
    if types.count(i) == 0:
        continue
    else:
        N.append(types.count(i))

def bob():
    


