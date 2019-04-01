# Stacks Dev Kit v2

项目包名: cc.stacks.devkit

历史包名: cc.stacks.developer

## 使用方法

本项目已同步Maven中央仓库, 在pom文件中加入下方代码即可

``` xml
<dependency>
  <groupId>cc.stacks</groupId>
  <artifactId>devkit</artifactId>
  <version>1.0.0-RELEASE</version>
</dependency>
```

若加入后无法下载, 请先确认是否使用的是Maven中央仓库, 若非, 请额外加入下方代码

``` xml
<repositories>
    <repository>
        <id>stacks-dev-kit</id>
        <url>https://raw.github.com/skai-zhang/DevKit/mvn-repo</url>
    </repository>
</repositories>
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
2. [日志构建工具](#日志构建工具)
3. [SQL参数构建工具](#sql参数构建工具)
3. [响应构建工具](#响应构建工具)
    * [简易成功响应](#简易成功响应)
    * [简易失败响应](#简易失败响应)
    * [含消息的成功响应](#含消息的成功响应)
    * [含消息的失败响应](#含消息的失败响应)
    * [含数据的成功响应](#含数据的成功响应)
    * [含数据的失败响应](#含数据的失败响应)
    * [含消息和数据的成功响应](#含消息和数据的成功响应)
    * [含消息和数据的失败响应](#含消息和数据的失败响应)

### 安全工具

Class: Security

#### 字符串转16进制

static stringToHex(String Text)

参数: Text(转换字符)

#### 16进制转字符串

static hexToString(String Hex)

参数: Text(转换字符)

#### MD 摘要

static MDDigest(boolean Reduction, String Text)

参数: Reduction(是否降级), Text(摘要字符)

> 未降级是MD5摘要, 降级后为MD2摘要

#### SHA 摘要

static SHADigest(int Level, String Text)

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

static UnicodeEncode(String Text)

参数: Text(编码字符)

#### Unicode 解码

static UnicodeDecode(String Code)

参数: Code(解码字符)

#### URLCode 编码

static URLCodeEncode(String Text)

参数: Text(编码字符)

#### URLCode 解码

URLCodeDecode(String Code)

参数: Code(解码字符)

#### Base32 编码

static Base32Encode(String Text)

参数: Text(编码字符)

#### Base32 解码

static Base32Decode(String Code)

参数: Code(解码字符)

#### Base64 编码

static Base64Encode(String Text)

参数: Text(编码字符)

#### Base64 解码

static Base64Decode(String Code)

参数: Code(解码字符)

#### 密钥生成器

static KeyGenerator(String Type, int Long)

参数: Type(密钥类型), Long(密钥长度)

> 类型支持: AES, DES

#### 对称算法加密

static SymmetricEncode(String Type, String Message, String Key)

参数: Type(密钥类型), Message(加密内容), Key(密钥)

> 类型支持: AES, DES

#### 对称算法解密

static SymmetricDecode(String Type, String Message, String Key)

参数: Type(密钥类型), Message(解密内容), Key(密钥)

> 类型支持: AES, DES

### 日志构建工具

#### 输出

static Output(int Level, int Code, String Message)

参数: Level(日志级别),Code(日志代码),Message(日志内容)

> 日志级别: 调试(1)、信息(2)、警告(3)和错误(4)

### SQL参数构建工具

#### 构建

static Build(Map<String, String> Parameters, List<String> Connect)

参数: Parameters(参数集合),Connect(连接符集合)

> 参数集合使用Map构建, Key为参数名称, Value为参数值
> 
> 连接符集合可以使用默认构建方法, 也可以自己用List构建

#### 生成And连接符

static AndConnect()

#### 生成Or连接符

static AndConnect()

### 响应构建工具

#### 简易成功响应

static Success()

#### 简易失败响应

static Failure()

#### 含消息的成功响应

static Success(String Message)

参数: Message(响应信息)

#### 含消息的失败响应

static Failure(String Message)

参数: Message(响应信息)

#### 含数据的成功响应

static Success(T Data)

参数: Data(响应数据)

#### 含数据的失败响应

static Failure(T Data)

参数: Data(响应数据)

#### 含消息和数据的成功响应

static Success(String Message,T Data)

参数: Message(响应信息),Data(响应数据)

#### 含消息和数据的失败响应

static Failure(String Message,T Data)

参数: Message(响应信息),Data(响应数据)