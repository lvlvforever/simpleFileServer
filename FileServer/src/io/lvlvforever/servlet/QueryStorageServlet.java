package io.lvlvforever.servlet;

import io.lvlvforever.model.FileItem;
import io.lvlvforever.util.Configuration;
import io.lvlvforever.util.Constants;
import io.lvlvforever.util.FileList;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

/**
 * Servlet implementation class QueryStorageServlet
 */
public class QueryStorageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryStorageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
		response.setHeader("Pragrma", "no-cache");
		String basePath = request.getSession().getServletContext()
				.getRealPath("");

		String configPath = basePath + Constants.CONFIG_PATH;

		String uploadPath = Configuration.getInstance(configPath).getProperty(
				Constants.FILE_STORAGE_PATH);
		PrintWriter writer = response.getWriter();
		writer.write(uploadPath);
		writer.flush();
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
