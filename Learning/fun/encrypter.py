abc = ['!','g','c','d','p','i','b','h',' ','f','j','k','l','n','m','o','e','q','r','s','t','u','v','w','x','y','z','A','B','_','D','E','F','G','H','I','J','K','L','N','M','|','P','Q','R','S','T','U','V','=','X','Y','Z','^','W','-','C','O','.','?',')','(','@','*','&','$','a','+']
daily = 9


while True:
    
    messages = []
    a = input()
    while a != 'done':
        messages.append(a)
        a = input()
    number = daily
    for inpu in messages:
        number = daily
        if '›' not in inpu:
            newstring = []
            for i in inpu:
                if i in abc:
                    if abc[(abc.index(i)+number) % (len(abc))] == '*':
                        newstring.append('\*')
                    elif abc[(abc.index(i)+number) % (len(abc))] == '_':
                        newstring.append('\_')
                    else:

                        newstring.append(abc[(abc.index(i)+number) % (len(abc))])
                    number+=1
                else:
                    newstring.append(i)
            newstring2 = []
            for i in newstring:
                newstring2.insert(0,i)
            newstring2.append('›')


            for i in newstring2:
                print(i,end='')
            print('')

        else:
            # messages = []
            # lastnum = 0
            # for i in range(len(inpu)):
            #     if inpu[i] == '›':
            #         messages.append(inpu[lastnum:i])
            # for i in messages:
            #     print(i)
            #     print('')

            # while True:
            newstring = inpu[:(len(inpu)-1)]
            newstring2 = []
            for i in newstring:
                newstring2.insert(0,i)
            newstring3 = []
            for i in newstring2:
                if i in abc:
                    newstring3.append(abc[(abc.index(i)-number) % (len(abc))])
                    number+=1
                else:
                    newstring3.append(i)
            for i in newstring3:
                print(i,end='')
            print('')
    print("\n\n\n")


