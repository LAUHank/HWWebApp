package cn.lhl.util;

import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

public class FtpUtil {
	
	private FtpUtil() {}
	
	private static final Logger LOGGER = Logger.getLogger(FtpUtil.class);
	
    //ftp服务器地址
    private static String hostname;
    //ftp服务器端口号
    private static Integer port;
    //ftp登录账号
    private static String username;
    //ftp登录密码
    private static String password;
    
    private static FTPClient ftpClient = null;
    
    /**
     * 连接ftp - 直接传参
     */
    public static void initFtpClient(String hostname, Integer port, String username, String password) {
        ftpClient = new FTPClient();
        ftpClient.setControlEncoding("utf-8");
        
        String statusText = "success";
        try {
            ftpClient.connect(hostname, port); //连接ftp服务器
            ftpClient.login(username, password); //登录ftp服务器
            int replyCode = ftpClient.getReplyCode(); //是否成功登录服务器
            if(!FTPReply.isPositiveCompletion(replyCode)){
            	statusText = "failed";
            }
        }catch (Exception e) { 
        	statusText = "failed";
           e.printStackTrace(); 
        } 
        LOGGER.info("user ["+username+"] connect "+statusText+" ftp server ["+hostname+"] on port ["+port+"]"); 
    }
    
    /**
     * 连接ftp - 使用配置文件
     */
    public static void initFtpClient() {
        ftpClient = new FTPClient();
        ftpClient.setControlEncoding("utf-8");
        
        String statusText = "success";
        try {
        	
        	Properties p = new Properties();
        	String src = "ftp.properties";
        	p.load(new InputStreamReader(FtpUtil.class.getClassLoader().getResourceAsStream(src), "utf-8"));
			hostname = p.getProperty("hostname", "192.168.56.1");
			port = Integer.valueOf(p.getProperty("port", "21"));
			username = p.getProperty("username", "lhl");
			password = p.getProperty("password", "123");
			LOGGER.info("加载ftp连接配置文件 ["+src+"] 成功"); 
			
            ftpClient.connect(hostname, port); //连接ftp服务器
            ftpClient.login(username, password); //登录ftp服务器
            int replyCode = ftpClient.getReplyCode(); //是否成功登录服务器
            
            if(!FTPReply.isPositiveCompletion(replyCode)){
            	statusText = "failed";
            }
            
        } catch (Exception e) { 
        	statusText = "failed";
           e.printStackTrace(); 
        } 
        LOGGER.info("user ["+username+"] connect "+statusText+" ftp server ["+hostname+"] on port ["+port+"]"); 
    }

    /**
     * 释放ftp连接
     */
    public static void releaseFtpClient() {
    	if (ftpClient == null) {
    		return;
    	}
    	try { 
            ftpClient.logout(); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } finally{ 
            if(ftpClient.isConnected()){ 
                try{
                    ftpClient.disconnect();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        } 
    }
    
    /**
     * 从ftp服务器上下载文件到指定输出流对象
     * @param pathname 下载文件的目录
     * @param filename 下载文件的文件名
     * @param os 指定输出流对象
     */
    public static void downloadFile(String pathname, String filename, OutputStream os){ 
        try { 
            //切换FTP目录 
            ftpClient.changeWorkingDirectory(pathname); 
            LOGGER.info("开始下载文件");
            FTPFile[] ftpFiles = ftpClient.listFiles(); 
            for(FTPFile file : ftpFiles){ 
            	String fn = file.getName();
            	LOGGER.info("ftp file name >>>>"+fn);
                if(filename.equalsIgnoreCase(fn)){ 
                    ftpClient.retrieveFile(fn, os); 
                    os.flush();
                    break;
                } 
            } 
            LOGGER.info("下载文件成功");
        } catch (Exception e) { 
        	LOGGER.error("下载文件失败");
            e.printStackTrace(); 
        } 
    }
    
    public static void main(String[] args) {
    	String ftpPath = "0A1B";
    	String ftpFileName = "2019.doc";
		String localpath = Paths.get("C:", "Users", "Administrator", "Desktop", "temp").toString();
    	String filename = "2019_"+Math.random()+".doc";
    	
    	// 连接ftp
    	FtpUtil.initFtpClient();
    	try (OutputStream out = new FileOutputStream(Paths.get(localpath, filename).toFile())) {
    		// 目录及文件名不能是中文
    		// FtpUtil.initFtpClient("192.168.56.1", 21, "lhl", "123");
			FtpUtil.downloadFile(ftpPath, ftpFileName, out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 下载完成要释放ftp连接
			FtpUtil.releaseFtpClient();
		}
    	
    	LOGGER.info("ok");
    }
}
