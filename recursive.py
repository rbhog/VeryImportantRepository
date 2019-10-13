def rec(num):
  if num <= 1:
    return num
  else:
    return (rec(num-1) + rec(num-2))


print(rec(55))
