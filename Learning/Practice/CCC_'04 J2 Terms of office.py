s = int(input())
e = int(input())
d = s
print('All positions change in year '+str(s))
while True:
    d = d+60
    if d <= e:
        print('All positions change in year '+str(d))
    else: 
        break
