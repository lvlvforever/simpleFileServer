package io.lvlvforever.servlet;

import io.lvlvforever.model.DFileItem;
import io.lvlvforever.util.Configuration;
import io.lvlvforever.util.Constants;
import io.lvlvforever.util.ResponseWrapper;
import io.lvlvforever.util.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSON;


/**
 * Servlet implementation class UploadFileServlet
 */
public class UploadFileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// 配置参数
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 10; // 10MB
	private static final long MAX_FILE_SIZE = 1024 * 1024 * 1024 * 5l; // 5GB
	private static final long MAX_REQUEST_SIZE = 1024 * 1024 * 1024 * 10l; // 10GB
	private double uploadPercent = 0.0;
	private static int count = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadFileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
		response.setHeader("Pragrma", "no-cache");
		PrintWriter writer = response.getWriter();
		UploadStatus status = (UploadStatus)(request.getSession().getAttribute("uploadStatus"));
		if(status != null){
			ResponseWrapper wrapper = new ResponseWrapper(status, "success", "ok");
			String json =JSON.toJSONString(wrapper);
			writer.write(json);
			if(status.getPercent() == 1.0 && status.isFinished()){
				request.getSession(true).setAttribute("uploadStatus", null);
			}
			
		}else{
			ResponseWrapper wrapper = new ResponseWrapper(status, "failure", "error");
			String json = JSON.toJSONString(wrapper);
			writer.write(json);

		}
		writer.flush();
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 首先检测是否是文件上传，主要依据enctype的值来判定
		if (!ServletFileUpload.isMultipartContent(request)) {
			PrintWriter writer = response.getWriter();
			writer.write("Error 不是文件上传,表单必须包含 enctype='multipart/form-data'");
			writer.flush();
			writer.close();
			return;
		}
		//app.properties 路径
		String basePath = request.getSession().getServletContext().getRealPath("");
		
		String configPath = basePath+Constants.CONFIG_PATH;

		

		String uploadPath = Configuration.getInstance(configPath).getProperty(Constants.FILE_STORAGE_PATH);
		
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置在内存中的缓存大小，如果超过了则保存到临时文件。
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		System.err.println(System.getProperty("java.io.tmpdir"));
		// 设置临时文件夹的目录
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置单个文件大小的最大值
		upload.setFileSizeMax(MAX_FILE_SIZE);
		// 设置上传文件总量的最大值，包括所有文件和表单的总和
		upload.setSizeMax(MAX_REQUEST_SIZE);
		File uploadDir = new File(uploadPath);
		HttpSession session = request.getSession();
		//设置上传进度的监听器
		UploadStatus status = new UploadStatus();
		status.setCount(count++);
		UploadListener listener = new UploadListener(status);
		upload.setProgressListener(listener);
		request.getSession(true).setAttribute("uploadStatus", status);

		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		try {
			// 解析request
			status.setStartTime(Utils.getTimeInSeconds());
			List<FileItem> formItems = upload.parseRequest(request);

			if (formItems != null && formItems.size() > 0) {
				String fileName = "";
				long uploadTime = 0;
				long fileSize = 0;
				String ip = null;
				String md5 = null;
				String abbr = null;
				
				
				for (FileItem item : formItems) {
					// 如果不是普通的formField则就是上传的文件
			if (!item.isFormField()) {
						 fileName = item.getName();
						 uploadTime = Utils.getTimeInSeconds();
						 fileSize = item.getSize() ;
						 ip = request.getRemoteHost();
						
					    MessageDigest md = MessageDigest.getInstance("MD5");
						
						File storeFile = new File(uploadPath + File.separator + uploadTime);
			
						InputStream in = item.getInputStream();
						if(!storeFile.exists()){
							storeFile.createNewFile();
						}
						OutputStream out = new FileOutputStream(storeFile);
						byte[] buffer = new byte[8192];
						int count = 0;
						while((count = in.read(buffer)) != -1){
							out.write(buffer, 0, count);
							md.update(buffer, 0, count);
						}
						byte[] md5Byte = md.digest();
						 md5 = Utils.byteMD5toString(md5Byte);
						out.close();
						in.close();
						item.delete();

					} else {
						abbr = item.getString();
					}
				}
				new DFileItem().addFileItem(uploadTime+"", fileName, fileSize+"", md5, ip, abbr);
				status.setFinished(true);
				System.err.println("store record to database");
			}
			request.setAttribute("message", "成功");
			request.getRequestDispatcher("result.jsp").forward(request,
					response);
		}catch(FileUploadBase.FileSizeLimitExceededException e){
			request.setAttribute("message", "单个文件大小超过限制");
			request.getRequestDispatcher("result.jsp").forward(request,
					response);
			e.printStackTrace();
			return;
		
		} catch(FileUploadBase.SizeLimitExceededException e){
			request.setAttribute("message", "上传文件总大小超过限制");
			request.getRequestDispatcher("result.jsp").forward(request,
					response);
			e.printStackTrace();
			return;
		
		}catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("result.jsp").forward(request,
					response);
			e.printStackTrace();
			return;
		}
	}

}
