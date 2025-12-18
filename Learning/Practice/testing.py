def primeNumber(num):
    sums = []
    b = num*2
    for i in range(b):
        sums.append([i+1,b-i-1])
    goodsum = []
    for item in sums:
        good = True
        for c in item:
            if good:
                if c == 1 or (c%5 == 0 and c !=5) or (c%7 == 0 and c !=7):
                    good = False
                    continue
                if (c == 2) or (c == 3):
                    continue
                elif not (((c-1) % 6 == 0) or ((c-5) % 6 == 0)):
                    good = False
                    continue
                else:
                    for i in range(5,c//2+1,6):
                        if c%(i+1) == 0 or c%(i+5) == 0:
                            good = False
                            continue
        if good:
            goodsum = item
            break
    print(goodsum[0],goodsum[1])

for i in range(4,1000,2):
    primeNumber(i)