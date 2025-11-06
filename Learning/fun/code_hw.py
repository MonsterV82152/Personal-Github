principale = 10000
monthly_ir = (5/12)*0.01
years = 5

monthly = principale * monthly_ir * ( (((1 + monthly_ir) ** (years*12)) )/ ( (1+monthly_ir) ** (years*12) - 1) ) 
print(round(monthly,2))