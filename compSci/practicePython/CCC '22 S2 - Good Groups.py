separate = []
together = []
a = int(input())
for i in range(a):
    together.append(input().split(' '))
a = int(input())
for i in range(a):
    separate.append(input().split(' '))
tests = []
a = int(input())
for i in range(a):
    together.append(input().split(' '))
count = 0
for i in tests:
    for j in separate:
        if j[0] in i and j[1] in i:
            count+=1
    for j in together:
        if j[0] in i and not j[1] in i:
            count+=1
        elif j[1] in i and not j[0] in i:
            count+=1
print(count)
