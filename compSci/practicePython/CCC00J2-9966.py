num1 = int(input())
num2 = int(input())

c1 = ["0","1","8"]
c2 = ["6","9"]
c3 = ['0','1','8','6','9']
nums = []
good = False
for i in range(num1,num2):
    digit = []
    isValid = True
    for j in str(i):
        if j not in c3:
            isValid = False
            break
        else:
            digit.append(j)
    if not isValid:
        continue
    for e in range(len(digit)//2):
        if (digit[e] == digit[len(digit)-1-e] and digit[e] not in c2) or (digit[e] == '6' and digit[len(digit)-1-e] == '9') or (digit[e] == '9' and digit[len(digit)-1-e] == '6'):
            isValid = True
        else:
            isValid = False
            break
    if len(digit)%2 == 1:
        if digit[len(digit)//2] not in c1:
            isValid = False
    if isValid:
        nums.append(i)
print(len(nums))



