import sys
import pygame
import random
import time
pygame.font.init()
screen = pygame.display.init()
width = 630
height = width
cellsize = 30
snakelen = 3
waittime = 200
direction = 's'
counter = 0
score = 0
basespeed = 200
appleis = True
grid = [['' for i in range(width//cellsize)] for j in range(height//cellsize)]
screen = pygame.display.set_mode((width,height))

def apple():
    global snakelen, waittime, basespeed
    basespeed = basespeed * 0.98
    snakelen+=1
    waittime = basespeed
    while True:
        b = random.randint(0,len(grid)-1)
        c = random.randint(0,len(grid[0])-1)
        if grid[b][c] == '':
            p = random.randint(0,10)
            if appleis:
                if p <= 5:
                    grid[b][c] = 'a'
                    break
                elif p == 6 or p == 7:
                    grid[b][c] = 'sap'
                    break
                elif p == 8 or p == 9:
                    grid[b][c] = 'sae'
                    break
                elif p == 10:
                    grid[b][b] = 'sad'
            else:
                grid[b][c] = 'a'
                break
            
def superapple(type):
    global snakelen, waittime, basespeed
    basespeed = basespeed * 0.95
    snakelen+=1
    if type == 'p':
        waittime = (waittime/basespeed)*waittime//2
    elif type == 'e':
        snakelen+=4
    elif type == 'd':
        snakelen -= 3
    while True and type != 'd':
        b = random.randint(0,len(grid)-1)
        c = random.randint(0,len(grid[0])-1)
        if grid[b][c] == '':
            p = random.randint(0,5)
            if p <= 2:
                grid[b][c] = 'a'
                break
            elif p == 3:
                grid[b][c] = 'sap'
                break
            elif p == 4:
                grid[b][c] = 'sae'
                break
            elif p == 5:
                grid[b][c] = 'sad'
            
def startgame():
    global direction, snakelen, waittime, counter, basespeed, score
    keyboard = False
    keyboard1 = False
    a = True
    while a:
        pygame.display.update()
        events = pygame.event.get()
        for event in events:
            if event.type == pygame.KEYDOWN and event.key == pygame.K_SPACE:
                a = False
                break
    grid[len(grid)//2][len(grid[0])//2] = 1
    grid[len(grid)//2][len(grid[0])//2-1] = 2
    grid[len(grid)//2][len(grid[0])//2-2] = 3
    while True:
        b = random.randint(0,len(grid)-1)
        c = random.randint(0,len(grid[0])-1)
        if grid[b][c] == '':
            grid[b][c] = 'a'
            break
    while True:
        movelist = []   
        score = snakelen-3 
        if snakelen <= 0:
            break
        events = pygame.event.get()
        offset = 0
        if len(movelist) != 0:
                    keyboard = True
                    keyboard1 = True
                    direction = movelist[0]
                    movelist.pop(0)
        for event in events:
            if event.type == pygame.QUIT:
                pygame.quit()
                print('quite')
            if event.type == pygame.KEYDOWN:
                
                if (event.key == pygame.K_w or event.key == pygame.K_UP) and direction != 's':
                    if keyboard == False and keyboard1 == False:
                        direction = 'w'
                    else:
                        movelist.append('w')
                if (event.key == pygame.K_s or event.key == pygame.K_DOWN) and direction != 'w':
                    if keyboard == False and keyboard1 == False:
                        direction = 's'
                    else:
                        movelist.append('s')        
                if (event.key == pygame.K_d or event.key == pygame.K_RIGHT) and direction != 'a':
                    if keyboard == False and keyboard1 == False:
                        direction = 'd'
                    else:
                        movelist.append('d')        
                if (event.key == pygame.K_a or event.key == pygame.K_LEFT) and direction != 'd':
                    if keyboard == False and keyboard1 == False:
                        direction = 'a'
                    else:
                        movelist.append('a')  

                keyboard = True
                keyboard1 = True
            # elif event.type == pygame.KEYDOWN and event.key == pygame.K_p:
            #     snakelen+=1
            # elif event.type == pygame.KEYDOWN and event.key == pygame.K_o:
            #     snakelen-=1
        if counter == 2:
            counter = 0
            gameover = False
            for i in range(len(grid)):
                for j in range(len(grid[i])):
                    if grid[i][j] != '' and grid[i][j] != 'a' and 'sa' not in str(grid[i][j]):
                        if grid[i][j] == 1:
                            if direction == 'd':
                                if j+1 > len(grid[i])-1:
                                    gameover = True
                                    break
                                elif grid[i][j+1] == 'a':
                                    apple()
                                elif 'sa' in str(grid[i][j+1]):
                                    superapple(grid[i][j+1][2])
                                elif grid[i][j+1] != '':
                                    gameover = True
                                    break
                                grid[i][j+1] = 0
                            elif direction == 's':
                                if i+1 > len(grid)-1:
                                    gameover = True
                                    break
                                elif grid[i+1][j] == 'a':
                                    apple()
                                elif 'sa' in str(grid[i+1][j]):
                                    superapple(grid[i+1][j][2])
                                elif grid[i+1][j] != '':
                                    gameover = True
                                    break
                                grid[i+1][j] = 0
                            elif direction == 'a':
                                if j-1 < 0:
                                    gameover = True
                                    break
                                elif grid[i][j-1] == 'a':
                                    apple()
                                elif 'sa' in str(grid[i][j-1]):
                                    superapple(grid[i][j-1][2])
                                elif grid[i][j-1]!= '':
                                    gameover = True
                                    break
                                grid[i][j-1] = 1
                            elif direction == 'w':
                                if i-1 < 0:
                                    gameover = True
                                    break
                                elif grid[i-1][j] == 'a':
                                    apple()
                                elif 'sa' in str(grid[i-1][j]):
                                    superapple(grid[i-1][j][2])
                                elif grid[i-1][j] != '':
                                    gameover = True
                                    break
                                grid[i-1][j] = 1
                            keyboard = False
                        if grid[i][j] < snakelen:
                            grid[i][j] += 1
                        elif grid[i][j] == snakelen:
                            grid[i][j] = ''
                        
            if gameover:
                break
            screen.fill((86,170,75))
            font1 = pygame.font.Font('BroadwayRegular-7Bpow.ttf', 40)
            text = font1.render(str(score), True, (255, 0, 0))
            textRect = text.get_rect()
            textRect.topright = (width-10,0+10)
            
            for i in range(len(grid)):
                for j in range(len(grid[i])):
                    if grid[i][j] == 'a':
                        pygame.draw.rect(screen, (200,0,0), (j*cellsize,i*cellsize,cellsize,cellsize))
                        pygame.draw.rect(screen, (50,0,0), (j*cellsize+(cellsize-6)/2,i*cellsize-7,6,14))
                    elif grid[i][j] == 'sap':
                        pygame.draw.rect(screen, (63, 3, 74), (j*cellsize,i*cellsize,cellsize,cellsize))
                        pygame.draw.rect(screen, (25,0,25), (j*cellsize+(cellsize-6)/2,i*cellsize-7,6,14))
                    elif grid[i][j] == 'sae':
                        pygame.draw.rect(screen, (29, 3, 99), (j*cellsize,i*cellsize,cellsize,cellsize))
                        pygame.draw.rect(screen, (25,0,25), (j*cellsize+(cellsize-6)/2,i*cellsize-7,6,14))
                    elif grid[i][j] == 'sad':
                        pygame.draw.rect(screen, (0), (j*cellsize,i*cellsize,cellsize,cellsize))
                        pygame.draw.rect(screen, (25,0,25), (j*cellsize+(cellsize-6)/2,i*cellsize-7,6,14))
                    elif grid[i][j] == 1:
                        pygame.draw.rect(screen, (3, 64, 19), (j*cellsize,i*cellsize,cellsize,cellsize))
                        # pygame.draw.rect(screen, (random.randint(100,255),random.randint(100,255),random.randint(100,255)), (j*cellsize,i*cellsize,cellsize,cellsize))
                    elif grid[i][j] != '':
                        if grid[i][j]%2 == 0:

                            pygame.draw.rect(screen, (29, 110, 20), (j*cellsize,i*cellsize,cellsize,cellsize))
                            pygame.draw.rect(screen, (29, 140, 20), (j*cellsize+5,i*cellsize+5,cellsize-10,cellsize-10))
                            # pygame.draw.rect(screen, (random.randint(100,255),random.randint(100,255),random.randint(100,255)), (j*cellsize,i*cellsize,cellsize,cellsize))
                            # pygame.draw.rect(screen, (random.randint(100,255),random.randint(100,255),random.randint(100,255)), (j*cellsize+5,i*cellsize+5,cellsize-10,cellsize-10))
                        else:
                            pygame.draw.rect(screen, (25, 92, 17), (j*cellsize,i*cellsize,cellsize,cellsize))
                            pygame.draw.rect(screen, (25, 110, 17), (j*cellsize+5,i*cellsize+5,cellsize-10,cellsize-10))
                            # pygame.draw.rect(screen, (random.randint(100,255),random.randint(100,255),random.randint(100,255)), (j*cellsize,i*cellsize,cellsize,cellsize))
                            # pygame.draw.rect(screen, (random.randint(100,255),random.randint(100,255),random.randint(100,255)), (j*cellsize+5,i*cellsize+5,cellsize-10,cellsize-10))
                    else:
                        pygame.draw.rect(screen, (86,140,75), (j*cellsize,i*cellsize,cellsize,cellsize))
                        pygame.draw.rect(screen, (86,170,75), (j*cellsize+5,i*cellsize+5,cellsize-10,cellsize-10))
                        # pygame.draw.rect(screen, (random.randint(0,100),random.randint(0,100),random.randint(0,100)), (j*cellsize,i*cellsize,cellsize,cellsize))
                        # pygame.draw.rect(screen, (random.randint(0,100),random.randint(0,100),random.randint(0,100)), (j*cellsize+5,i*cellsize+5,cellsize-10,cellsize-10))
            pygame.display.update()
        counter += 1
        k = int(waittime)-offset if int(waittime)-offset >= 0 else 0
        pygame.time.wait(k//2)
        
        keyboard1 = False
while True:
    startgame()
    screen.fill((86,170,75))
    font1 = pygame.font.Font('BroadwayRegular-7Bpow.ttf', 100)
    text = font1.render("GAME OVER", True, (255, 0, 0))
    textRect = text.get_rect()
    textRect.center = (width//2,height//2)
    screen.blit(text, textRect)
    text1 = font1.render(str(score), True, (255, 0, 0))
    textRect1 = text1.get_rect()
    textRect1.center = (width//2,height//2+120)
    screen.blit(text1, textRect1)
    cellsize = 30
    snakelen = 3
    waittime = 200
    direction = 's'
    counter = 0
    score = 0
    basespeed = 200
    grid = [['' for i in range(width//cellsize)] for j in range(height//cellsize)]
