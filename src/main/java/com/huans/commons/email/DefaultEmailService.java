package com.huans.commons.email;

import java.util.List;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.huans.commons.email.entity.AttachmentFile;
import com.huans.commons.email.utils.CollectionUtils;
import com.huans.commons.email.utils.StringUtils;


/**
 * 
 * @author zhahuan
 *
 */
public class DefaultEmailService extends AbstractEmailService implements EmailService {
	
    public DefaultEmailService(JavaMailSender javaMailSender, SimpleMailMessage simpleMailMessage, VelocityEngine velocityEngine) {
    	super(javaMailSender, simpleMailMessage, velocityEngine);
    }
	
    @Override
	public void sendNormal(String receive, String subject, String content) {
		StringUtils.assertBlank(receive, "receive");
		
		send(new String[] { receive }, subject, content);
	}
	
	@Override
	public void sendNormal(List<String> receiveList, String subject, String content) {
		CollectionUtils.assertEmpty(receiveList, "receive");
		
		send(StringUtils.toArray(receiveList), subject, content);
	}
	
	@Override
	public void sendHtml(String receive, String subject, String tplPath, Map<String, Object> params) {
		StringUtils.assertBlank(receive, "receive");
		StringUtils.assertBlank(tplPath, "tplPath");
		
		send(new String[] { receive }, subject, tplPath, params);
	}

	@Override
	public void sendHtml(List<String> receiveList, String subject, String tplPath, Map<String, Object> params) {
		CollectionUtils.assertEmpty(receiveList, "receive");
		StringUtils.assertBlank(tplPath, "tplPath");
		
		send(StringUtils.toArray(receiveList), subject, tplPath, params);
	}

	@Override
	public void sendHtml(List<String> receiveList, String subject, String tplPath, Map<String, Object> params, List<AttachmentFile> files) {
		CollectionUtils.assertEmpty(receiveList, "receive");
		StringUtils.assertBlank(tplPath, "tplPath");
		
		send(StringUtils.toArray(receiveList), subject, tplPath, params, files);
	}

}
