a = str(input("Put Sentence/Word Here: "))
p = 0
import random
for i in range(len(a)):
    
    if a[i+p] == 'l':
        a = a[:i+p]+'w'+a[i+p+1:]
    elif a[i+p] == 'r':
        a = a[:i+p]+'wr'+a[i+p+1:]
        p+=1
    elif a[i+p] == 'o':
        a = a[:i+p]+'wo'+a[i+p+1:]
        p+=1
    elif a[i+p] == 'd':
        k = random.randint(1,3)
        if k == 1:
            a = a[:i+p]+'dw'+a[i+p+1:]
            p+=1
    elif a[i+p] == 'u':
        k = random.randint(1,3)
        if k == 1:
            a = a[:i+p]+'uw'+a[i+p+1:]
            p+=1
    elif a[i+p] == 'e':
        k = random.randint(1,3)
        if k == 1:
            a = a[:i+p]+'ew'+a[i+p+1:]
            p+=1
    elif a[i+p] == ' ':
        k = random.randint(1,9)
        if k == 1 or k == 7:
            a = a[:i+p]+' ~ '+a[i+p+1:]
            p+=2
        elif k == 2:
            a = a[:i+p]+' ~uwu~ '+a[i+p+1:]
            p+=6
        elif k >= 3 and k < 7:
            continue
        else:
            a = a[:i+p]+' ~ '+a[i+p+1:]
            p+=2

print(a+' <3')