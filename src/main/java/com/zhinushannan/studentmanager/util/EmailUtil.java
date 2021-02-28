package com.zhinushannan.studentmanager.util;

import java.security.Security;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.sun.net.ssl.internal.ssl.Provider;

/**
 * @author zhinushannan
 * @date 2020/12/19 10:37
 * @subject
 */
public class EmailUtil {
    /**
     * 用来发送邮件的方法
     * @param address 收件人地址
     * @param content 邮件的内容
     */
    public static void sendMail(String address, String content) {
        try {
            // 设置SSL连接、邮件环境
            Security.addProvider(new Provider());
            final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
            Properties props = System.getProperties();
            props.setProperty("mail.smtp.host", "smtp.qq.com");
            props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.socketFactory.port", "465");
            props.setProperty("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("1377875184@qq.com", "suawztjwchmaigac"); // 账户 授权码
                }
            });

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("1377875184@qq.com"));
            message.setRecipients(Message.RecipientType.TO, address);
            message.setSubject("学生信息管理系统密码重置验证码");
            message.setContent("尊敬的用户您好，您的重置验证码为：" + content + "。请勿泄露此验证码！", "text/html;charset=UTF-8");
            message.saveChanges();
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
