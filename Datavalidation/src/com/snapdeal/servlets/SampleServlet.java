package com.snapdeal.servlets;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.sanpdeal.backendsystems.BackendSystemCheck;
import com.snapdeal.datavalidator.config.ReadConfig;
import com.snapdeal.datavalidator.util.DBUtil;
import com.snapdeal.datavalidator.util.DataValidator;
import com.snapdeal.datavalidator.util.Message;
import com.snapdeal.datavalidator.util.ReadingExcelFile;
import com.snapdeal.datavalidator.util.SSHCinnectionUtil;


/**
 * Servlet implementation class SampleServlet
 */
public class SampleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean isMultipart;
	private int maxFileSize = 50 * 1024;
	private int maxMemSize = 4 * 1024;
	private File file ; 
	private String baseDir="";
	private String filePath="";
	private String privatekeyfile="";
	private ReadConfig readConfig;
	private String sship="54.169.156.29";
	private String user="as7892";
	private String dbip="20.0.0.65";
	private int forwardPort=3011;
	private String ForwardPort="3011";
	private int port=3307;
	private String DBUsername="ak4776IU";
	private String DBPassword="a@6774#k";
	static final Logger LOGGER = Logger.getLogger(SampleServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SampleServlet() {
        super();
    }
    
    public void init( ){
        // Get the file location where it would be stored.
    	LOGGER.info("Logging from init() method in sample Servlet.java");
    	ServletContext context = getServletContext();
    	System.out.println("Real Path using servlet context"+context.getRealPath("/"));
    	filePath=context.getRealPath("/WEB-INF/lib/uploadedFile/");
    	System.out.println("Uploaded File "+filePath);
    	privatekeyfile = context.getRealPath("/WEB-INF/lib/anjali.pem");
    	baseDir=  context.getRealPath("/WEB-INF/lib/");
    	System.out.println("Privatekey path "+privatekeyfile);
        readConfig=new ReadConfig(baseDir);
       String sship= readConfig.getPropValues("sship");
       String user= readConfig.getPropValues("user");
       String dbip=readConfig.getPropValues("dbip");
       int forwardPort=Integer.parseInt(readConfig.getPropValues("forwardPort"));
       String ForwardPort=readConfig.getPropValues("forwardPort");
       int port=Integer.parseInt(readConfig.getPropValues("port"));
       String DBUsername=readConfig.getPropValues("DBUsername");
       String DBPassword=readConfig.getPropValues("DBPassword");
		 try {
			SSHCinnectionUtil.myStart( sship, user, dbip, forwardPort,  port, privatekeyfile);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Excetion occured @@123@", e);
			
		}
		 try {
			 DBUtil.sqlConnection(ForwardPort,DBUsername,DBPassword); 
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Excetion occured here ###123####", e);
		}
     }
	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @throws IOException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
	     try{
	    	 mymethodForPost(request, response);
	     }catch(Exception e){
	    	 try {
	    		 SSHCinnectionUtil.connection=null;
				 DBUtil.connection=null;
	    	 LOGGER.error("Connection termination occured  so trying again here##$$");
 			ServletContext context=getServletContext();
 			String privateKeyFilePath = privatekeyfile = context.getRealPath("/WEB-INF/lib/anjali.pem");
 			LOGGER.error("values that are not null sship : "+sship+" user :"+user+" dbip :"+dbip+" forwardPort: "+forwardPort+" port : "+port+" privateKeyFilePath : "+privateKeyFilePath);
 			SSHCinnectionUtil.myStart(sship, user, dbip, forwardPort, port, privateKeyFilePath);
 			Thread.sleep(6000); 
 			DBUtil.sqlConnection(ForwardPort,DBUsername,DBPassword);
 			mymethodForPost(request, response);
	    	 }catch(Exception ee){
	    		 LOGGER.error("Connection termination occured  so trying again gives error again!!123!!");
	    		 LOGGER.error("Excetion", ee);
	  	       RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
	  	       request.setAttribute("error", ee.getMessage());
	  	       rd.forward(request, response);
	    	 } 
	     }
	}	
	
	public void mymethodForPost(HttpServletRequest request, HttpServletResponse response) throws Exception{
		 isMultipart = ServletFileUpload.isMultipartContent(request);
	      response.setContentType("text/html");
	      try{
	      java.io.PrintWriter out = response.getWriter( );
	      if( !isMultipart ){
	         out.println("<html>");
	         out.println("<head>");
	         out.println("<title>Servlet upload</title>");  
	         out.println("</head>");
	         out.println("<body>");
	         out.println("<p>No file uploaded</p>"); 
	         out.println("</body>");
	         out.println("</html>");
	         return;
	      }
	      }catch(Exception e){
	    	  
	    	  LOGGER.error("Exception occured here###1", e);
	    	  e.printStackTrace();
	    	  System.out.println("Exception e");
	    	  RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
	    	  request.setAttribute("error", e.getLocalizedMessage());
	    	  try {
				rd.forward(request, response);
			} catch (IOException e1) {
				LOGGER.error("Exception occured here###2", e);
				e1.printStackTrace();
			}
	      }
	      DiskFileItemFactory factory = new DiskFileItemFactory();
	      // maximum size that will be stored in memory
	      factory.setSizeThreshold(maxMemSize);
	      // Location to save data that is larger than maxMemSize.
	      factory.setRepository(new File(filePath));
	      // Create a new file upload handler
	      ServletFileUpload upload = new ServletFileUpload(factory);
	      // maximum file size to be uploaded.
	      upload.setSizeMax( maxFileSize );
	      List fileItems = upload.parseRequest(request);
	      // Process the uploaded file items
	      Iterator i = fileItems.iterator();
	      while ( i.hasNext () ) 
	      {
	         FileItem fi = (FileItem)i.next();
	         if ( !fi.isFormField () )	
	         {
	            // Get the uploaded file parameters
	            String fileName = fi.getName();
	            // Write the file
	            if( fileName.lastIndexOf("\\") >= 0 ){
	               file = new File( filePath + 
	               fileName.substring( fileName.lastIndexOf("\\"))) ;
	            }else{
	               file = new File( filePath + 
	               fileName.substring(fileName.lastIndexOf("\\")+1)) ;
	            }
	            fi.write( file ) ;
	    		Message message =new Message();
	    		ReadingExcelFile readingFile = new ReadingExcelFile();
	    		DataValidator dataValidator = new DataValidator();
	    		String path =filePath+"//"+fileName.substring(fileName.lastIndexOf("\\")+1);
	    		System.out.println("path  "+path);
	    		readingFile.readExcelFile(path,message);
	    		 //Starting data validation 
	    		 dataValidator.validateBrandId(readingFile.getBrandID(), message);
	    		 dataValidator.validateFreebieID(readingFile.getFreebieID(), message);
	    		 dataValidator.validateNavigationCategory(readingFile.getNavigationCategory(), message);
	    		 dataValidator.validatePricing(readingFile.getMrp(), readingFile.getSellingPrice(), readingFile.getMinimumSellingPrice(), message);
	    		 dataValidator.validateProductCategory(readingFile.getProductCategory(), message);
	    		 dataValidator.validateShippingGroup(readingFile.getShoppingGroup(), message);
	    		 dataValidator.validatevendorCode(readingFile.getVendorCode(), message);
	    		 //Validating backend system
	    		 BackendSystemCheck systemCheck=new BackendSystemCheck();
	    		 systemCheck.backendSystemCheck(message,readConfig);
	    		 System.out.println("Displaying The Error list");
	    		 List<String> errorList=message.getErrorMessageList();
	    		 //Passing error list to jsp
	    		 request.setAttribute("jspErrorList",errorList);
	   		     List<String> warningList=message.getWarningMessageList();
	    		//Passing warning list to jsp
	    		 request.setAttribute("jspWarningList",warningList);
	    		 System.out.println("Displaying The info list");
	    		 List<String> infoMessageList=message.getInfoMessageList();
	    		//Passing info list to jsp
	    		 request.setAttribute("jspinfoList",infoMessageList);
	    		 List<String> errorLists=message.getErrorMessageList();
	    		 boolean camdown=false;
	    		 boolean cocofsdown=false;
	    		 boolean ipmsdown=false;
	    		 boolean shippingdown=false;
	    		 boolean scoredown=false;
	    		 for(String s:errorLists){
	    			 if(s.contains("CAMS")){
	    				 camdown=true;
	    			 }
	    			 if(s.contains("COCOFS")){
	    				 cocofsdown=true;
	    			 }
	    			 if(s.contains("IPMS")){
	    				 ipmsdown=true;
	    			 }
	    				
	    			 if(s.contains("SHIPPING")){
	    				 shippingdown=true;
	    			 }
	    			 if(s.contains("SCORE")){
	    				 scoredown=true;
	    			 }
	    			 
	    		 }
	    		 //setting all the boolean values for the jsp
	    		 request.setAttribute("camdown", camdown);
	    		 request.setAttribute("cocofsdown", cocofsdown);
	    		 request.setAttribute("ipmsdown", ipmsdown);
	    		 request.setAttribute("shippingdown", shippingdown);
	    		 request.setAttribute("scoredown", scoredown);
	    		 boolean success = (new File
	    		         (path)).delete();
	    		         if (success) {
	    		            System.out.println("The file has been successfully deleted "+path); 
	    		         }
	    		         else
	    		        	 System.out.println("The file has not been successfully deleted "+path); 
	    		 RequestDispatcher requestDispatcher = request.getRequestDispatcher("datavalidator.jsp");
	    		 requestDispatcher.forward(request, response); 
	         }
	      }	
	}

}
