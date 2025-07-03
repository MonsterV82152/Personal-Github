pagecount = int(input())
book = []
reach = []
for i in range(pagecount):
    page = input().split(' ')
    book.append(page)
    reach.append(False)

def extendpage(page,count):
    smallest = 0
    reach[page-1] = True
    if book[page-1][0] == '0':
        return count
    else:
        for i in range(int(book[page-1][0])):
            if extendpage(int(book[page-1][i+1]),count+1) < smallest:
                smallest = extendpage(int(book[page-1][i+1]),count+1)
        return smallest
counter = True
for i in reach:
    if not i:
        counter = False
if not counter:
    print("N")
else:
    print('Y')
print(extendpage(1,1))

