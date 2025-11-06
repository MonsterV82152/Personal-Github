a = int(input())
stuff = []
for i in range(a):
    stuff.append(input())
for i in range(len(stuff)):
    stuff[i] = stuff[i].split()
for item in stuff:
    for i in range(int(item[0])):
        print(item[1], end='')
    print('\n')