#Task: This is a dice throwing program for a 6 sided dice. It uses the the random funcion to decide the outcome. It then checks if the user wants to roll again. 
import random
print ("Dice Thrower")
print()
again = True
while again == True :
	result = random.randint(1, 6)
	print ("You have rolled: ", result)
	ans = input("roll again?: ")
	if ("N" in ans or "n" in ans):
		again = False
		
		