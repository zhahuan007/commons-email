package com.huans.commons.email;

import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.huans.commons.email.entity.AttachmentFile;
import com.huans.commons.email.utils.ImageUtils;

/**
 * 
 * @author zhahuan
 *
 */
public abstract class AbstractEmailService implements EmailService {
	
	private Log LOGGER = LogFactory.getLog(AbstractEmailService.class);
	
	/** java mail sender **/
	private JavaMailSender javaMailSender;
	
	/** simple mail message **/
    private SimpleMailMessage simpleMailMessage;
    
    /** velocity engine **/
    private VelocityEngine velocityEngine;
    
    private static final String DEFAULT_CHAR_SET = "UTF-8";
    
    public AbstractEmailService(JavaMailSender javaMailSender, SimpleMailMessage simpleMailMessage, VelocityEngine velocityEngine) {
    	this.javaMailSender = javaMailSender;
    	this.simpleMailMessage = simpleMailMessage;
    	this.velocityEngine = velocityEngine;
    }
    
    public AbstractEmailService(JavaMailSender javaMailSender, SimpleMailMessage simpleMailMessage) {
    	this.javaMailSender = javaMailSender;
    	this.simpleMailMessage = simpleMailMessage;
    }
    
    /**
     * 发送模板邮件
     * subject  		：主题
     * to				：收件人
     * templateName		：模板地址
     * params			：邮件变量替换内容
     * files			: 附件列表
     */
    public void send(String[] receive, String subject, String templateName, Map<String, Object> params, List<AttachmentFile> files) {
        
        simpleMailMessage.setSubject(subject); 						//设置邮件主题
        simpleMailMessage.setTo(receive);             					//设定收件人
        
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        
        String result = null;  
        try {  
        	MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, DEFAULT_CHAR_SET);
        	
        	//设置收件人，主题，内容  
        	helper.setSubject(subject);  
        	helper.setFrom(simpleMailMessage.getFrom());  
        	helper.setTo(receive);
            
        	result = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateName, DEFAULT_CHAR_SET, params);
            
            helper.setText(result, true);
            
            if(null != files && !files.isEmpty()) {
            	for(AttachmentFile f : files) {
            		helper.addAttachment(f.getFileName(), f.getFile());
            		
            		if (ImageUtils.isImage(f.getFile())) {
	            		FileSystemResource img = new FileSystemResource(f.getFile());
	            		helper.addInline(f.getFile().getName(), img);
            		}
            	}
            }
        } catch (Exception e) {
        	LOGGER.error("发送模板邮件,带附件异常", e);
        }  
        javaMailSender.send(mimeMessage);
    }
    
    
    /**
     * 发送模板邮件
     * subject  		：主题
     * to				：收件人
     * templateName		：模板地址
     * params			：邮件变量替换内容
     */
    public void send(String[] receive, String subject, String templateName, Map<String, Object> params) {
        simpleMailMessage.setSubject(subject); 						//设置邮件主题
        simpleMailMessage.setTo(receive);             					//设定收件人
        
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, DEFAULT_CHAR_SET);
        
        String result = null;  
        try {  
        	//设置收件人，主题，内容  
        	helper.setSubject(subject);  
        	helper.setFrom(simpleMailMessage.getFrom());  
        	helper.setTo(receive);
            
        	result = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateName, DEFAULT_CHAR_SET, params);
            
            helper.setText(result, true);
        } catch (Exception e) {
        	LOGGER.error("发送模板邮件异常", e);
        }  
        javaMailSender.send(mimeMessage);
    }
    
    /**
     * 发送普通邮件
     * subject  		：主题
     * to				：收件人
     * content			：邮件内容
     */
    public void send(String[] receive, String subject, String content) {
    	simpleMailMessage.setSubject(subject);
    	simpleMailMessage.setTo(receive);
    	simpleMailMessage.setText(content);
    	send(simpleMailMessage);
    }
    
    /**
     * 发送邮件
     * @param simpleMailMessage
     */
    public void send(SimpleMailMessage simpleMailMessage) {
    	javaMailSender.send(simpleMailMessage);
    }

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
}
