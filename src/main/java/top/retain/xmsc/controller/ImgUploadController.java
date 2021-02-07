package top.retain.xmsc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import top.retain.xmsc.util.ResultUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Retain
 * @date 2021/1/15 17:02
 */
@WebServlet(name = "ImgUploadServlet",urlPatterns = "/upload")
@Slf4j
public class ImgUploadController extends HttpServlet {
    ObjectMapper om=new ObjectMapper();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String savePath = this.getServletContext().getRealPath("/files");
        File file = new File(savePath);
        if (!file.exists() && !file.isDirectory()) {

            log.info(savePath+"目录不存在，需要创建");
            //创建目录
            boolean mkdir = file.mkdir();
            log.info(String.valueOf(mkdir));
        }
        String filename="";
        try{
            //用ServletFileUpload获取ajax的formdata
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            if(!ServletFileUpload.isMultipartContent(request)){
                return;
            }
            //由于只上传一张图片，所以获取第一个即可
            List<FileItem> list = upload.parseRequest(request);
            String name = list.get(0).getName();
            log.info(name);
            if(name==null || name.trim().equals("")){
                return;
            }
            String ext = name.substring(name.lastIndexOf("."));
            filename = UUID.randomUUID().toString().substring(0,13)+ext;
            InputStream in = list.get(0).getInputStream();
            FileOutputStream out = new FileOutputStream(savePath + File.separator + filename);
            log.info("savePath:"+savePath + "\\" + filename);
            log.info("start upload");

            byte buffer[] = new byte[1024];
            //判断输入流中的数据是否已经读完
            int len = 0;
            while((len=in.read(buffer))>0){
                out.write(buffer, 0, len);
            }
            log.info("upload finished");
            in.close();
            out.close();
            list.get(0).delete();

        }catch (Exception e) {
            e.printStackTrace();
        }
        String visitPath="files/"+filename;

        HashMap<String, Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("src",visitPath);
        ResultUtil.toResult(om.writeValueAsString(map),response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
