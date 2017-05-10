发送邮件组件  
=====================  

----------  
  
  
 你好这是一个发送邮件的组件，只需要依赖响应jar包，做一些简单配置就可以发送邮件，支持普通邮件和html邮件。  
  
  
使用说明  
---------------  
  


1. maven依赖  

		<dependency>
		    <groupId>com.huans</groupId>
			<artifactId>commons-email</artifactId>
			<version>0.0.1-SNAPSHOT</version>
	    </dependency>


2. spring 配置邮件服务。
		
		记得修改 host、username和password，测试demo里面用的163的邮箱。
		
		<bean id="javaMailSender" class="org.springframework.mail.javamail.JavaMailSenderImpl">
	        <property name="host" value="smtp.163.com" /> <!-- 服务器 --> 
	        <property name="port" value="25" /> <!-- 端口号 -->
	        <property name="username" value="xxxxx@xxxx.com" /> <!-- 用户名 -->
	        <property name="password" value="xxxx" /> <!--  密码   -->
	        <property name="javaMailProperties"> <!-- SMTP服务器验证 -->
	            <props>
	               <prop key="mail.transport.protocol">smtp</prop>
	               <prop key="mail.smtp.ssl.trust">smtp.163.com</prop>
	               <!-- 验证身份 -->
	               <prop key="mail.smtp.auth">true</prop>
	               <prop key="mail.smtp.starttls.enable">true</prop>
	            </props>
	        </property>
	    </bean>

		<!-- 邮件service -->
		<bean id="emailService" class="com.huans.commons.email.DefaultEmailService">
			<constructor-arg index="0" ref="javaMailSender"/>
			<constructor-arg index="1" ref="simpleMailMessage"/>
			<constructor-arg index="2" ref="velocityEngine"/>
		</bean>

	具体配置见  src/main/resources   example-srping-email.xml
	
3. 邮件模板(html邮件)

	velocity模板见		src/main/resources   test-cs.vm

4. 测试代码
	
	配置都OK了，直接运行 EmailServiceTest 类test方法就可以了。
	
	
  
----------  
  [依赖服务]:
  
  - spring	    : https://spring.io/
  - velocity	: http://velocity.apache.org/

