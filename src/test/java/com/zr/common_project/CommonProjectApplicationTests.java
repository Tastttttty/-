package com.zr.common_project;

import com.zr.common_project.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;

@SpringBootTest
class CommonProjectApplicationTests {

	@Test
	void contextLoads() {
	}

	@Resource
	MailService mailService;

	@Test
	void testEmail(){
		//		mailService.sendSimpleEmail("1012453015@qq.com", "Test", "testtestetkljkljklf");
		mailService.sendAttachmentMail("1012453015@qq.com", "Test", "testtestetkljkljklf", "C:\\Users\\LovinEveryMinute\\Desktop\\java\\Spring Cloud Alibaba笔记.pdf");
		}

}
