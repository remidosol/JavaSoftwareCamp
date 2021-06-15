package kodlamaio.hrms.demo.core.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class JavaMailSenderConfig {

    @Value("${spring.mail.host}")
    private String mailHost;

    @Value("${spring.mail.port}")
    private int mailPort;

    @Value("${spring.mail.username}")
    private String mailUsername;

    @Value("${spring.mail.password}")
    private String mailPassword;

    @Bean(name = "mailSender")
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.mailHost);
        mailSender.setPort(this.mailPort);

        mailSender.setUsername(this.mailUsername);
        mailSender.setPassword(this.mailPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

//		MimeMessage message = sender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(message);
//
//		try {
//			helper.setTo(recipients);
//			helper.setSubject("Simple mail template");
//			helper.setText(someClass.generateMailHtml("Hi There"), true);
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}

        return mailSender;
    }
}
