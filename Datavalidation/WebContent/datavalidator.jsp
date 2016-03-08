<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>File and Back end System Status</title>
<style>
table {
	border: 1px solid black;
}
</style>
</head>
<body>
	<table width='80%' align='center'>
		<tr>
			<td align='center' width='100%'>
				<center>
					<h1 align='center'>
						<u>COMS File and Backend System Status</u>
						</h3>
				</center>
			</td>
		</tr>
		<tr>
			<td></td>
		</tr>
		<tr>
			<td align='center'><h3>
					<u>File Status </u>
				</h3></td>
		</tr>
		<tr>
			<td></td>
		</tr>
	</table>
	<table width='80%' align='center'border="0px">
		<tr>
			<td><h3 align='top-center'>Following are the error in your
					Uploaded File</h3></td>
			<td><h3 align='top-center'>Following Info can be used to
					correct data</h3></td>
		</tr>
		<tr>
			<td
				style='width: 537px; word-break: break-all; word-wrap: break-word; align: top-center;'>
				<table width='100%' border="0px"style='height: 810px;'>
				
					<% List<String> errorList=(List<String>)request.getAttribute("jspErrorList"); 
					 int error =1;
					 for(String s:errorList){
						 
					%>
					<tr>
						<td><%= error%> <%=s%></td>
					</tr>
					<% error++; }
					if(errorList.size()<1){
						
					%>
					<tr>
						<td><h3>No Errors</h3></td>
					</tr>
					<tr>
						<td>You can upload this sheet on COMS Admin panel Using below link</td>
						
					</tr>
					<tr>
						<td><a href="http://52.74.25.212:10160/">Click here for COMS Admin panel</a> </td>
					</tr>
					<%} %>
					<tr>
						<td><h3>Warnings</h3></td>
					</tr>
					<% List<String> warningList=(List<String>)request.getAttribute("jspWarningList");  
					   int warningcount=1;
					   for(String s:warningList){
						   
					%>
					<tr>
						<td><%=warningcount %> .<%=s %></td>
					</tr>
					<% warningcount++; } %>
					<%if(warningList.size()<1){ %>
					<tr>
						<td><h4>No Warnings</h4></td>
					</tr>
					<%} %>
					
				</table>
			</td>
			<td
				style='width: 537px; word-break: break-all; word-wrap: break-word; align: top-center;'>
				<table width='100%' border="0px"style='height: 810px;'>
				<% 
				int info=1;
	    		int infocounter=1;
	    		List<String> infoMessageList=(List<String>)(request.getAttribute("jspinfoList"));
	    		for(String s : infoMessageList){
	    			if(s.startsWith("You")){
	    		
				%>
					<tr>
						<td><h3 style='color: green;'><%= info%>. <%= s %></h3></td>
					</tr>
				<% info++;} else {%>
					<tr>
						<td><%=s %></td>
					</tr>
				<%infocounter++;}} %>	
				</table>
			</td>
		</tr>
	</table>
	<table width='80%' border="0px" align='center'>
		<tr>
			<td align='center'>
				<h3>Current Status of Backend System</h3>
			</td>
		</tr>
	</table>
	<table width='80%' align='center' border=0px;>
		<tr>
			<td>CAMS STATUS</td>
			<td>COCOFS STATUS</td>
			<td>IPMS STATUS</td>
			<td>SHIPPING STATUS</td>
			<td>SCORE STATUS</td>
		</tr>
		<tr>
		<%
		boolean camsdown=true;
		boolean cocofsdown=true;
		boolean ipmsdown=true;
		boolean shippingdown=true;
		boolean scoredown=true;
		String cams="red";
		String cocofs="red";
		String ipms="red";
		String shipping="red";
		String score="red";
		camsdown=(boolean)request.getAttribute("camdown");
		cocofsdown=(boolean)request.getAttribute("cocofsdown");
		ipmsdown=(boolean)request.getAttribute("ipmsdown");
		shippingdown=(boolean)request.getAttribute("shippingdown");
		scoredown=(boolean)request.getAttribute("scoredown");
		if(!camsdown)
			cams="green";
		if(!cocofsdown)
			cocofs="green";
		if(!ipmsdown)
			ipms="green";
		if(!shippingdown)
			shipping="green";
		if(!scoredown)
			score="green";
		%>
			<td><div
					style='height: 25px; width: 25px; background-color:<%= cams %>; border: solid 1px silver;'></div></td>
			<td><div
					style='height: 25px; width: 25px; background-color:<%= cocofs%>; border: solid 1px silver;'></div></td>
			<td><div
					style='height: 25px; width: 25px; background-color:<%= ipms%>; border: solid 1px silver;'></div></td>
			<td><div
					style='height: 25px; width: 25px; background-color:<%= shipping%>; border: solid 1px silver;'></div></td>
			<td><div
					style='height: 25px; width: 25px; background-color:<%= score%>; border: solid 1px silver;'></div></td>
		</tr>
	</table>
</body>
</html>
