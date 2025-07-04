tests = []
while True:
    tests.append(input())
    if tests[len(tests)-1] == 'X':
        tests.pop()
        break
def findS(w,start):
    count = 0
    for i in range(start,len(w)):
        if w[i] == 'B':
            count+=1
        elif w[i] == 'S':
            if count == 0:
                return i
            else:
                count -= 1
    return -1
        
def aword(test):
    if 'BS' in test:
        return False
    if test[0] == 'A':
        if len(test) == 1:
            return True
        elif test[1] == 'N' and len(test)>2:
            return aword(test[2:])
        else:
            return False
    elif test[0] == 'B':
        s = findS(test,1)
        if s != -1:
            if s+1 == len(test):
                return aword(test[1:s])
            elif len(test) >= s+2:
                if test[s+1] == 'N' and aword(test[1:s]) and len(test)>s+2:
                    return aword(test[s+2:])
                else:
                    return False
            else:
                return False
        else:
            return False
    else:
        return False
for test in tests:
    if aword(test):
        print('YES')
    else:
        print('NO')

# while True:
#     a = input()
#     if a == 'x':
#         break
#     print(aword(a))