import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;
import java.io.*;
public class md5{
    static String	hashString(String	message,	String	algorithm)	{
			//	Compute	the	hash	value	of	message	using	algorithm	and
			//	return	a	string	representation	of	it.
			byte[]	hashedBytes	= null;	 //	will	store	the	hash	value	of	message
			try	{
						//	instantiate	the	specified	algorithm.	
						//	It	may	not	exist,	thus	the	try-catch
						MessageDigest	digest = MessageDigest.getInstance(algorithm);
						
						//	Compute	the	hash	value	of	message
						hashedBytes	=	digest.digest(message.getBytes("UTF-8"));
            }catch(NoSuchAlgorithmException|UnsupportedEncodingException e)	{
						e.printStackTrace();
			}
			//	Convert	hash	value	(in	byte[])	to	a	hex	String	and	return	result
			return	bToH(hashedBytes);
    }	//	hashString()
    static String	bToH(byte[]	value)	{
			//	Converts	value	to	a	string	of	hex	digits
			StringBuilder	sb	=	new	StringBuilder(value.length*2);	
			//	Why,length*2	?	See	Question	3.
			for	(byte	b	:	value)
						sb.append(String.format("%02x",	b));
			return	sb.toString();
    }	//	bToH()
    String	hashAFile(String filename,	String	algorithm)	{
			//	Hash	the	contents	of	the	file,	
			//	fileName	and	return	its	hash	value	as	a	hex	string.
			byte[]	hashedBytes	=null;					//	the	result
			try	{
				//	Open	the	file
				FileInputStream	inStream	=	new	FileInputStream(filename);
                //	Instantiate	a	digest	with	the	algorithm
						MessageDigest	digest	=	MessageDigest.getInstance(algorithm);
						//	Define	input	file	chunk	buffer	(of	1024	bytes)
						byte[]	buffer	=	new	byte[1024];
						int	bytesRead	=	-1;	//	counts	how	many	bytes	were	read
						while	((bytesRead	= inStream.read(buffer))!= -1)	{
									//	there	are	bytes	in	input	file	to	process
									//	supply	the	chunk	to	digest
									digest.update(buffer,0,bytesRead);
						}
						//	finalize	computation
						hashedBytes	=	digest.digest();
			}	catch	(NoSuchAlgorithmException	|	IOException	e)	{
						//	Catches	both:	algorithm	and	file	I/O	exceptions
						e.printStackTrace();
			}
			return	bToH(hashedBytes);	 	 //	Convert	bytes	to	hex	string
}	//	hashAFile()

    public	static	void main(String[]	args)	{
			String	msg	="It was the best of times, it was the worst of times.";
			String	hashValue	= hashString(msg,"MD5");
			System.out.println(hashValue+" "+msg);
            String hashValue2 = hashString(msg,"SHA-256");
			System.out.println(hashValue2+" "+msg);
            String name1 = "TwoCities.txt";
            String name2 = "Anassa.txt";
            String name3 = "moby10b.txt";
            String name4 = "SpongeBob.png";
            var reader = new md5();
            /*String fileValue = reader.hashAFile(name1,"MD5");
            System.out.println(fileValue+" "+name1);
            String fileValue2 = reader.hashAFile(name2,"MD5");
            System.out.println(fileValue2+" "+name2);
            String fileValue3 = reader.hashAFile(name3,"MD5");
            System.out.println(fileValue3+" "+name3);
            String fileValue4 = reader.hashAFile(name4,"MD5");
            System.out.println(fileValue4+" "+name4);
            
            
            String fileValue = reader.hashAFile(name1,"SHA-256");
            System.out.println(fileValue+" "+name1);
            String fileValue2 = reader.hashAFile(name2,"SHA-256");
            System.out.println(fileValue2+" "+name2);
            String fileValue3 = reader.hashAFile(name3,"SHA-256");
            System.out.println(fileValue3+" "+name3);
            String fileValue4 = reader.hashAFile(name4,"SHA-256");
            System.out.println(fileValue4+" "+name4);
            */
            String file1 = "/home/gtowell/Public/337/Lab10/shattered-1.pdf";
            String file2 = "/home/gtowell/Public/337/Lab10/shattered-2.pdf";
            System.out.println();
            String fileValue71 = reader.hashAFile(file1,"MD5");
            System.out.println("MD5:"+fileValue71+" "+file1);
            String fileValue72 = reader.hashAFile(file1,"SHA-256");
            System.out.println("SHA-256:"+fileValue72+" "+file1);
            String fileValue73 = reader.hashAFile(file1,"SHA1");
            System.out.println("SHA1:"+fileValue73+" "+file1);
            String fileValue74 = reader.hashAFile(file2,"MD5");
            System.out.println("MD5:"+fileValue74+" "+file2);
            String fileValue75 = reader.hashAFile(file2,"SHA-256");
            System.out.println("SHA-256:"+fileValue75+" "+file2);
            String fileValue76 = reader.hashAFile(file2,"SHA1");
            System.out.println("SHA1:"+fileValue76+" "+file2);


    }	//	main()
}