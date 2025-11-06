def main(input1):
    inp = input1.split(" ")
    needed = int(inp[0])-1
    inp.pop(0);
    string = ''
    for i in inp:
        string += i.upper()
    total = ["ZZ" for i in range(len(string))]
    currentletter = "ZZ"
    counter = 0
    for i in range(len(string)):
        total.append(string[i])
        total.sort();
        if total[needed] != currentletter:
            counter += 1
            currentletter = total[needed]
    print(counter)

main()

