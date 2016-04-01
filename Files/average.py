#Task: Find the average between a user submitted amount of numbers
n = eval (input ("Number of Numbers?: "))
sum = 0
for i in range(n):
	sum = sum + eval (input ("Enter Number: "))
average = sum / n
print(" Average: ", average)