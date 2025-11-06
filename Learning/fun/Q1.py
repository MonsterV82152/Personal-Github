import math
import pygame
pygame.font.init()
font1 = pygame.font.Font('BroadwayRegular-7Bpow.ttf', 25)

a = int(input())
screen = pygame.display.init()
screen = pygame.display.set_mode((2000,500))
screen.fill((155,155,155))

pygame.init()
def f(n, x,y,diff):
    if n <= 1:
        text = font1.render(str(n), True, (255, 0, 0), (0,0,0))
        textRect = text.get_rect()
        textRect.center = (x,y)
        screen.blit(text, textRect)
        pygame.display.update()
        return n
    else:
        
        pygame.draw.line(screen, (0,0,0), (x,y),(x-diff,y+25))
        a = f(n-1,x-diff,y+25, diff/1.5)
        pygame.draw.line(screen, (0,0,0), (x,y),(x+diff,y+25))
        b = f(n-2,x+diff,y+25, diff/1.5)
        text = font1.render(str(n), True, (255, 255, 255), (0,0,0))
        textRect = text.get_rect()
        textRect.center = (x,y)
        screen.blit(text, textRect)
        pygame.display.update()
        return a+b


print(f(a,1000,200,500))
pygame.time.wait(10000)