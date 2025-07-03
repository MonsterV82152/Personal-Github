lis1 = [[0,0,1,1,1,0,0,1],[1,1,0,0,0,1,1,0]]
for lis in lis1:
    res = 0
    for i in range(len(lis)):
        if i == 0:
            res -= lis[i] * 2**(len(lis)-1)
        else:
            res += lis[i] * 2**(len(lis)-i-1)
    print(res)