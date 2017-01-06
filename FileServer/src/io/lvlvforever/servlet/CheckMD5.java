package io.lvlvforever.servlet;


import io.lvlvforever.model.DFileItem;
import io.lvlvforever.model.FileItem;

import java.awt.image.FilteredImageSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Servlet implementation class ChechMD5
 */
public class CheckMD5 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckMD5() {
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
		String id = request.getParameter("id");
		JSONObject object = new JSONObject();
		DFileItem dao = new DFileItem();
		FileItem item = dao.queryFileItem(id);
		if(id == null || item == null){
			object.put("success", "false");
		}else{
			object.put("success", "true");
			object.put("MD5",item.getMd5());
		}
		PrintWriter writer = response.getWriter();
		writer.write(object.toJSONString());
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
