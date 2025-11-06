row = int(input())
column = int(input())
max = int(input())
count = 1
grid = [[0 for i in range(column)] for i in range(row)]
shortestroute = max*row
for i in range(row):
    for j in range(column):
        grid[i][j] = count
        if (count < max):
            count+=1
        else:
            count = 1
def findNeighbours(x,y,count):
    global grid, shortestroute
    if count < shortestroute:
        count+=grid[y-1][x-1]

        if y == row:
            if count < shortestroute:
                shortestroute = count
        else:
            upColumn = column >= x+1
            downColumn = 1 <= x-1
            findNeighbours(x,y+1,count)
            if upColumn:
                findNeighbours(x+1,y+1,count)
            if downColumn:
                findNeighbours(x-1,y+1,count)
for i in range(len(grid[0])):
    findNeighbours(i+1,1,0,[])
print(shortestroute)

