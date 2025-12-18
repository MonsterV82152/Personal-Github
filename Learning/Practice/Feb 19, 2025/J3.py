codes = []
uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
numbers = "1234567890-"
for i in range(int(input())):
    codes.append(input())
for i in codes:
    numberCode = 0
    endCode = ''
    number = False
    position = 0
    for j in range(len(i)):
        if i[j] in numbers:
            if not number:
                position = j
            if number:
                if i[j] == "-":
                    numberCode+=int(i[position:j])
                    position = j
            else:
                number = True
            
        else:
            if number:
                numberCode+=int(i[position:j])
            number = False
            
        if i[j] in uppercase:
            number = False
            endCode = endCode+i[j]
    if number:
        numberCode+=int(i[position:len(i)])
    print(endCode+str(numberCode))
