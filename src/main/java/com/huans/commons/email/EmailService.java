package com.huans.commons.email;

import java.util.List;
import java.util.Map;

import com.huans.commons.email.entity.AttachmentFile;


/**
 * 发送邮件service类
 * @author zhahuan
 *
 */
public interface EmailService {
	
	/**
	 * 发送普通邮件
	 * @param receiveList   收件人列表
	 * @param subject		主题
	 * @param content		内容
	 */
	public void sendNormal(List<String> receiveList, String subject, String content) ;
	
	/**
	 * 发送普通邮件
	 * @param receive	收件人
	 * @param subject	主题
	 * @param content	内容
	 */
	public void sendNormal(String receive, String subject, String content) ;
	
	/**
	 * 发送模板邮件
	 * @param receive	收件人
	 * @param subject	主题
	 * @param tplPath	模板路径
	 * @param params	模板参数
	 */
	public void sendHtml(String receive, String subject, String tplPath, Map<String, Object> params) ;
	
	/**
	 * 发送模板邮件
	 * @param receiveList	收件人列表
	 * @param subject		主题
	 * @param tplPath		模板路径
	 * @param params		模板参数
	 */
	public void sendHtml(List<String> receiveList, String subject, String tplPath, Map<String, Object> params) ;
	
	/**
	 * 发送模板邮件
	 * @param receiveList	收件人列表
	 * @param subject		主题
	 * @param tplPath		模板路径
	 * @param params		模板参数
	 * @param files			附件
	 */
	public void sendHtml(List<String> receiveList, String subject, String tplPath, Map<String, Object> params, List<AttachmentFile> files) ;
	
}
