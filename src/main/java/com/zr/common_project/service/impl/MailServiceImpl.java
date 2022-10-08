package com.zr.common_project.service.impl;

import com.zr.common_project.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Author: zhouxin
 * Date: 2022/10/6
 * Time: 20:20
 * Description:
 */

@Slf4j
@Service
public class MailServiceImpl implements MailService {

    @Resource
    private JavaMailSender mailSender;

    @Value("${mail.fromMail.addr}")
    private String from;

    /**
     *  发送文字邮件
     * @Param: [to, subject, content]
     * @Return: void
     * @Author: zhouxin
     * @Date: 2022/10/7 8:40
     */
    @Override
    public void sendSimpleEmail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
            log.info("邮件已发送");
        }catch (Exception e){
            log.error("邮件发送失败+"+e.getMessage());
        }
    }

    /**
     *  发送带附件的邮件
     * @Param: [to, subject, content, filePath]
     * @Return: void
     * @Author: zhouxin
     * @Date: 2022/10/7 8:40
     */
    @Override
    public void sendAttachmentMail(String to, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setText(content, true);
            helper.setSubject(subject);
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);
            log.info("发送成功");
            mailSender.send(message);
        }catch (MessagingException e) {
            log.error("发送失败"+e.getMessage());
        }
    }

}
