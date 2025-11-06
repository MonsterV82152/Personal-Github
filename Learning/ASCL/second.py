from sympy import isprime # type: ignore
def goldbach(inp) -> list:
    nList = []
    try:
        if int(inp) <= 3 or int(inp) % 2 != 0:
            nList.append("Invalid Input")
            return nList
        n = int(inp)
        while True:
            if isprime(n):
                if isprime(int(inp)-n):
                    nList.append(n)
                    nList.append(int(inp)-n)
                    return(nList)
            n-=1
    except TypeError:
        return ["Invalid Input"]
def main():
    # print(isprime(999_999_929))
    inp = None
    while inp != "exit":
        inp = input("Input: ")
        print(goldbach(inp))
    
main()