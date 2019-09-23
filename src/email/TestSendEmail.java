package email;

import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestSendEmail {

    public static void main(String[] args) {
        System.out.println("test");
        String templateName = "template_1.ftl";
        Map<String, String> map = new HashMap<String, String>();
        map.put("content", "卧了个槽");
        try {
            MailOperation.sendMailByTemplate("Heting.Zhao@openjawtech.com", "The subject", map, "template_1.ftl");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}