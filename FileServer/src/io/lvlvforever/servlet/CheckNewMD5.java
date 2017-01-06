package io.lvlvforever.servlet;


import io.lvlvforever.model.DConfig;
import io.lvlvforever.model.DFileItem;
import io.lvlvforever.model.FileItem;
import io.lvlvforever.util.Constants;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

/**
 * Servlet implementation class CheckNewMD5
 */
public class CheckNewMD5 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckNewMD5() {
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
		DConfig config = new DConfig();
		FileItem item = dao.queryFileItem(id);
		if(id == null || item == null){
			object.put("success", "false");
		}else{
			String timestamp = item.getUploadTime();
			String storage = config.queryKey(Constants.FILE_STORAGE_PATH);
			File file = new File(storage+timestamp);
			String md5 = io.lvlvforever.util.Utils.calculateMD5(file);
			object.put("success", "true");
			object.put("MD5",md5);
			System.err.println(md5);
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
