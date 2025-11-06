
# basenum = int(input("Please enter the base to convert to: "))
# base = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
# basenums = [2,8,16]
# inp = int(input("Please enter your number: "))
# for basenum in basenums:
#     print("Base number: " + str(basenum))
#     for inputs in range(1,51,1):
#         inp = inputs
#         a = [1]
#         counter = 1
#         num = ''
#         while a[-1] <= inp:
#             a.append(basenum**counter)
#             counter+=1
#         a.pop()
#         for i in range(len(a)-1,-1,-1):
#             for j in range(basenum+1):
#                 if j*a[i] > inp:
#                     num = num+base[j-1]
#                     inp -= (j-1)*a[i]
#                     break
#         if (inputs != 50):
#             print(num,end=", ")
#         else:
#             print(num,end='')
#     print("\n")

# Optimization
# for basenum in basenums:
#     for inputs in range(1,51,1):
#         num = inputs
#         result = ''
#         while num != 0:
#             result = base[num%basenum] + result
#             num //= basenum
#         print(result)

a = [1,2,3]
print(a.count(1))
def findNum(num, basenum):
    result = ''
    while num != 0:
        result = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"[num%basenum] + result
        num //= basenum
    return result
getNum = lambda num, basenum : sum(["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".index(str(num)[i])*basenum**(len(str(num))-i-1) for i in range(len(str(num)))])
# print([[findNum(num, basenum) for num in range(1,51,1)] for basenum in [2,8,16]])
# print([[format(i, j) for i in range(1,51,1)] for j in ['b', 'o', 'x']])
print(findNum(16271926, 2))
print(getNum(100001100001011100101001101100,2))
# for i in range(1, 51):
#     print(format(i, 'o'))

# for x in range(1, 51):
#     print(format(x, 'x'))

# for i in range(1, 51):
#     print(format(i, 'b'))

# 46 
# 56