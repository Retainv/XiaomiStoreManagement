package top.retain.xmsc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import top.retain.xmsc.dto.Type;
import top.retain.xmsc.service.ITypeService;
import top.retain.xmsc.service.impl.TypeServiceImpl;
import top.retain.xmsc.util.ResultEnum;
import top.retain.xmsc.util.ResultUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

/**
 * @author Retain
 * @date 2021/1/14 11:06
 */
@WebServlet(name = "TypeServlet",urlPatterns = "/type")
public class TypeController extends HttpServlet {

    ObjectMapper om = new ObjectMapper();

    ITypeService typeService =new TypeServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String type=request.getParameter("methodType");

        if("addType".equals(type)) {
            addType(request, response);
        } else if("getAllTypes".equals(type)){
            getAllTypes(request, response);
        } else if ("delTypeById".equals(type)){
            delTypeById(request,response);
        } else if ("getTypeById".equals(type)){
            getTypeById(request, response);
        }else if ("updateTypeById".equals(type)){
            updateTypeById(request, response);
        }


    }

    private void updateTypeById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id= request.getParameter("typeId");
        String name = request.getParameter("name");
        String desc = request.getParameter("desc");
        Type type = Type.builder().id(Integer.valueOf(id)).name(name).description(desc).build();
        if (typeService.modifyType(type)>0){
            ResultUtil.toResult(ResultEnum.SUCCESS.getCode(),response);
        }else {
            ResultUtil.toResult(ResultEnum.ERROR.getCode(),response);
        }
    }

    private void getTypeById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        Type type = typeService.getTypeById(Integer.valueOf(id));
        ResultUtil.toResult(om.writeValueAsString(type),response);
    }

    private void delTypeById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id= request.getParameter("id");
        if (typeService.deleteType(Integer.valueOf(id))>0){
            ResultUtil.toResult(ResultEnum.SUCCESS.getCode(),response);
        }else {
            ResultUtil.toResult(ResultEnum.ERROR.getCode(),response);
        }
    }

    private void getAllTypes(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HashMap<String, Object> map = new HashMap<>();
        //获取页码、大小
        int currentPage = request.getParameter("page")==null ? 1 : Integer.parseInt(request.getParameter("page"));
        int size = request.getParameter("page")==null ? 10 : Integer.parseInt(request.getParameter("limit"));
        //分页查询
        List<Type> types = typeService.getAllTypes(currentPage, size);
        map.put("code",0);
        map.put("msg","");
        map.put("count",typeService.getCount());
        map.put("data",types);

        ResultUtil.toResult(om.writeValueAsString(map),response);
    }

    private void addType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String desc = request.getParameter("desc");
        Type type = Type.builder().name(name).description(desc).build();
        if (typeService.addType(type)>0){
            ResultUtil.toResult(ResultEnum.SUCCESS.getCode(),response);
        }else {
            ResultUtil.toResult(ResultEnum.ERROR.getCode(),response);
        }
    }
}
