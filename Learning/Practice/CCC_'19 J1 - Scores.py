in1 = int(input())
in2 = int(input())
in3 = int(input())
in4 = int(input())
in5 = int(input())
in6 = int(input())
score1 = in1*3
score1 = in2*2+score1
score1 = in3+score1
score2 = in4*3
score2 = in5*2+score2
score2 = in6+score2
if score2 > score1:
    print('B')
elif score2 < score1:
    print('A')
elif score2 == score1:
    print('T')