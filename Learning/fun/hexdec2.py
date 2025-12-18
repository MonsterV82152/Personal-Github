a = input("Please enter your number: ")

base = "123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
basenumber = int(input("Please enter the base: "))

counter = 0
number = 0
for i in range(-1,len(a)-1,-1):
    if a[i] in base:
        number += int((base.index(a[i])+1)*basenumber**counter)
    counter += 1

print(number)