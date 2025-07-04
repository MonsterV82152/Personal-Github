c_r = input().split(' ')
currentpos = [0,0]
tests = []
te = []
while True:
    tests.append(input().split(' '))
    if tests[-1] == ['0','0']:
        break

tests.pop()
tests = [[int(i) for i in test] for test in tests]
c_r = [int(i) for i in c_r]
for test in tests:
    if currentpos[0]+test[0] > c_r[0]:
        currentpos[0] = c_r[0]
    elif currentpos[0]+test[0] < 0:
        currentpos[0] = 0
    else:
        currentpos[0]+=test[0]
    if currentpos[1]+test[1] > c_r[1]:
        currentpos[1] = c_r[1]
    elif currentpos[1]+test[1] < 0:
        currentpos[1] = 0
    else:
        currentpos[1]+=test[1]
    print(currentpos[0],currentpos[1])
               


