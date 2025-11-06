x = input()
lis = x.split()
lis = [int(i) for i in lis]
lis.sort()
for i in lis:
    print(i * 2)
print(lis)

