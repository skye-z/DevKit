package cc.stacks.devkit;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 邮件工具
 */
@SuppressWarnings("all")
public class Mail {

    private Session session;

    private Transport transport;

    private String Name;

    private String Address;

    public Mail(String SmtpHost,String Name,String Address,String PassWord){
        try {
            this.Name = Name;
            this.Address = Address;
            Properties Prop = new Properties();
            Prop.setProperty("mail.host", SmtpHost);
            Prop.setProperty("mail.transport.protocol", "smtp");
            Prop.setProperty("mail.smtp.auth", "true");
            session = Session.getInstance(Prop);
            session.setDebug(false);
            transport = session.getTransport();
            transport.connect(SmtpHost, Address, PassWord);
        }catch (Exception e){
            BuildLogs.Error(100001,"创建邮件对象时发生错误:"+e.getMessage(),Mail.class);
        }
    }

    public void Send(String ToAddress, String ToName, String Title, String Content){
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Address,Name, "UTF-8"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(ToAddress,ToName));
            message.setSubject(Title);
            message.setContent(Content, "text/html;charset=UTF-8");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }catch (Exception e){
            BuildLogs.Error(100001,"发送邮件时发生错误:"+e.getMessage(),Mail.class);
        }
    }

    public static void main(String[] args) {
        Mail mail = new Mail("hwsmtp.exmail.qq.com","Stacks Message Service","service@stacks.cc","Zsk19981112");
        mail.Send("zshukai@foxmail.com","sKai","测试DevKit","测试内容");
    }

}
