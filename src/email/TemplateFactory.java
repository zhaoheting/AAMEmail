package email;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class TemplateFactory {

    // 模板文件路径
    private static String templatePath = "/template";

    // 模板文件内容编码
    private final static String ENCODING = "utf-8";

    // 模板生成配置
    private static Configuration conf = new Configuration();

//    static {
//        // 设置模板文件路径
//        conf.setClassForTemplateLoading(TemplateFactory.class, templatePath);
//    }

    public static String generateHtmlFromFtl(String name, Map<String, String> map) throws IOException, TemplateException {
        Writer out = new StringWriter();
        Template temp = conf.getTemplate("/src/template/" + name);
//        temp.setEncoding(ENCODING);
        temp.setOutputEncoding(ENCODING);
        temp.process(map, out);
        return out.toString();
    }
}
