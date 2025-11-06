alph = "abcdefghijklmnopqrstuvwxyz"
said = [0 for i in alph]
total = 0
t = int(input())
prints = []
lastCommand = 0
index = -1
reverse = 1
for i in range(t):
    command = input().split()
    num = int(command[1])-lastCommand
    total+=num//26
    num%=26
    for j in range(num):
        index += reverse
        index%=26
        said[index]+=1
    if (command[0] == "UPIT"):
        prints.append(said[alph.index(command[2])]+total)
    else:
        reverse*=-1
    lastCommand = int(command[1])
print("\n".join(list(map(str,prints))))