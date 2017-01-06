package io.lvlvforever.servlet;

import io.lvlvforever.model.DFileItem;
import io.lvlvforever.model.FileItem;
import io.lvlvforever.util.Configuration;
import io.lvlvforever.util.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class DownloadFileServlet
 */
public class DownloadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		//app.properties 路径
		DFileItem dao = new DFileItem();
		String basePath = request.getSession().getServletContext().getRealPath("");
		String configPath = basePath+Constants.CONFIG_PATH;
		String uploadPath = Configuration.getInstance(configPath).getProperty(Constants.FILE_STORAGE_PATH);
		FileItem item = dao.queryFileItem(id);
		if(item == null){
			PrintWriter writer = response.getWriter();

			writer.write("文件已经被删除");
			writer.close();
			return;
		}
		
		String name = item.getFileName();
		response.setContentType("application/octet-stream");
		String downloadName = URLEncoder.encode(name,"UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-Disposition", "attachment;filename*=utf-8'zh_cn'"+downloadName);
        File file = new File(uploadPath+item.getUploadTime());
        response.setContentLength((int)file.length());
        FileInputStream in = new FileInputStream(file);
        OutputStream out = response.getOutputStream();
        byte[] buffer = new byte[8192];
        int len = 0;
        while((len = in.read(buffer)) != -1){
        	out.write(buffer, 0, len);
        }
        out.flush();
        out.close();
        in.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
