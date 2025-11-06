inp = input().split().split("")
operations = []
currentOperation = ''
def findOperation(a):
    operation = []
    i = len(a)-1
    while i >= 0:
        i-=1
        match a[i]:
            case ")":
                for j in range(len(a)):
                    if a[j] == "(":
                        operation.append(findOperation(a[j+1:i]))
                        i = j
                        break
            case str() if a[i] == "*" or a[i] == "x":
                

