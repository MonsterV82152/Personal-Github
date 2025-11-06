a = int(input())
stuff = []
for i in range(a):
    stuff.append(input())

for item in stuff:
    current = ''
    counter = 0
    for a in item:
        if a != current:
            if counter != 0:
                print(str(counter),current,end=' ')
            current = a
            counter = 1
        elif a == current:
            counter+=1
    if counter != 0:
        print(str(counter),current,end=' ')
    print('\n')
