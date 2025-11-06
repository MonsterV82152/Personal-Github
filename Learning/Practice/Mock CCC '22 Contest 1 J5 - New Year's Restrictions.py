inp = input().split(' ')
cont = []
resoloutions = list(range(1,int(inp[0])+1))
for i in range(int(inp[1])):
    sdf = input().split(' ')
    cont.append(int(sdf[0]))
    cont.append(int(sdf[1]))


largest = [0,0]


    

while True:
    shouldbreak = True
    for i in resoloutions:
        if cont.count(i) > 1 and cont.count(i) > largest[0]:
                largest[0] = cont.count(i)
                largest[1] = i
                shouldbreak = False
    
    if not shouldbreak:
        resoloutions.remove(largest[1])
        for i in range(largest[0]):
            cont.remove(largest[1])

    largest = [0,0]

    if shouldbreak:
        break
print(len(resoloutions))
    
        