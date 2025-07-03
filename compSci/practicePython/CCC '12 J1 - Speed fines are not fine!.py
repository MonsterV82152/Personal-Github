speed = int(input())
current = int(input())
if current <= speed:
    print('Congratulations, you are within the speed limit!')
elif current-speed <= 20 and current-speed > 0:
    print('You are speeding and your fine is $100.')
elif current-speed <= 30 and current-speed > 20:
    print('You are speeding and your fine is $270.')
elif current-speed > 30:
    print('You are speeding and your fine is $500.')