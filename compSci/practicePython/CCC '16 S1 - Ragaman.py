original = input()
test = input()
original = list(original)
test = list(test)

original.sort()
test.sort()
def function():
    if len(original) != len(test):
        return False
    while "*" in test:
        test.remove("*")
    for i in range(len(original)):
        if len(test)<= i or test[i] != original[i]:
            test.insert(i,'*')
    if len(test) != len(original):
        return False
    return True
    
if function():
    print('A')
elif not function():
    print('N')
        



