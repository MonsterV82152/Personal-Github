2, 2, 6, 10, 30, 32, 96, 100
a = [2,2]

2, 3, 5, 7, 11, 13, 17, 19
4, 6, 8, 9, 10


1, 3, 5, 7, 9
2, 4, 6, 8, 10
#two types of numbers, two types of whole numbers

start = [2, 2, 6]
for i in range(2,50,1):
    if (i % 2 != 0):
        start.append(start[-1]*3)
    elif (i % 4 == 0):
        start.append(start[-1]+2)
    elif (i % 4 == 2):
        start.append(start[-1]+4)
print(start)

# a = 2 if True else 3
# start2 = [2, 2, 6, (lambda a, i:a*3 if i % 2 != 0 else (a+2 if i % 4 == 0 else a+4))()]

#hw: https://www.datafiles.acsl.org/samples/contest3/abc_3_jr.pdf