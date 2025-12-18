p1 = input().split(" ")
p2 = input().split(" ")

def nextPoints(steps,p):
    np = [
            [p[0]+2,p[1]+1],[p[0]-2,p[1]+1],[p[0]+2,p[1]-1],[p[0]-2,p[1]-1],
            [p[0]+1,p[1]+2],[p[0]-1,p[1]+2],[p[0]+1,p[1]-2],[p[0]-1,p[1]-2]
    ]
    for pp in np:
        if pp[0]>0 and pp[1]>0 and pp[0] < 100 and pp[1] < 100 and (pp not in points) and (((p[0]-p2[0])**2 + (p[1]-p2[1])**2)<=5 or  ((pp[0]-p2[0])**2 + (pp[1]-p2[1])**2) <= ((p[0]-p2[0])**2 + (p[1]-p2[1])**2)):
            points.append([steps,pp])
                
            
p1 = [int(p1[0]),int(p1[1])]
p2 = [int(p2[0]),int(p2[1])]

points = [[0,p1]]

index = 0
while len(points)>0:
    point = points[index]
    steps = point[0]
    p = point[1]
    if p[0] == p2[0] and p[1] == p2[1]:
        print(steps)
        break
    index += 1
    
    nextPoints(steps+1,p)