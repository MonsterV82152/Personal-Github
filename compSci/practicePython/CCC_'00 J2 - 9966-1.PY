num1 = input()
num2 = input()
len1 = len(num1)
len2 = len(num2)

chars = ['00','11','88','69','96']
nums = []
def add(l,n):
    if n == '' and l%2 == 1:
        for c in chars[:3]:
            n = c[0]
            add(l,n)
    elif len(n) < l:
        if len(n) == l-2:
            for c in chars[1:]:
                add(l,c[0]+n+c[1])
        else:
            for c in chars:
                add(l,c[0]+n+c[1])
    else:
        if int(n) >= int(num1) and int(n) <= int(num2):
            nums.append(n)
for l in range(len1,len2+1):
    add(l,'')

print(len(nums))
