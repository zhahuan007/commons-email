package com.huans.commons.email;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:example-srping-email.xml"})
public class EmailServiceTest {
	
	@Autowired
	private EmailService emailService;
	
	/**
	 * 普通邮件发送测试
	 */
	@Test
	public void sendMailSimpleTest() {
		emailService.sendNormal("xxxx@xxxx.com", "test email", "hello world！");
	}
	
	/**
	 * html邮件发送测试
	 */
	@Test
	public void sendMailHtmlTest() {
		String subject = MessageFormat.format("[风控]高危用户 {0} - 取消订单", 123);
		
		String cmTemplate = "/test-cs.vm";
		
		Map<String, Object> params = new HashMap<>();
		params.put("userId", 123);
		params.put("name", "测试123");

		List<Long> list = new ArrayList<>();
		list.add(2341234123L);
		list.add(234134341111L);
		
		params.put("orderList", list);
		
		List<String> receiveList = new ArrayList<>();
		receiveList.add("xxxx@xxxx.com");
		emailService.sendHtml(receiveList, subject, cmTemplate, params);
	}
	
}
