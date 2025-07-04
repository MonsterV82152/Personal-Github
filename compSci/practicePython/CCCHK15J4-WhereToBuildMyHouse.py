length = int(input())
river = [0 for i in range(length+1)]
river.pop(0)
for i in range(int(input())):
    bob = input().split(' ')
    river[int(bob[0]):int(bob[1])] = [1 for i in range(int(bob[1])-int(bob[0]))]
count = 0
saved = 0
iftrue = False
for i in river:
    if i == 0:
        count+=1
        iftrue = True
    if i == 0:
        if count > saved:
            saved = count
        count = 0
if not iftrue:
    print('0')
else:
    print(saved)