def q1():
    number = input("Enter your binary number: ")
    decimal_value = 0
    for i in range(len(number)):
        decimal_value += int(number[i]) * (2 ** (len(number) - 1 - i))
    return decimal_value
result = q1()
print(f"The decimal value is: {result}")