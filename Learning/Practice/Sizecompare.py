start = int(input())

while True:
    a = int(input())
    if start > a:
        start += a
    else:
        break
print(start)