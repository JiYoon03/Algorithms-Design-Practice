import hashlib

msg	= b"Bryn Mawr"
digest	= hashlib.new("md5")#	creates	an	instance	of	MD5
digest.update(msg)#adds	msg	to	hash	value	computation
digest.digest()#	completes	computation,	returns	result
#print(digest.digest()) -->b'\x03\xb8qF\xec\x1c]\xd0<x\x986\xdaM\xb7\xc2'
#print(digest.hexdigest())-->03b87146ec1c5dd03c789836da4db7c2
#print(hashlib.algorithms_available)
def	hashAFile(fileName,	algorithm):
	#Hash	the	contents	of	the	file,	filename	using	algorithm,
    #and	return	its	hash	value	as	a	hex	string.
    bufferSize	=	1024
    digest	=	hashlib.new(algorithm)
    with	open(fileName,	"rb")	as	inStream:
        buffer = inStream.read(bufferSize)#read bufferSize bytes
        while len(buffer):
            digest.update(buffer)	 #	add	to	digest
            buffer	=	inStream.read(bufferSize)
    return digest.hexdigest()


def hashString(message, algorithm):
    msg2 = message
    digest2 = hashlib.new(algorithm)
    digest2.update(msg2)
    #print(digest2.digest())
    return digest2.hexdigest()

msg	=	b"It was the best of times, it was the worst of times."
hashValue	=	hashString(msg,	"MD5")
print(hashValue," ",msg)
hashValue2	=	hashString(msg,	"SHA256")
print(hashValue2," ",msg)
name1 = "TwoCities.txt"
name2 = "Anassa.txt"
name3 = "moby10b.txt"
name4 = "SpongeBob.png"
fileValue = hashAFile(name1,"MD5")
print(fileValue," ",name1)
fileValue2 = hashAFile(name2,"MD5")
print(fileValue2," ",name2)
fileValue3 = hashAFile(name3,"MD5")
print(fileValue3," ",name3)
fileValue4 = hashAFile(name4,"MD5")
print(fileValue4," ",name4)

fileValueA = hashAFile(name1,"SHA256")
print(fileValueA," ",name1)
fileValueA2 = hashAFile(name2,"SHA256")
print(fileValueA2," ",name2)
fileValueA3 = hashAFile(name3,"SHA256")
print(fileValueA3," ",name3)
fileValueA4 = hashAFile(name4,"SHA256")
print(fileValueA4," ",name4)
