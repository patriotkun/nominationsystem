<%@ page contentType="text/html; charset=Shift_JIS" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<HTML>
	<HEAD>
		<TITLE>トップ画面</TITLE>
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
					<bean:write name="loginUser" property="name" scope="session" ignore="true" />さん<br>
					チキチキご指名 第<bean:write name="loginUser" property="th" scope="session" ignore="true" />回目
				</FONT>
				<br>
				<html:link action="/Logout">Logout</html:link>&nbsp&nbsp&nbsp&nbsp&nbsp
				<html:link action="/Top">Reload</html:link>
			</P>
			<P>
				現在の研修生は下記の通りです。
				<TABLE BORDER="5">
				 <TR>
					<TH>No.</TH>
					<TH>氏名</TH>
					<TH>年齢</TH>
					<TH>社名</TH>
					<TH>ランク</TH>
					<TH>ぼたん</TH>
				 </TR>
				 <!-- 研修生の一覧を表示するエリア -->
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