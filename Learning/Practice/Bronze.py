stuff = []
for i in range(int(input())):
    stuff.append(int(input()))
gold = [0,0]
silver = [0,0]
bronze = [0,0]
for i in stuff:
    if i == gold[0]:
        gold[1] += 1
    elif i == silver[0]:
        silver[1] += 1
    elif i == bronze[0]:
        bronze[1] += 1
    elif i > gold[0]:
        bronze[1] = silver[1]
        bronze[0] = silver[0]
        silver[0] = gold[0]
        silver[1] = gold[1]
        gold[0] = i
        gold[1] = 1
    elif i > silver[0]:
        bronze[1] = silver[1]
        bronze[0] = silver[0]
        silver[0] = i
        silver[1] = 1
    elif i > bronze[0]:
        bronze[0] = i
        bronze[1] = 1



    
    
print(bronze[0],'',bronze[1])


