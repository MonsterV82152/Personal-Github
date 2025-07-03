start1 = input().split(' ')
day = int(start1[0])
days = int(start1[1])
print('Sun Mon Tue Wed Thr Fri Sat')
for i in range((day-1) * 4-1):
    print(' ',end = '')

# print(' ' if day > 1 else '',end='')
# for i in range(days):
#     n = ' ' + ('' if i+1>=10 else ' ') + str(i+1)
#     print(n,end = '\n' if (i+day) % 7 == 0 or i + 1 == days else ' ')


a = day
for i in range(int(start1[1])):
    n = ((' ' if a == 1 else '  ') if i+1 > 9 else ('  'if a == 1 else '   '))+str(i+1)
    print(n,end = '')
    if a == 7 and i+1 != days:
        print('')
        a = 1
    else:
        a = a+1
print('')

# Resources: 0.882s, 9.50 MB 
# Maximum single-case runtime: 0.032s 
# Final score: 100/100 (5.0/5 points) 


