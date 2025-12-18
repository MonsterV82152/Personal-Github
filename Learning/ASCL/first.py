# Get input from user -> list -> least amount of lines to find the mode
# lis = input().split(" ")

# num = [[i, lis.count(i)] for i in lis]
# highest = num[0]
# for i in num:
#     highest = i if (i[1] > highest[1]) else highest
# print(highest[0])


print((lambda a: max(a, key=a.count))(list(map(float, input().split(" ")))))