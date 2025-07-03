r = int(input())
c = int(input())
grid = []
for i in range(r):
    grid.append([])
    a = input()
    for j in range(len(a)):
        grid[i].append(a[j])
farmery = int(input())
farmerx = int(input())
explored = []
points = 0
def findneighbors(x,y):
    global grid, points
    if grid[y][x] != '*':
        if grid[y][x] == 'M':
            points+=5
        elif grid[y][x] == 'S':
            points+=1
        elif grid[y][x] == 'L':
            points+=10
        explored.append([x,y])
        if x+1 < c:
            if grid[y][x+1] != '*' and [x+1,y] not in explored:
                findneighbors(x+1,y)
        if x-1 >= 0:
            if grid[y][x-1] != '*' and [x-1,y] not in explored:
                findneighbors(x-1,y)
        if y-1 >= 0:
            if grid[y-1][x] != '*' and [x,y-1] not in explored:
                findneighbors(x,y-1)
        if y+1 < r:
            if grid[y+1][x] != '*' and [x,y+1] not in explored:
                findneighbors(x,y+1)
findneighbors(farmerx,farmery)
print(points)