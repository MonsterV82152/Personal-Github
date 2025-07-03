ac = list(input())
re = list(input())
quiet = ''
ifquite = False
silly = ['','']
if len(ac) != len(re):
    ifquite = True
justsilly = False
for i in range(len(re)):
    if ac[i] == re[i]:
        continue
    elif ac[i] == silly[0] and re[i] == silly[1]:
        continue
    elif quiet == '':
        if ac[i] != re[i] and ac[i+1] == re[i]:
                quiet = ac[i]
                count = 0
                for p in range(len(ac)):
                    if p > len(ac):
                        break
                    if ac[p-count] == quiet:
                        ac.pop(p-count)
                        count += 1
    if silly == ['',''] and ac[i] != re[i]:
        silly[0] = ac[i]
        silly[1] = re[i]

if ifquite:
    if quiet == '':
        quiet = ac[len(ac)-1]

print(silly[0],'',silly[1])
if quiet != '':
    print(quiet)
else:
    print('-')