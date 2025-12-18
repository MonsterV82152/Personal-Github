lis = []
while True:
    lis.append(int(input()))
    if lis[len(lis)-1] == 0:
        lis.pop()
        break

d = []
for item in lis:
    a = 1
    b = 0
    d.append([item,item])
    while a <= item:
        if item % a == 0:
            b = item//a
        if (b+a) < sum(d[len(d)-1]):
            d[len(d)-1] = [a,b]
        a = a+1
for item in d:
    print('Minimum perimeter is',sum(item)*2,'with dimensions',item[0],'x',item[1])

