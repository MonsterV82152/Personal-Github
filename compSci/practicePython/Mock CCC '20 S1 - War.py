card = int(input())
p1 = input().split()
p2 = input().split()
many = 0
past = True
for i in range(card):
    if p1[i] == p2[i] and past:
        past = False
        many+=1
    elif p1[i] != p2[i]:
        past = True
print(many)