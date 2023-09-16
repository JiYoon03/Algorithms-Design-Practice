import hashlib

text = 'Hello!'

m = hashlib.md5()
print(m.hexdigest())
m.update(b"Have Fun!")
print(m.hexdigest())
m.update(text.encode('UTF-8'))
print(m.hexdigest())