amount = int(input())
progress = []
for i in range(amount):
    boss = int(input())
    if boss == 0 and len(progress)>0:
        progress.pop()
    else:
        progress.append(boss)
print(sum(progress))