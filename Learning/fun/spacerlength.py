calculation = str(input("\"l\" for length to spacers, \"j\" for objects to length,\n or \"k\" for objects to spacers: "))
length = ['0.5\" Spacer(s) - ','0.375\" Spacer(s) - ','0.25\" Spacer(s) - ','1/8\" Spacer(s) - ','1/16\" Spacer(s) - ','1/32\" Spacer(s) - ','1/64\" Spacer(s) - ']
lengths = [0.5,0.375,0.25,0.125,0.0625,0.03125,0.015625]
needed = [0,0,0,0,0,0,0]
output = 0
brea = False
if calculation == 'l': output = float(input("Needed Length for Spacers: "))
if calculation == "j" or calculation == 'k':
    inp = input("""\n\nPlease input in this format -> CodeName(No Space)Quantity(Space)\n
    Nylock -> n\n
    C-Channel -> c\n
    Spacers -> 1/2s 3/8s 1/4s 1/8s 1/16s 1/32s 1/64s\nl
    Bearing -> b\n
    HGear -> hg\n
    LGear -> lg\n
    Insert -> i\n\n
    Custom Length -> l
""").split(' ')
    for i in inp:
        if 'n' in i:
            output += 0.1875*int(i[i.index('n')+1])
        elif 'c' in i:
            output += 0.046*int(i[i.index('c')+1])
        elif 'b' in i:
            output += 0.25*int(i[i.index('b')+1])
        elif 'hg' in i:
            output += 0.384*int(i[i.index('hg')+2])
        elif 'lg' in i:
            output += 0.375*int(i[i.index('lg')+2])
        elif 'i' in i:
            output += 0.0625*int(i[i.index('i')+1])
        elif '1/2s' in i:
            output += 0.5*int(i[i.index('1/2s')+4])
        elif '3/8s' in i:
            output += 0.375*int(i[i.index('3/8s')+4])
        elif '1/4s' in i:
            output += 0.25*int(i[i.index('1/4s')+4])
        elif '1/8s' in i:
            output += 0.125*int(i[i.index('1/8s')+4])
        elif '1/16s' in i:
            output += 0.0625*int(i[i.index('1/16s')+5])
        elif '1/32s' in i:
            output += 0.03125*int(i[i.index('1/32s')+5])
        elif '1/64s' in i:
            output += 0.015625*int(i[i.index('1/64s')+5])
        elif 'l' in i:
            i = i[1:]
            print(i)
            i = i.split('+')
            print(i)
            for j in i:
                j = j.split('.')
                print(j)
                output += int(j[0])
                output += int(j[1])/(10^len(j[1]))
        
if calculation == "l" or calculation == "k":
    
    while True:
        brea = True
        for i in range(len(lengths)):
            if lengths[i] <= output:
                needed[i] += 1
                output -= lengths[i]
                brea = False
                break
        if brea: break
    
for i in range(len(needed)):
    if needed[i] != 0:
        print(length[i]+str(needed[i]))
if calculation == 'l' or calculation == 'k': print('Extra - '+str(output))
if calculation == 'j': print(output)
        
