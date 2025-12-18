lis = []
lis2 = []
for i in range(int(input())):
    lis.append(input())
current = ''
for i in lis:
    if i != current:
        lis2.append([i,1])
        current = i
    elif i == current:
        lis2[len(lis2)-1][1]+=1

count = 0
maxcount = 0
used = False
        count += lis2[i][1]
        if count >= maxcount:
            used = False
            maxcount = count
    elif lis2[i][0] == 'P' and lis2[i][1] == 1 and len(lis2) > i+1:
        if count+lis2[i+1][1]+1 > maxcount:
            used = True
            maxcount = count+lis2[i+1][1]+1
        count = 0
    
    else:
        count = 0
        
if not used:
    if (len(lis2) == 1):
        if lis2[0][0] == 'S':
            maxcount-=1
        else:
            maxcount+=1
    else:
        maxcount+=1

        

print(maxcount)