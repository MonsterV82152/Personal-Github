donuts = int(input())
events = []
for i in range(2*int(input())):
    events.append(input())
adding = False
for i in events:
    if i == '+':
        adding = True
    elif i == '-':
        adding = False
    else:
        if adding:
            donuts+=int(i)
        else:
            donuts-=int(i)

print(donuts)