<%@ page contentType="text/html; charset=Shift_JIS" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<HTML>
	<HEAD>
		<TITLE>�g�b�v���</TITLE>
<style type="text/css">
<!--
body {
	background-image: url(/images/41LK4m4fW7L.jpg);
	background-repeat: repeat;
}
-->
</style>
	</HEAD>
	<body>
		<CENTER>
			<P>
				<FONT SIZE="5">
					 Welcome To ITnabi!<br>
					<bean:write name="loginUser" property="name" scope="session" ignore="true" />����<br>
					�`�L�`�L���w�� ��<bean:write name="loginUser" property="th" scope="session" ignore="true" />���
				</FONT>
				<br>
				<html:link action="/Logout">Logout</html:link>&nbsp&nbsp&nbsp&nbsp&nbsp
				<html:link action="/Top">Reload</html:link>
			</P>
			<P>
				���݂̌��C���͉��L�̒ʂ�ł��B
				<TABLE BORDER="5">
				 <TR>
					<TH>No.</TH>
					<TH>����</TH>
					<TH>�N��</TH>
					<TH>�Ж�</TH>
					<TH>�����N</TH>
					<TH>�ڂ���</TH>
				 </TR>
				 <!-- ���C���̈ꗗ��\������G���A -->
				 <html:form action="/Top">
				 <logic:iterate id="trainee" name="userslist">
					<TR>
					   <TD><CENTER><bean:write name="trainee" property="id" scope="page" /></CENTER></TD>
					   <TD><CENTER><bean:write name="trainee" property="name" scope="page" /></CENTER></TD>
					   <TD><CENTER><bean:write name="trainee" property="age" scope="page" /></CENTER></TD>
					   <TD><CENTER><bean:write name="trainee" property="company" scope="page" /></CENTER></TD>
					   <TD><CENTER><bean:write name="trainee" property="rank" scope="page" /></CENTER></TD>
					   <TD><CENTER><html:multibox property="cbox" >
					   	<bean:write name="trainee" property="id" scope="page" />
					   </html:multibox></CENTER></TD>
					</TR>
				 </logic:iterate>
				 </html:form>
			  </TABLE>
			</P>
			<logic:iterate id="pic" name="picture">
				<img src="/NominationSystem<bean:write name="pic" scope="page" />" />
			</logic:iterate>
		</CENTER>
	</BODY>
</HTML>