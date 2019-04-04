package cc.stacks.devkit;

import com.google.gson.Gson;

/**
 * 身份令牌工具
 */
@SuppressWarnings("all")
public class Token<T> {

    // 内嵌数据
    private T Data;

    // 签发时间
    private long Time;

    // 失效时长
    private int Invalid;

    // 签发类型
    private String Type;

    public Token(T Data,int Invalid,String Type){
        this.Data = Data;
        this.Invalid = Invalid;
        this.Type = Type;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    public long getTime() {
        return Time;
    }

    public void setTime(long time) {
        Time = time;
    }

    public int getInvalid() {
        return Invalid;
    }

    public void setInvalid(int invalid) {
        Invalid = invalid;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    // 签发
    public String Issuance(String Key){
        try {
            this.Time = System.currentTimeMillis();
            // 创建Gson对象
            Gson gson = new Gson();
            // 加密数据
            String AESCode = Security.SymmetricEncode("AES", gson.toJson(this), Key);
            // 混淆密文
            return Security.Base64Encode(AESCode + "." + Security.MDDigest(false,AESCode));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // 验证
    public static Token Analysis(String Code, String Key) {
        try {
            // 创建Gson对象
            Gson gson = new Gson();
            // Base64解码
            String Base64Code = Security.Base64Decode(Code);
            // 分割AES密文
            String AESCode = Base64Code.substring(0, Base64Code.indexOf("."));
            // 分割MD5值
            String Md5Code = Base64Code.substring(Base64Code.indexOf(".") + 1);
            // 判断数据是否正确
            if (Security.MDDigest(false,AESCode).equals(Md5Code)) {
                // 解密身份令牌
                Token token = gson.fromJson(Security.SymmetricDecode("AES", AESCode, Key),Token.class);
                // 判断是否有效
                if (token.Time+token.Invalid>System.currentTimeMillis())
                    return token;
                token.setData(null);
                return token;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    public static void main(String[] args) {
//        String Key = Security.SymmetricKeyGenerator("AES", 128);
//        Map<String, String> Account = new HashMap<>();
//        Account.put("Id", "1");
//        Account.put("Name", "Name");
//        Account.put("Identity", "2");
//        Token token = new Token(Account, 0, "WebService");
//        String Code = token.Issuance(Key);
//        token = Token.Analysis(Code,Key);
//        System.out.println("Key: "+Key);
//        System.out.println("Token: "+Code);
//        System.out.println("Data: "+token.getData());
//
//    }

}
