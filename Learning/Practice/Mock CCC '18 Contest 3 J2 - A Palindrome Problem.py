put = input()
iftrue = False
for i in range(len(put)):
    if len(put) <= 1:
        iftrue = False
        break
    first = put[:i+1]
    last = put[i+1:]
    if last[::-1] == last and first[::-1] == first:
        iftrue = True
        break
if iftrue:
    print('Yes')
else:
    print('No')