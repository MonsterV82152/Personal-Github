row = int(input())
column = int(input())
max = int(input())
grid = [(i%max)+1 for i in range(row*column)]
def findNeighbours(x,y,count):
    global grid, shortestroute
    count+=grid[(y-1)*column+x-1]
    if count < shortestroute:
        if y == row:
            shortestroute = count
        else:
            findNeighbours(x,y+1,count)
            if column >= x+1:
                findNeighbours(x+1,y+1,count)
            if 1 <= x-1:
                findNeighbours(x-1,y+1,count)
for i in range(column):
    findNeighbours(i,1,0)