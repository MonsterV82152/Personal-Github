mini = int(input())
maxi = int(input())
numhotel = int(input())
cur = 0
motels = [0, 990, 1010, 1970, 2030, 2940, 3060, 3930, 4060, 4970, 5030, 5990, 6010, 7000]
for i in range(numhotel):
    motels.append(int(input()))
total = 0
hahaha = []
counter = 1
def nexthotel(cur,many):
    b = 1
    for i in motels:
        if i > cur and i <= cur+maxi and i >= cur+mini:
            if many == b:
                return i
            else: 
                b+=1
    return -1
def findpaths(cur):
    global total, motels, mini, maxi, counter
    counter = 0
    bb = nexthotel(cur,1)
    if bb == -1:
        return 0
    elif bb == 7000:
        total+=1
        return
    else:
        while nexthotel(cur,counter) != -1:
            if nexthotel(cur, counter) == 7000:
                total+=1
                return
            else:
                findpaths(nexthotel(cur,counter))
            counter += 1


findpaths(0)

print(total)

    