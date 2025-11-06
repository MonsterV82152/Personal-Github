# print((lambda word : str(26-[word.lower().count(pieces[i]) for pieces[i] in "abcdefghijklmnopqrstuvwxyz"].count(0)) + "\n" + str(sum([word.lower().count(pieces[i]) for pieces[i] in "aeiou"])) + "\n" + str(sum([word.count(pieces[i]) for pieces[i] in "abcdefghijklmnopqrstuvwxyz".upper()])) + "\n" + str(max([''.join(word.split()).lower().count(pieces[i]) for pieces[i] in "abcdefghijklmnopqrstuvwxyz"])) + "\n" + str((lambda a: max(a, key=(lambda a : len(a))))(sorted(word.split()))))(''.join([pieces[i] for pieces[i] in input() if pieces[i].isalnum() or pieces[i] == " "])))

startPose, pieces = (lambda imp : (imp[:2], [[imp[i],imp[i+1]] for i in range(3,len(imp), 2)]))(list(map(int,input().split(", "))))
king = False
count = 0
while True:
    moved = False
    for i in range(len(pieces)):
        if (not king and pieces[i][0] == startPose[0]+1 and startPose[0]+2 <= 8 and ((pieces[i][1] == startPose[1]-1 and startPose[1]-2 > 0) or (pieces[i][1] == startPose[1]+1 and startPose[1]+2 <= 8))):
            startPose = [startPose[0]+2, (pieces[i][1]-startPose[1])*2+startPose[1]]
            if (startPose[0] == 8):
                king = True
            count+=1
            pieces.pop(i) 
            moved = True
            break 
            
        elif (king and pieces[i][0] == startPose[0]-1 and startPose[0]-2 > 0 and ((pieces[i][1] == startPose[1]-1 and startPose[1]-2 > 0) or (pieces[i][1] == startPose[1]+1 and startPose[1]+2 <= 8))):
            startPose = [startPose[0]-2, (pieces[i][1]-startPose[1])*2+startPose[1]]
            count+=1
            pieces.pop(i)
            moved = True
            break
    if (not moved):
        break
print(count)