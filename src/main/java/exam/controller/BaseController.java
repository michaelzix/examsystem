package exam.controller;

import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;

import exam.common.Constant;
import exam.model.SysUser;

import java.util.List;

public class BaseController extends Controller {

    public void list(){
    }


    
    public SysUser getUser(){
    	return getSessionAttr(Constant.CUR_USER);
    }
    

}
