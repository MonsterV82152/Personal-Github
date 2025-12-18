while True:    
    hm = int(input())
    if hm > 1000 or hm < 1:
        print ('bad')
    else: break
listA = []

for i in range(hm):
    while True:
        listA.append(input().split(' '))
        if int(listA[i][0]) > len(listA[i][1]) or len(listA[i][1]) > 80:
            print('error')
            listA.pop(i)
        else:
            break
fin = []
for item in listA:
    numA = int(item[0])
    fin.append(item[1][:(numA-1)] + item[1][numA:])
for i in range(len(fin)):
    print(i+1,fin[i])
