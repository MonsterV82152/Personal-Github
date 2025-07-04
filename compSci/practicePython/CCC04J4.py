encode = input()
word = input()
poop = []
char = list(map(chr, range(97, 123)))+list(map(chr, range(65, 91)))
print(char)
diff = []
a = 0
for i in range(len(word)):
    if word[i-a] not in char:
        word = word[:(i-a)]+word[(i+1-a):]
        a+=1
for i in range(len(word)):
    if i%len(encode) == 0:
        poop.append(word[i])
    elif i%len(encode) != 0:
        poop[len(poop)-1] = poop[len(poop)-1]+word[i]

for i in encode:
    diff.append(65-ord(i))
print(list(map(chr, range(50, 123))))
for j in poop:
    for k in range(len(j)):
        if ord(j[k])+diff[k] > 91:
            print(chr(65+((ord(j[k])+diff[k])%91)), end='')    
        else:
            print(chr(ord(j[k])+diff[k]),end='')    