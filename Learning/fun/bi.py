a = int(input())
num = 0
counter = 0
while True:
    if a < 10:
        num += a*2**counter
        break
    else:
        num += a%10*2**counter
        a = a//10
    counter+= 1

print(num)

