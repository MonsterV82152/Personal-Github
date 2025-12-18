ac = list(input())
re = list(input())
acd = []
acdnum = []
red = []
rednum = []
swaps = ['','']
quiet = ''
for i in ac:
    if i not in acd:
        acd.append(i)
        acdnum.append(1)
    else:
        acdnum[acd.index(i)]+=1
for i in re:
    if i not in red:
        red.append(i)
        rednum.append(1)
    else:
        rednum[red.index(i)]+=1
placeholder = False
for i in range(len(acd)):
    placeholder = False
    if acd[i] not in red:
        if acdnum[i] in rednum:
            for j in range(len(rednum)):
                if rednum[j] == acdnum[i] and red[j] not in acd:
                    swaps[1] = red[j]
                    swaps[0] = acd[i]
                    placeholder = True
        if len(acd) != len(red) and not placeholder:
            quiet = acd[i]


sillykeys = ['','']
quietkeys = False
if len(ac) != len(re):
    quietkeys = True

count = 0
for p in range(len(ac)):
    if p > len(ac):
        break
    if ac[p-count] == quiet:
        ac.pop(p-count)
        count += 1

for i in range(len(re)):
    if ac[i] == re[i]:
        continue
    elif ac[i] == sillykeys[0] and re[i] == sillykeys[1]:
        continue
    if sillykeys == ['',''] and ac[i] != re[i]:
        sillykeys[0] = ac[i]
        sillykeys[1] = re[i]
if quietkeys and quiet == '':
    quiet = ac[len(ac)-1]

print(sillykeys[0],'',sillykeys[1])
if quietkeys:
    print(quiet)
else:
    print('-')







