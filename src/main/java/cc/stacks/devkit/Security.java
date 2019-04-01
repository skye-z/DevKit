package cc.stacks.devkit;

import java.util.Base64;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.net.URLCodec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 安全工具
 */
@SuppressWarnings("all")
public class Security {

    private static Base32 base32 = new Base32();

    private static URLCodec urlCodec = new URLCodec();

    // 转换16进制
    public static String stringToHex(String Text) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder Hex = new StringBuilder();
        byte[] bs = Text.getBytes();
        int bit;
        for (byte b : bs) {
            bit = (b & 0x0f0) >> 4;
            Hex.append(chars[bit]);
            bit = b & 0x0f;
            Hex.append(chars[bit]);
        }
        return Hex.toString().trim();
    }

    // 还原16进制
    public static String hexToString(String Hex) {
        String str = "0123456789ABCDEF";
        char[] hexs = Hex.toCharArray();
        byte[] bytes = new byte[Hex.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

    // MD5 摘要
    public static String MDDigest(boolean Reduction, String Text) {
        if (Reduction)
            return DigestUtils.md2Hex(Text);
        return DigestUtils.md5Hex(Text);
    }

    // SHA 摘要
    public static String SHADigest(int Level, String Text) {
        if (Level == 1)
            return DigestUtils.sha1Hex(Text);
        else if (Level == 256)
            return DigestUtils.sha256Hex(Text);
        else if (Level == 384)
            return DigestUtils.sha384Hex(Text);
        else if (Level == 512)
            return DigestUtils.sha512Hex(Text);
        else
            return DigestUtils.sha1Hex(Text);
    }

    // Unicode 编码
    public static String UnicodeEncode(String Text) {
        StringBuilder Unicode = new StringBuilder();
        for (char Char : Text.toCharArray())
            Unicode.append("\\u").append(Integer.toHexString(Char));
        return Unicode.toString();
    }

    // Unicode 解码
    public static String UnicodeDecode(String Code) {
        StringBuilder Unicode = new StringBuilder();
        String[] Hex = Code.split("\\\\u");
        for (int i = 1; i < Hex.length; i++) {
            int data = Integer.parseInt(Hex[i], 16);
            Unicode.append((char) data);
        }
        return Unicode.toString();
    }

    // URLCode 编码
    public static String URLCodeEncode(String Text) {
       try {
           return urlCodec.encode(Text);
       }catch (Exception e){
           return null;
       }
    }

    // URLCode 解码
    public static String URLCodeDecode(String Code) {
        try {
            return urlCodec.decode(Code);
        }catch (Exception e){
            return null;
        }
    }

    // Base32 编码
    public static String Base32Encode(String Text) {
        byte[] EncodeBytes = base32.encode(Text.getBytes());
        return new String(EncodeBytes);
    }

    // Base32 解码
    public static String Base32Decode(String Code) {
        byte[] DecodeBytes = base32.decode(Code.getBytes());
        return new String(DecodeBytes);
    }

    // Base64 编码
    public static String Base64Encode(String Text) {
        byte[] EncodeBytes = Base64.getEncoder().encode(Text.getBytes());
        return new String(EncodeBytes);
    }

    // Base64 解码
    public static String Base64Decode(String Code) {
        byte[] DecodeBytes = Base64.getDecoder().decode(Code.getBytes());
        return new String(DecodeBytes);
    }

    // 密钥生成器
    public static String KeyGenerator(String Type, int Long) {
        try {
            // 创建密钥生成器对象
            KeyGenerator keyGenerator = KeyGenerator.getInstance(Type);
            // 指定密钥长度
            keyGenerator.init(Long);
            // 生成密钥
            SecretKey SecretKey = keyGenerator.generateKey();
            // 转换16进制
            return Hex.encodeHexString(SecretKey.getEncoded());
        } catch (Exception e) {
            return null;
        }
    }

    // 对称算法加密
    public static String SymmetricEncode(String Type, String Message, String Key) {
        try {
            // 创建加密对象
            Cipher cipher = Cipher.getInstance(Type + "/ECB/PKCS5Padding");
            if (Type.equals("DES")) {
                // 创建密钥工厂
                SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(Type);
                // 创建密钥转换器
                DESKeySpec DesKeySpec = new DESKeySpec(Hex.decodeHex(Key.toCharArray()));
                // 初始化加密对象
                cipher.init(Cipher.ENCRYPT_MODE, secretKeyFactory.generateSecret(DesKeySpec));
            } else if (Type.equals("AES")) {
                // 初始化加密对象
                cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(Hex.decodeHex(Key.toCharArray()), "AES"));
            }
            // 执行加密操作
            byte[] resultBytes = cipher.doFinal(Message.getBytes());
            // 转换16进制
            return Hex.encodeHexString(resultBytes);
        } catch (Exception e) {
            return null;
        }
    }

    // 对称算法解密
    public static String SymmetricDecode(String Type, String Message, String Key) {
        try {
            // 创建解密对象
            Cipher cipher = Cipher.getInstance(Type + "/ECB/PKCS5Padding");
            if (Type.equals("DES")) {
                // 创建密钥转换器
                DESKeySpec DesKeySpec = new DESKeySpec(Hex.decodeHex(Key.toCharArray()));
                // 创建密钥工厂
                SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(Type);
                // 初始化解密对象
                cipher.init(Cipher.DECRYPT_MODE, secretKeyFactory.generateSecret(DesKeySpec));
            } else if (Type.equals("AES")) {
                // 初始化加密对象
                cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(Hex.decodeHex(Key.toCharArray()), "AES"));
            }
            // 还原进制
            byte[] result = Hex.decodeHex(Message.toCharArray());
            // 执行解密操作
            return new String(cipher.doFinal(result));
        } catch (Exception e) {
            return null;
        }
    }

//    public static void main(String[] args) {
//        System.out.println("==================== 转换 ====================");
//        System.out.println("Hex:");
//        String HexCode = Security.stringToHex("测试");
//        System.out.println("转码 " + HexCode);
//        System.out.println("还原 " + Security.hexToString(HexCode));
//
//        System.out.println("\n\n==================== 摘要 ====================");
//        System.out.println("MD:");
//        System.out.println("MD2 " + Security.MDDigest(true, "测试"));
//        System.out.println("MD5 " + Security.MDDigest(false, "测试"));
//
//        System.out.println("\nSHA:");
//        System.out.println("SHA1 " + Security.SHADigest(1, "测试"));
//        System.out.println("SHA256 " + Security.SHADigest(256, "测试"));
//        System.out.println("SHA384 " + Security.SHADigest(384, "测试"));
//        System.out.println("SHA512 " + Security.SHADigest(512, "测试"));
//
//        System.out.println("\n\n=================== 编码 ====================");
//        System.out.println("Unicode:");
//        String UnicodeCode = Security.UnicodeEncode("测试");
//        System.out.println("编码 " + UnicodeCode);
//        System.out.println("解码 " + Security.UnicodeDecode(UnicodeCode));
//
//        System.out.println("\nURLCode:");
//        String URLCodeCode = Security.URLCodeEncode("测试");
//        System.out.println("编码 " + URLCodeCode);
//        System.out.println("解码 " + Security.URLCodeDecode(URLCodeCode));
//
//        System.out.println("\nBase32:");
//        String Base32Code = Security.Base32Encode("测试");
//        System.out.println("编码 " + Base32Code);
//        System.out.println("解码 " + Security.Base32Decode(Base32Code));
//
//        System.out.println("\nBase64:");
//        String Base64Code = Security.Base64Encode("测试");
//        System.out.println("编码 " + Base64Code);
//        System.out.println("解码 " + Security.Base64Decode(Base64Code));
//
//        System.out.println("\n\n=================== 加密 ====================");
//        System.out.println("\nDES:");
//        String DESKey = KeyGenerator("DES", 56);
//        String DESTest = SymmetricEncode("DES", "测试", DESKey);
//        System.out.println("密钥: " + DESKey);
//        System.out.println("加密: " + DESTest);
//        System.out.println("解密: " + SymmetricDecode("DES", DESTest, DESKey));
//
//        System.out.println("\nAES:");
//        String AESKey = KeyGenerator("AES", 128);
//        String AESTest = SymmetricEncode("AES", "测试", AESKey);
//        System.out.println("密钥: " + AESKey);
//        System.out.println("加密: " + AESTest);
//        System.out.println("解密: " + SymmetricDecode("AES", AESTest, AESKey));
//    }
}
