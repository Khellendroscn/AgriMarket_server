package com.myeclipseide.ws;

import java.io.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.agri.SourcePath;
import com.agri.bean.impl.CustomerInfoImpl;
import com.agri.db.DBPoolManager;
import net.khe.db2.DBSession;
import net.khe.db2.DataBase;


//import org.springframework.stereotype.Component;

//import com.sun.jersey.spi.resource.Singleton;

//@Component
@Path("image")
//@Singleton
public class ImageResource  {
	private DBPoolManager manager = DBPoolManager.getInstance();
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public void upload(@FormParam("imageName") String imageName,
					   @FormParam("customerID") String customerID,
					   @FormParam("customerHeadImg") FileInputStream headImg) throws Exception {
		SourcePath sourcePath = SourcePath.getInstance();
		String filePath = sourcePath.getProperty("userHeadImg") + customerID+".jpg";
		// save the file to the server
		saveFile(headImg, filePath);
		DataBase<CustomerInfoImpl> dbInfo = manager.newDataBase(CustomerInfoImpl.class);
		CustomerInfoImpl info = dbInfo.getInstance(new Integer(customerID));
		info.setImgPath(filePath);
		DBSession<CustomerInfoImpl> session = dbInfo.createSession();
		session.put(info);
		session.execute();
		dbInfo.close();
	}
	
	private void saveFile(InputStream uploadedInputStream, String serverLocation) {
		try {
			OutputStream outpuStream = new FileOutputStream(new File(serverLocation));
			int read = 0;
			byte[] bytes = new byte[1024];
			outpuStream = new FileOutputStream(new File(serverLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}
			outpuStream.flush();
			outpuStream.close();
			uploadedInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
