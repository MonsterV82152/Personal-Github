def aword(w):
    if w == '' or 'BS' in w or 'BN' in w or 'NS' in w or 'SA' in w:
        return False
    c1 = 0
    c2 = 0
    n = False
    n1 = 0
    for c in w:
        if c == 'B':
            if c2 == 1:
                return False
            c1+=1
        elif c == "A":
            c2+=1
        elif c == "N":
            c2-=1
        elif c == "S":
            c1 -= 1
        else:
            return False
        if c2 < 0 or c2>1:
            return False
    if c1 != 0 or c2 != 1:
        return False
    else:
        return True

# while True:
#     a = input()
#     if a == 'x':
#         break
#     print(aword(a))


tests = []
while True:
    tests.append(input())
    if tests[len(tests)-1] == 'X':
        tests.pop()
        break

for test in tests:
    if aword(test):
        print('YES')
    else:
        print('NO')

