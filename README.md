# Stacks Dev Kit v2

项目包名: cc.stacks.devkit

历史包名: cc.stacks.developer

## 使用方法

``` xml
<repositories>
    <repository>
        <id>stacks-dev-kit</id>
        <url>https://raw.github.com/skai-zhang/DevKit/mvn-repo</url>
    </repository>
</repositories>

<dependency>
    <groupId>cc.stacks</groupId>
    <artifactId>devkit</artifactId>
    <version>Bulid.1</version>
</dependency>
```

## 工具列表

1. [安全工具](#安全工具)
    * [字符串转16进制](#字符串转16进制)
    * [16进制转字符串](#16进制转字符串)
    * [MD 摘要](#md-摘要)
    * [SHA 摘要](#sha-摘要)
    * [Unicode 编码](#unicode-编码)
    * [Unicode 解码](#unicode-解码)
    * [URLCode 编码](#urlcode-编码)
    * [URLCode 解码](#urlcode-解码)
    * [Base32 编码](#base32-编码)
    * [Base32 解码](#base32-解码)
    * [Base64 编码](#base64-编码)
    * [Base64 解码](#base64-解码)
    * [密钥生成器](#密钥生成器)
    * [对称算法加密](#对称算法加密)
    * [对称算法解密](#对称算法解密)
2. [日志工具](#日志工具)

### 安全工具

Class: Security

#### 字符串转16进制

stringToHex(String Text)

参数: Text(转换字符)

#### 16进制转字符串

hexToString(String Hex)

参数: Text(转换字符)

#### MD 摘要

MDDigest(boolean Reduction, String Text)

参数: Reduction(是否降级), Text(摘要字符)

> 未降级是MD5摘要, 降级后为MD2摘要

#### SHA 摘要

SHADigest(int Level, String Text)

参数: Level(算法级别), Text(摘要字符)

> 级别1, SHA1
> 
> 级别256, SHA256
> 
> 级别384, SHA384
> 
> 级别512, SHA512
> 
> 填写其他的默认为SHA1

#### Unicode 编码

UnicodeEncode(String Text)

参数: Text(编码字符)

#### Unicode 解码

UnicodeDecode(String Code)

参数: Code(解码字符)

#### URLCode 编码

URLCodeEncode(String Text)

参数: Text(编码字符)

#### URLCode 解码

URLCodeDecode(String Code)

参数: Code(解码字符)

#### Base32 编码

Base32Encode(String Text)

参数: Text(编码字符)

#### Base32 解码

Base32Decode(String Code)

参数: Code(解码字符)

#### Base64 编码

Base64Encode(String Text)

参数: Text(编码字符)

#### Base64 解码

Base64Decode(String Code)

参数: Code(解码字符)

#### 密钥生成器

KeyGenerator(String Type, int Long)

参数: Type(密钥类型), Long(密钥长度)

> 类型支持: AES, DES

#### 对称算法加密

SymmetricEncode(String Type, String Message, String Key)

参数: Type(密钥类型), Message(加密内容), Key(密钥)

> 类型支持: AES, DES

#### 对称算法解密

SymmetricDecode(String Type, String Message, String Key)

参数: Type(密钥类型), Message(解密内容), Key(密钥)

> 类型支持: AES, DES