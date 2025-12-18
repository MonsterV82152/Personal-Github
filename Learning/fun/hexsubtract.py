
def subtracthex(hex1,pos,first):
    hexnums = "0123456789ABCDEF"
    if pos != 0:
        
        if hex1[pos] != '0':
            num = hex1[:pos]+hexnums[hexnums.index(hex1[pos])-1]+hex1[pos+1:]
        else:
            num = hex1[:pos]+'F'+hex1[pos+1:]
            num = subtracthex(num,pos-1,False)
            
    else:
        num = hex1[:pos]+hexnums[hexnums.index(hex1[pos])-1]+hex1[pos+1:]
    return num

def hexsubtract(hex1,hex2):
    num = ''
    hexnums = "0123456789ABCDEF"
    if len(hex2)<len(hex1):
        for i in range(len(hex1)-len(hex2)):
            hex2 = '0'+hex2
    for i in range(len(hex2)-1,-1,-1):
        if hexnums.index(hex1[i]) >= hexnums.index(hex2[i]):
            num = hexnums[hexnums.index(hex1[i])-hexnums.index(hex2[i])]+num
        else:
            hex1 = subtracthex(hex1,i-1,True)
            num = hexnums[hexnums.index(hex1[i])-hexnums.index(hex2[i])]+num
    for i in range(len(num)):
        if num[i] == '0':
            num = num[1:]
        else:
            break
    
    return(num)

a = input()
b = input()
print(hexsubtract(a,b))
