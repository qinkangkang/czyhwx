package com.czyh.czyhwx.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageStreamUtil {

	public InputStream HttpToStream(String Wxurl) {
		InputStream inStream = null;
		try {
			URL url = new URL(Wxurl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			inStream = conn.getInputStream();//通过输入流获取图片数据   
		} catch (IOException e) {

			e.printStackTrace();
		}
		return inStream;

//		byte data[] = readInputStream(inStream);
//		inStream.read(data);  //读数据     
//		inStream.close();   
//	    inStream.read(data);  //读数据     
//      inStream.close();
//      response.setContentType("image/jpg"); //设置返回的文件类型     
//      OutputStream os = response.getOutputStream();    
//      os.write(data);    
//      os.flush();    
//      os.close();   
	}
	
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[2048];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}
	
}
