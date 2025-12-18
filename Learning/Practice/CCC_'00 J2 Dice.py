a = int(input())
b = int(input())
answer = 0
if (a <= 9 and b >= 9) or (a >= 9 and b <=9):
    answer = a if a <= b else b
elif a < 10 and b < 10:    
    if a > b:
        if a + b >= 10:
            answer = b-(9-a)
        else:
            answer = 0
    elif b > a :
        if a + b >= 10:
            answer = a-(9-b)
        else:
            answer = 0
else:
    answer = 9
if answer == 1:
    print('There is 1 way to get the sum 10.')
else:
    print('There are '+str(answer)+' ways to get the sum 10.')

