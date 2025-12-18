num = int(input())
topnums = [0,2,3,5,6,7,8,9]
midnums = [2,3,4,5,6,8,9]
botnums = [2,3,5,6,8,9,0]
toplside = [5,6]
toprside = [1,2,3,7]
tops = [4,8,9,0]
botlside = [2]
botrside = [1,3,4,5,7,9]
bots = [6,8,0]


if num in topnums:
    print(' * * *')
else:
    print('')
if num in toplside:
    for i in range(3):
        print('*')
elif num in toprside:
    for i in range(3):
        print('      *')
elif num in tops:
    for i in range(3):
        print('*     *')
if num in midnums:
    print(' * * *')
else:
    print('')
if num in botlside:
    for i in range(3):
        print('*')
elif num in botrside:
    for i in range(3):
        print('      *')
elif num in bots:
    for i in range(3):
        print('*     *')
if num in botnums:
    print(' * * *')
else:
    print('')
