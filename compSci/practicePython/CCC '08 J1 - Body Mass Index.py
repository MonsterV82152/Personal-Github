a = float(input())
b = float(input())
if a/(b*b) > 25:
    print('Overweight')
elif a/(b*b) < 18.5:
    print('Underweight')
else:
    print('Normal weight')


