package com.brotherlogic.shopping.web.servlets;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VersionServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Return the version string
		Properties props = new Properties();
		props.load(VersionServlet.class
				.getResourceAsStream("/application.properties"));
		PrintStream ps = new PrintStream(resp.getOutputStream());
		ps.print(props.get("application.version"));
		ps.close();
	}

}
