#Task: This code outputs works out if the year entered by the user was a lap year or not. 
year = eval( input( "Enter a year: "))
if (year % 4) == 0:
	if (year % 100) == 0:
		if (year % 400) == 0:
			print(year, "is a leap year")
		else:
			print(year, "is not a leap year")
	else:
		print(year, "is a leap year")
else:
	print(year, "is not a leap year")