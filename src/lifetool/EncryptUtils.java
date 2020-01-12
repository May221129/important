package lifetool;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.junit.Test;	

public class EncryptUtils {
	
	private static final String ENCRPT_WAY = "AES/ECB/PKCS5Padding";
	
	private static final String ENCRPT_SUB = ".over";
	
	private static final String PATH = "I:\\DCIM\\100MSDCF";
	
    @Test
    public void testGenKey() throws Exception{
    	genKey();
    }
    
    @Test
    public void testEncrypt() throws Exception {
    	String keyStr = "QhJNXcrBJ1GiM5K9wi3wfA==";
    	
    	byte[] encrypt = encrypt("�yԇ�ַ���".getBytes(), keyStr);
    	
    	decrypt(encrypt, keyStr);
    }
	
    
    // �����
    public static void main(String[] args) {
    	if (args == null || args.length <= 0) {
    		System.out.println("��Կû��");
    		return;
    	}
    	encryptSource(new File(PATH), args[0]);
    	
//    	decryptSource(new File(PATH), args[0]);
    	
    	System.out.println("success");
	}
    
    // �����ĵ�
    private static void encryptSource(File file, String key) {
    	if (!file.exists()) {
    		System.out.println("�ļ�������");
    	}
    	if (file.isFile()) {
    		encryptFile(file, key);
    		System.out.println(file.getName());
    	} else {
    		for (File f : file.listFiles()) {
    			encryptSource(f, key);
    		}
    	}
    }
    
    // �����ĵ�
    private static void decryptSource(File file, String key) {
    	if (!file.exists()) {
    		System.out.println("�ļ�������");
    	}
    	if (file.isFile()) {
    		decryptFile(file, key);
    		System.out.println(file.getName());
    	} else {
    		for (File f : file.listFiles()) {
    			decryptSource(f, key);
    		}
    	}
    }
    
    // �����ļ�
    private static void decryptFile(File file, String key) {
    	try {
    		if (!file.getName().contains(ENCRPT_SUB)) {
    			return;
    		}
    		
        	byte[] fileBytes = readFile(file);
        	if (fileBytes == null || fileBytes.length == 0) {
        		System.out.println(file.getName() + ":�ļ�����Ϊ0");
        		return;
        	}
        	byte[] result = decrypt(fileBytes, key);
        	
        	if (result != null && result.length > 0) {
        		updateTargetFile(result, new File(file.getAbsolutePath()
        				.substring(0, 
        						file.getCanonicalPath().lastIndexOf(ENCRPT_SUB))));
        	}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(file.getName() + ":����ʧ��");
		}
    }
    
    // �����ļ�
    private static void encryptFile(File file, String key) {
    	try {
    		if (file.getName().contains(ENCRPT_SUB)) {
    			return;
    		}
    		
        	byte[] fileBytes = readFile(file);
        	if (fileBytes == null || fileBytes.length == 0) {
        		System.out.println(file.getName() + ":�ļ�����Ϊ0");
        		return;
        	}
        	byte[] result = encrypt(fileBytes, key);
        	
        	if (result != null && result.length > 0) {
        		if (updateTargetFile(result, new File(file.getCanonicalFile() + ENCRPT_SUB))) {
        			file.delete();
        		}
        	}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(file.getName() + ":����ʧ��");
		}
    }
    
    // ����Ŀ���ļ�
    private static boolean updateTargetFile(byte[] result, File file) {
    	FileOutputStream fos = null;
    	try {
    		if (file.exists()) {
    			file.delete();
    		} else {
    			file.createNewFile();
    		}
    		
    		fos = new FileOutputStream(file);
    		fos.write(result);
    		fos.flush();
    		
    		return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(file.getName() + "�������ļ�ʧ��");
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    	return false;
    }
    
    // ��ȡ�ļ�
    private static byte[] readFile(File file)throws Exception {
    	BufferedInputStream bis = null;
    	try {
        	bis = new BufferedInputStream(new FileInputStream(file));
        	long fileLenght = file.length();
        	// ����ļ�����500MB�ͷ���
        	if (fileLenght > 1024*1024*500) {
        		System.out.println(file.getName() + ":�ļ�̫�������ܼ���");
        		return null;
        	}
        	
        	byte[] content = new byte[(int)fileLenght];
        	bis.read(content);
        	return content;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(file.getName() + "����");
		}finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    	return null;
    	
    }
    
    // ����
    private static byte[] encrypt(byte[] source, String secretKey){
    	try {
    		Key key = getKey(secretKey);
            // ����
            Cipher cipher = Cipher.getInstance(ENCRPT_WAY);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            
            return cipher.doFinal(cipher.doFinal(source));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���ܳ��e");
		}
    	return null;
    	
    }
    
    // ����
    private static byte[] decrypt(byte[] source, String secretKey) throws Exception{
    	Key key = getKey(secretKey);
    	
    	Cipher cipher = Cipher.getInstance(ENCRPT_WAY);
    	cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(cipher.doFinal(source));
    }
    

    
    // ������Կ
    private static void genKey() throws Exception {
        // ����KEY
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");            
        keyGenerator.init(128);
        // ������Կ
        SecretKey secretKey = keyGenerator.generateKey();
        // ��ȡ��Կ
        byte[] keyBytes = secretKey.getEncoded();
        
        geyKeyStr(keyBytes);
    }
    
    // �����Կ�ַ���
    private static void geyKeyStr(byte[] keyBytes) {
    	Base64.Encoder encoder = Base64.getEncoder();
    	System.out.println(encoder.encodeToString(keyBytes));
    }
    
    // ��ȡ��Կ����
    private static Key getKey(String keyStr) {
    	Base64.Decoder decoder = Base64.getDecoder();
    	return new SecretKeySpec(decoder.decode(keyStr), "AES");
    }
}
