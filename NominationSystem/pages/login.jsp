<%@ page contentType="text/html;charset=Shift_JIS" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<HTML>
	<HEAD>
		<TITLE>ÇÎÇÆÇ¢ÇÒ Ç™ÇﬂÇÒ</TITLE>
	</HEAD>
	<BODY>
	 <!-- ErrorMessageArea -->
	 <CENTER>
	  <FONT SIZE="20"><html:errors /></FONT>
	  </CENTER>
		<CENTER>
			<html:form action="/Login">
				<P>
					<FONT SIZE="5">ÇÎÇÆÇ¢ÇÒ</FONT>
				</P>
				<P>
					<TABLE>
						<TR>
							<TD>ÇÎÇ¨ÇÒ ID&nbsp;ÅF&nbsp;</TD>
							<TD><html:text property="id" size="20" /></TD>
						</TR>
						<TR>
							<TD>ÇœÇ∑ÇÌÅ[Ç«&nbsp;ÅF&nbsp;</TD>
							<TD><html:password property="password" size="20" redisplay="false" /></TD>
						</TR>
					</TABLE>
				</P>
				<P>
					<html:submit property="submit" value="Login" />
				</P>
			</html:form>
		</CENTER>
	</BODY>
</HTML>