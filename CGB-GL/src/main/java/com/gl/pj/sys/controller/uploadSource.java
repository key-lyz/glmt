package com.gl.pj.sys.controller;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
/**
 * @上传文件类
 * 
 * @author Chenjinbo
 *
 */
@Controller
public class uploadSource {
    /**
     * 
     * @上传文件方法
     * @param file
     * @param request
     * @return
     */
	@RequestMapping(value="/upload/" , method = RequestMethod.POST)
	@ResponseBody
	public String uploadSource(@RequestParam("file") MultipartFile file , HttpServletRequest request) {
		System.out.println(file);
		String pathString = null;
		String pathResult = null;
		if(file!=null) {
			String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
  			System.out.println("cs:"+ System.getProperty("user.dir"));
  			
   			
  			//path=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\upload\\";
  			path=System.getProperty("user.dir")+"\\target\\classes\\static\\upload\\";

				System.out.println("静态资源地址"+path);
				//  /E:/javawork3sts/CGB-GL/target/classes/static/upload/20190716202344_01.png
		pathString = path + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" +file.getOriginalFilename();
		
		pathResult="/upload/"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" +file.getOriginalFilename();
		}
		try {
			File files=new File(pathString);
            //打印查看上传路径
            System.out.println(pathString);
            if(!files.getParentFile().exists()){
                files.getParentFile().mkdirs();
            }
			file.transferTo(files);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return "{\"code\":0,\"msg\":\""+pathResult+"\"}";
	}
 
	
}
