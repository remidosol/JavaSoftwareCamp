package kodlamaio.hrms.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
public class TemplateEngineUtils {

    private TemplateEngine templateEngine;

    @Autowired
    public TemplateEngineUtils(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String generateMailHtml(String host, String port, String email, String token, String templateFileName) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("host", host);
        variables.put("port", port);
        variables.put("email", email);
        variables.put("token", token);

        return this.templateEngine.process(templateFileName, new Context(Locale.getDefault(), variables));
    }
}
