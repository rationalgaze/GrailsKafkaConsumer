<%--
  Created by IntelliJ IDEA.
  User: niko
  Date: 10/3/18
  Time: 11:26 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Simple Chat</title>
</head>
<body>
<g:form action="join">
    <label for="nickname">Please enter your name</label>
    <g:textField name="nickname"/>
    <g:submitButton  name="Join Chat" value="Join Chat"/>
</g:form>
</body>
</html>