package email;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class MailOperation {

    private final static String USER_NAME = "nilaogong.tingge@qq.com";
    private final static String PASSWORD = "omojwjotlbozcada";
    private final static String HOST_SERVER = "smtp.qq.com";
    private final static String SENDER_ADDRESS = "nilaogong.tingge@qq.com";
    private final static String ENCODING = "utf-8";

    public static String generateHtmlFromFtl(String name, Map<String, String> map) throws IOException, TemplateException {
        Writer out = new StringWriter();
        Configuration conf = new Configuration();
        Template temp = conf.getTemplate("/src/template/" + name);
        temp.setOutputEncoding(ENCODING);
        temp.process(map, out);
        return out.toString();
    }

    public static void sendMailByTemplate(String receiver, String subject, Map<String, String> map, String templateName)
            throws IOException, TemplateException, MessagingException {
        MailSender mailSender = new MailSender();
        mailSender.setSmtpHost(HOST_SERVER);
        mailSender.setNeedAuth(true);
        mailSender.setNamePass(USER_NAME, PASSWORD, "nidaye");
        String maiBody = generateHtmlFromFtl(templateName, map);
        mailSender.setSubject(subject);
        mailSender.setBody(maiBody);
        mailSender.setReceiver(receiver);
        mailSender.setSender(SENDER_ADDRESS);
        mailSender.sendout();
    }
}
