
# Given an array of integers, find the two of them that sum to 2020 and return the product of them

numbers = []
numbers_hash = {}
f = open("day1-input.txt", "r")
for x in f:
  numbers_hash[int(x)] = True
  numbers.append(int(x))

# Brute force:
# Take every combination of two numbers in the list and add them up to see if the sum is 2020
# If it is, then we're done
# O(n^2) - for each number, we have to loop through other numbers

# Idea 1:
# Create a hash of the numbers
# Loop through each number in the list.
# Let x = 2020 minus the number
# if x is in the hash, then we found the second number
# O(n) - loop through all of the numbers in the worst case

# for number in numbers:
#   x = 2020 - number
#   if x in numbers_hash:
#     # We found our number!
#     print(x)
#     print(number)
#     print(number * x)

# Given an array of integers, find the three of them that sum to 2020 and return the product of them

# Brute force:
# Take every combination of three number sin the list and add them to see if sum is 2020
# If it is, then we're done
# O(n^3) - for each of 3 numbers, have to loop through the rest

# Universe is the set of all combinations of 3 numbers
# Idea 1:
# Sum all combinations of 2 numbers  and apply same hashing thing as above
# O(n^2)

# Idea 2:
# Sort the numbers??
# Sum the 3 lowest
# If its less than 2020,
# Move to next highest

index1 = 0
index2 = 1
index3 = 2

# Start 0, 1, 2
# 0, 1, 3
# 0, 2, 3
# 1, 2, 3
# 1, 2, 4
# 1, 3, 4
# 2, 3, 4
# ...
# length of array - 3, length of array - 2, length of array - 1

# 0, 40, 45

from itertools import combinations

comb = combinations(numbers, 3)

for c in comb:
  if c[0] + c[1] + c[2] == 2020:
    print(c)
    print(c[0] * c[1] * c[2])

#
# numbers.sort()
#
# num1 = numbers[index1]
# num2 = numbers[index2]
# num3 = numbers[index3]
#
# counter = 0
#
# sum = num1 + num2 + num3
#
# while sum !== 2020:
#   if index3 > len(numbers):
#     break
#
#   if counter % 3 == 0:
#     num3 ++
#
#   if counter % 3 == 2
