<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="org.apache.log4j.Logger" %>
<%    
  ServletContext context=getServletContext();
  String filename = "SampleDataSheet.xlsx";   
  String filepath = context.getRealPath("/Download/");    //property.properties
  String fullpath=filepath+filename;
  File file=new File(filepath,filename);
  //response.setHeader("Content-Disposition", "inline;filename=\""+file.getName()+"\"");
  response.setHeader("Content-Type", getServletContext().getMimeType(file.getName()));
  response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
  //response.setHeader("Content-Length", file.length());
 
 
 
 System.out.println("File name "+(file.getName()));
 System.out.println("Content type "+context.getMimeType(file.getName()));
 try {
     FileInputStream fileInputStream = new FileInputStream(file);
     OutputStream responseOutputStream = response.getOutputStream();
     int bytes;
     while ((bytes = fileInputStream.read()) != -1) {
         responseOutputStream.write(bytes);
     }
     fileInputStream.close();
     responseOutputStream.close();

 } catch (FileNotFoundException e) {
     // TODO Auto-generated catch block
    // LOGGER.error("Excetion", e);
     e.printStackTrace();
 } catch (IOException e) {
     // TODO Auto-generated catch block
    // LOGGER.error("Excetion", e);
     e.printStackTrace();
 }
%>   