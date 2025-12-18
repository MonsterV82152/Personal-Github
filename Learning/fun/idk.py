


b = str(input("Input your name here: "))
c = int(input("Input the amount of Vex IQ pins you have used (Don't use a big number): "))
q = int(((len(b)*c)**5)/3.14)
print("Your code: {q}")
CommonNames = ["Bob", "Kevin", "Billy", "Joe", "Stuart", "Taylor", "Owen", "Issac"]
numbers = ['0','1','2','3','4','5','6','7','8','9']
constants = ["b", "c", 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z']

while True:
    pins = []

    for i in range(c):
        pins.append(str(input("Please give pin #"+str(i+1)+" a name: ")))

    if b in pins:
        print("Weirdo")
        break  
    else:
        a = False
        for i in CommonNames:
            if i in pins:
                print("Names are too basic, please try again.")
                a = True
                continue
        for i in pins:
            if i == "Real Names" or i == 'real names':
                print("no")
                print("just...")
                print("no")
                a = True
                continue
            pastletter = ''
            for j in i:
                if j in numbers:
                    print("no")
                    print("just...")
                    print("no")
                    a = True
                    continue
                elif pastletter in constants and j in constants:
                    if ((pastletter == "t" or pastletter == 's') and j == 'h') or (pastletter == 'd' and j == 'd'):
                        continue    
                    print("Real Names Please")
                    a = True
                    continue
                pastletter = j
    if a:
        continue

    break

for i in range(1000):
    print("\n \n \n \n \n")
print("Finally, some original names.")
if int(input('Please give us your code: ')) != q:
    print("You FAILED")
else:
    print("Finally, the FAILURE did somthing")
                    
    
        
    
