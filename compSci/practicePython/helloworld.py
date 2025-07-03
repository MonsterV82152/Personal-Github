import time
char = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz "
inp = input()
current = ''
for i in inp:
    for j in char:
        if i == j:
            current = current+j
            print("\n\n\n\n\n\n\n\n\n\n\n"+current)
            time.sleep(0.005)
            break
        else:
            print("\n\n\n\n\n\n\n\n\n\n\n"+current+j)
            time.sleep(0.005)

        