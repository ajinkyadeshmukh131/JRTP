package com.ad.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/wish")
public class WishMessageServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	//set response content type
		res.setContentType("text/html");
		//get print writer
		PrintWriter pw=res.getWriter();
		//write the messages to response obj
		pw.println("<h1 style='color:red;text-align:center'>Good Morning</h1>");
		//home hyperlink
		pw.println("<br><br><br><a href='index.jsp'>home</a>");
		//close stream
		pw.close();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
