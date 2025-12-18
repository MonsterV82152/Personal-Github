base = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
cmd = int(input("Press 1 for decimal to other base. Press 2 for other base to decimal: "))
if cmd == 1:
    basenum = int(input("Please enter the base to convert to: "))

    inp = int(input("Please enter your number: "))
    a = [1]
    counter = 1
    num = ''
    
    while a[-1] < inp:
        a.append(basenum**counter)
        counter+=1
    a.pop()
    for i in range(len(a)-1,-1,-1):
        for j in range(basenum):
            if j*a[i] > inp:
                num = num+base[j-1]
                inp -= (j-1)*a[i]
                break
    print(num)
elif cmd == 2:
    basenumber = int(input("Please enter the base: "))-1
    a = input("Please enter your number: ")

    counter = 0
    number = 0
    for i in range(-1,len(a)-1,1):
        if a[i] in base:
            number += int((base.index(a[i])+1)*basenumber**counter)
        counter += 1

    print(number)