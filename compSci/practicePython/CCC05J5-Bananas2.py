inp = []
while True:
    inp.append(input())
    if inp[len(inp)-1] == 'X':
        inp.pop()
        break
def opopo(w):
    if w.find('b') == 0:
        if w.find('s') == len(w)-1:
            if opopo():
                return(True)
        else