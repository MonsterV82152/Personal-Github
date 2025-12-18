a = input()
b = []
for i in a:
    b.insert(0,i)
number = 0
for i in range(len(b)):
    if b[i] == '1':
        number += 16**i
    elif b[i] == '2':
        number += 16**i*2
    elif b[i] == '3':
        number += 16**i*3
    elif b[i] == '4':
        number += 16**i*4
    elif b[i] == '5':
        number += 16**i*5
    elif b[i] == '6':
        number += 16**i*6
    elif b[i] == '7':
        number += 16**i*7
    elif b[i] == '8':
        number += 16**i*8
    elif b[i] == '9':
        number += 16**i*9
    elif b[i] == 'A':
        number += 16**i*10
    elif b[i] == 'B':
        number += 16**i*11
    elif b[i] == 'C':
        number += 16**i*12
    elif b[i] == 'D':
        number += 16**i*13
    elif b[i] == 'E':
        number += 16**i*14
    elif b[i] == 'F':
        number += 16**i*15
print(number)
