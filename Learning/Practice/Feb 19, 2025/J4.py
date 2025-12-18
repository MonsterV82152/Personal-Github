lis = []

for i in range(int(input())):
    lis.append(input())
maxcount = 0
def findCount(inp, count, used):
    global maxcount
    for i in range(len(inp)):
        if inp[i] == "S":
            count+=1
        else:
            if not used:
                findCount(inp[i+1:],count+1,True)
            if count > maxcount:
                maxcount = count
            count = 0
    if count > maxcount:
        maxcount = count
        count = 0

            

findCount(lis,0,False)
# for i in lis:
#     if i == "S":
#         count+=1
#     else:
#         if not sunshine:
#             count+=1
#             sunshine = True
#         else:
#             sunshine = False
#             if count > maxcount:
#                 maxcount = count
#             count = 0
print(maxcount)