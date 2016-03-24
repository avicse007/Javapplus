package com.snapdeal.ite4helthcheck.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author singh.avinash
 */
public class ReportGeneration {

	public static void generateReport(List<String> compnents, Map<String, List<String>> apis) throws IOException {
		ReadProperty propertyValues = null;
		propertyValues = new ReadProperty();
		FileWriter file = new FileWriter("SystemsHealthCheckReport.html", false);
		BufferedWriter bw = new BufferedWriter(file);
		if (compnents.size() > 0) {
			bw.write("<html>");
			bw.write("<head><h3 align='center'><i>Below Component(s) Failed to Respond</i></h3></head>");
			bw.write("<body>");
			bw.write("<div id=\"chart_div\" align=\"center\"></div>");
			bw.write("<TABLE BORDER='1' CELLPADDING=10 CELLSPACING=0 align=\"center\">");
			bw.write("<TR style=\"background-color:#E6E6FA\"><th align=\"center\">S.No.</th>");
			bw.write("<th align=\"center\">Component Name</th>");
			bw.write("<th align=\"center\">Health Check Url</th>");
			int serialNumber = 0;
			String color_grey = "#F5F5F5";
			String color_white = "#FFFFFF";
			String color = "";
			for (String component : compnents) {
				if (serialNumber % 2 == 0)
					color = color_white;
				else
					color = color_grey;
				bw.write("<tr>");
				bw.write("<td align=\"center\" style=\"background-color:" + color + "\">" + (++serialNumber));
				bw.write("</td>");
				bw.write("<td align=\"center\" style=\"background-color:" + color + "\">" + component.split(">>>")[0].split(">>")[1]
						+ "</td>");
				bw.write("<td align=\"center\" style=\"background-color:" + color + "\">" + component.split(">>>")[1].split(">>")[1]
						+ "</td>");
				bw.write("</tr>");

			}
			bw.write("</TABLE>");
		}

		if ((apis.size()) > 0) {
			bw.write("<h3 align=\"center\"><i>Below API(s) failed to Respond</i></h3>");
			bw.write("<TABLE BORDER='1' CELLPADDING=10 CELLSPACING=0 align=\"center\" style=\"width:700px;\">");
			bw.write("<TR style=\"background-color:#E6E6FA\"><th align=\"center\">S.No.</th>");
			bw.write("<th align=\"center\">Compnent</th>");
			bw.write("<th align=\"center\">API Name</th>");
			bw.write("<th align=\"center\">API URL</th>");
			bw.write("<th align=\"center\">API Request</th>");
			int serialNumber = 0;
			String color_grey = "#F5F5F5";
			String color_white = "#FFFFFF";
			String color = "";

			for (Entry<String, List<String>> entry : apis.entrySet()) {
				int rowspan=entry.getValue().size();
				if (serialNumber % 2 == 0)
					color = color_white;
				else
					color = color_grey;
				String data="";
			    System.out.println("Key = " + entry.getKey() + ", ListSize = " + entry.getValue().size());
			    bw.write("<tr>");
				bw.write("<td align=\"center\" style=\"background-color:" + color + "\" "  +" rowspan=\""+rowspan+"\""+ ">" + (++serialNumber));
				bw.write("</td>");
				bw.write("<td align=\"center\" style=\"background-color:" + color + "\" "  +" rowspan=\""+rowspan+"\""+ ">" + entry.getKey() + "</td>");
			    for(int i=0;i<entry.getValue().size();i++){
			    	System.out.println("Values :"+entry.getValue().get(i));
			    	data=entry.getValue().get(i);
				//String tmp[] = entry.getValue().split(",");
				
				bw.write("<td align=\"center\" style=\"background-color:" + color + "\">" + data.split(">>>")[1].split("_")[1] + "</td>");
				bw.write("<td align=\"center\" style=\"background-color:" + color + "\">" + data.split(">>>")[3] + "</td>");
				bw.write("<td align=\"center\" style=\"background-color:" + color + "\">"+ data.split(">>>")[5] + "</td>");
				bw.write("</td>");
				bw.write("</TR>");
			}
			}
			bw.write("</TABLE>");
		}
		bw.write("</body>");
		bw.write("</html>");
		bw.close();
		file.close();
	}
}

