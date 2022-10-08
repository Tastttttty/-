package com.zr.common_project.service;

import org.springframework.stereotype.Service;

/**
 * Author: zhouxin
 * Date: 2022/10/6
 * Time: 20:18
 * Description:
 */


public interface MailService {
    public void sendSimpleEmail (String to, String subject, String content);

    public void sendAttachmentMail(String to, String subject, String content, String filePath);
}
