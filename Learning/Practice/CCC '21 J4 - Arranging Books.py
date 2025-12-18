stuff = list(input())
Ls = stuff.count('L')
Ms = stuff.count('M')
Ss = stuff.count('S')
swaps = 0
for i in range(Ls):
    if stuff[i] != 'L':
        for j in range(len(stuff)-Ls):
            if stuff[Ls+j] == 'L':
                stuff[Ls+j] = stuff[i]
                stuff[i] = 'L'
                swaps+=1
        # if stuff[i] == 'M':
        #     for j in range(Ms):
        #         if stuff[Ls+j] == 'L':
        #             stuff[Ls+j] = 'M'
        #             stuff[i] = 'L'
        #             swaps+=1
        #             break
        # elif stuff[i] == 'S':
        #     for j in range(Ss):
        #         if stuff[Ls+Ms+j] == 'L':
        #             stuff[i] = 'L'
        #             stuff[Ls+Ms+j] = 'S'
        #             break
if Ms != 0:
    for i in range(Ms):
        if stuff[Ls+i] != 'M':
            if stuff[Ls+i] == 'S':
                for j in range(Ss):
                    if stuff[Ls+Ms+j] == 'M':
                        stuff[Ls+Ms+j] = 'S'
                        stuff[Ls+i] = 'M'
                        swaps+=1
                        break


print(swaps)
        
                

