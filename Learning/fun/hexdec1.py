a = input("Please enter your number: ")

base = "123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
basenumber = int(input("Please enter the base: "))
b = []
number = 0
for i in a:
    b.insert(0,i)
for i in range(len(b)):
        if b[i] in base:
            number += int((base.index(b[i])+1)*basenumber**i)

print(number)