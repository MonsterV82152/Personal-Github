a = int(input())
b = int(input())
c = int(input())
for i in range(a):
    print('*',end = '')
    for i in range(2):
        for i in range(b):
            print(' ',end = '')
        print('*',end = '')
    print('')
for i in range(b*2+3):
    print('*',end = '')
print('')
for i in range(c):
    for i in range(b+1):
        print(' ',end = '')
    print('*')

