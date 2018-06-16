package exam.controller;


import exam.common.Constant;
import exam.common.ServerResponse;
import exam.model.SysUser;
import exam.service.IUserService;
import exam.service.impl.UserServiceImpl;
import exam.utils.EncryptUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jfinal.aop.Clear;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class HomeController extends BaseController {
	
	private Logger logger = LogManager.getLogger(this.getClass());
	
	private IUserService userService = new UserServiceImpl();

    public void index(){
    	setAttr("user", getSessionAttr(Constant.CUR_USER));
    }

    public void welcome(){
    }
    
    public void logout(){
    	removeSessionAttr(Constant.CUR_USER);
    	render("login.html");
    }
       
    @Clear
    public void login(){
      
    }
       
    @Clear
    public void loginJSON(){
    	String username = getPara("username");
    	String password = getPara("password");
    	String verifyCode = getPara("verifyCode");
    	if(StringUtils.equalsIgnoreCase(verifyCode, (String)getSessionAttr(Constant.VERIFYCode_KEY))){
    		ServerResponse loginResp = userService.login(username,EncryptUtils.MD5Encode(password));
    		if(loginResp.isSuccess()){
    			setSessionAttr(Constant.CUR_USER, loginResp.getData());
    		}
    		renderJson(loginResp);
    	}else{
    		renderJson(ServerResponse.createByErrorMessage("验证码不正确"));  
    	}    
    }
    
    @Clear
    public void register(){
    }
    
    @Clear
    public void registerJSON(){
    	SysUser user = getBean(SysUser.class, "user", true);
        ServerResponse register =	userService.register(user);
        if(register.isSuccess()){
			setSessionAttr(Constant.CUR_USER, register.getData());
		}
        renderJson(register);
    }

    Color getRandColor(int fc,int bc){//给定范围获得随机颜色
        Random random = new Random();
        if(fc>255) fc=255;
        if(bc>255) bc=255;
        int r=fc+random.nextInt(bc-fc);
        int g=fc+random.nextInt(bc-fc);
        int b=fc+random.nextInt(bc-fc);
        return new Color(r,g,b);
    }

    @Clear
    public void verifyCode() throws Exception{
       HttpServletResponse response = getResponse();
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        //在内存中创建图像
        int width = 60;
        int height = 20;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //获取图形上下文
        Graphics g = image.getGraphics();
        //随机类
        Random random = new Random();
        //设定背景
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        //设定字体
        g.setFont(new Font("Times New Roman",Font.PLAIN,18));
        //随机产生干扰线
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        //随机产生4位验证码
        String[] codes = {"2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","J","K","L","M","N","P","Q","R","S","T","U","V","W","X","Y","Z"};
        String code = "";
        for(int i=0;i<4;i++){
            String str = codes[random.nextInt(codes.length)];
            code += str;
            // 将认证码显示到图象中
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            //调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.drawString(str, 13 * i + 6, 16);
        }


// 将认证码存入SESSION
        setSessionAttr(Constant.VERIFYCode_KEY,code);
// 图象生效
        g.dispose();

// 输出图象到页面
        ImageIO.write(image, "JPEG",  response.getOutputStream());
        renderNull();
        return;
    }
}
