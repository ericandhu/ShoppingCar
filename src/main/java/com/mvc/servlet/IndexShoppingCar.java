package com.mvc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexShoppingCar extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		request.getRequestDispatcher("web/hello.jsp").forward(request, response); // 請求轉發到指定頁面url
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>Hello!Servlet!</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1><b>Hello!Servlet!</b></h1>");
		out.println("</body>");
		out.println("</html>");
	}

}