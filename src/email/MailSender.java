package email;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class MailSender {
    private MimeMessage mimeMessage; // MIME邮件对象
    private Session session; // 邮件会话对象
    private Properties props; // 系统属性
    private Multipart mimeMultipart; // Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成
    private String username;// 发件人的用户名
    private String password;// 发件人的密码
    private String nickname;// 发件人的昵称

    public MailSender() {
        props = System.getProperties();
        session = Session.getDefaultInstance(props, null);
        // 创建MIME邮件对象
        mimeMessage = new MimeMessage(session);
        mimeMultipart = new MimeMultipart();
//        setSmtpHost(smtp);
//        createMimeMessage();
    }

    public void setSmtpHost(String hostName) {
//        if (props == null)
//            props = System.getProperties();
        props.put("mail.smtp.host", hostName);
    }

//    public void createMimeMessage() {
//        // 获得邮件会话对象
//        session = Session.getDefaultInstance(props, null);
//        // 创建MIME邮件对象
//        mimeMsg = new MimeMessage(session);
//        mp = new MimeMultipart();
//    }

    public void setNeedAuth(boolean need) {
        if (props == null)
            props = System.getProperties();
        if (need) {
            props.put("mail.smtp.auth", "true");
        } else {
            props.put("mail.smtp.auth", "false");
        }
    }

    public void setSubject(String subject) throws UnsupportedEncodingException, MessagingException {
        mimeMessage.setSubject(MimeUtility.encodeText(subject, "utf-8", "B"));
    }

    public void setBody(String mailBody) throws MessagingException {
        BodyPart bp = new MimeBodyPart();
        bp.setContent("" + mailBody, "text/html;charset=utf-8");
        mimeMultipart.addBodyPart(bp);
    }

    public void setSender(String sender) throws UnsupportedEncodingException, AddressException, MessagingException {
        nickname = MimeUtility.encodeText(nickname, "utf-8", "B");
        mimeMessage.setFrom(new InternetAddress(nickname + " <" + sender + ">"));
    }

    public void setReceiver(String receiver) throws AddressException, MessagingException {
        mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
    }

    public void sendout() throws MessagingException {
        mimeMessage.setContent(mimeMultipart);
        mimeMessage.saveChanges();
        Session mailSession = Session.getInstance(props, null);
        Transport transport = mailSession.getTransport("smtp");
        transport.connect((String) props.get("mail.smtp.host"), username, password);
        transport.sendMessage(mimeMessage, mimeMessage.getRecipients(Message.RecipientType.TO));
        transport.close();
    }

    public void setNamePass(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }
}
