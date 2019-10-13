def rec(num):
  if num <= 1:
    return num
  else:
    return (rec(n-1) + rec(n-2))


print(rec(55))
