import math

a = ''
cows = 2
while a == '':
    cows = math.floor(cows+cows/2)
    print(cows)
    a = input()
    