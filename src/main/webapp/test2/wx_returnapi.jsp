<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%
String signature=request.getParameter("signature");
String timestamp=request.getParameter("timestamp");
String nonce=request.getParameter("nonce");
String echostr=request.getParameter("echostr");
out.print(echostr);
%>