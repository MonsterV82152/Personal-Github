ac = list(input())
re = list(input())

sillykeys = ['','']
quiet = ''
quietkeys = False
if len(ac) != len(re):
    quietkeys = True



for i in range(len(re)):
    if ac[i] == re[i]:
        continue
    elif ac[i] == sillykeys[0] and re[i] == sillykeys[1]:
        continue
    elif ac[i] != re[i]:
        if quiet == '':
            test = ''
            for d in ac[i:]:
                if d != ac[i+1]:
                    test = d
                    break
            if ac[i+1] == re[i] and quietkeys:
                quiet = ac[i]
                count = 0
                for p in range(len(ac)):
                    if p > len(ac):
                        break
                    if ac[p-count] == quiet:
                        ac.pop(p-count)
                        count += 1
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



