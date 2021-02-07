package top.retain.xmsc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import top.retain.xmsc.dto.Goods;
import top.retain.xmsc.dto.Type;
import top.retain.xmsc.service.IGoodsService;
import top.retain.xmsc.service.impl.GoodsServiceImpl;
import top.retain.xmsc.util.ResultEnum;
import top.retain.xmsc.util.ResultUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author Retain
 * @date 2021/1/15 9:55
 */
@WebServlet(name = "GoodsServlet",urlPatterns = "/goods")
@MultipartConfig
public class GoodsController extends HttpServlet {

    public static final int NO_TYPE=-1;
    ObjectMapper om = new ObjectMapper();

    IGoodsService goodsService=new GoodsServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String type=request.getParameter("methodType");

        if("addGoods".equals(type)) {
            addGoods(request, response);
        } else if("getAllGoods".equals(type)){
            getAllGoods(request, response);
        } else if ("delGoodsById".equals(type)){
            delGoodsById(request,response);
        } else if ("getGoodsById".equals(type)){
            getGoodsById(request, response);
        }else if ("updateGoodsById".equals(type)){
            updateGoodsById(request, response);
        }else if ("getGoodsByType".equals(type)){
            getGoodsByType(request, response);
        }
    }

    private void getGoodsByType(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        HashMap<String, Object> map = new HashMap<>();
        //获取页码、大小
        int currentPage = request.getParameter("page")==null ? 1 : Integer.parseInt(request.getParameter("page"));
        int size = request.getParameter("limit")==null ? 10 : Integer.parseInt(request.getParameter("limit"));
        int typeId= Integer.parseInt(request.getParameter("typeId"));
        //分页查询
        List<Goods> goods = goodsService.getGoodsByType(currentPage, size,typeId);
        map.put("code",0);
        map.put("msg","");
        map.put("count",goodsService.getCount(typeId));
        map.put("data",goods);

        ResultUtil.toResult(om.writeValueAsString(map),response);
    }

    private void updateGoodsById(HttpServletRequest request, HttpServletResponse response) throws IOException {
            Goods goods = getFields(request);
        String imgUrl = request.getParameter("imgUrl");
        goods.setGoodsId(Integer.valueOf(request.getParameter("goodsId")));
        goods.setGoodsImg(imgUrl);
            if (goodsService.modifyGoods(goods)>0){
                ResultUtil.toResult(ResultEnum.SUCCESS.getCode(),response);
            }else {
                ResultUtil.toResult(ResultEnum.ERROR.getCode(),response);
            }
    }

    private void getGoodsById(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        int goodsId = Integer.parseInt(request.getParameter("goodsId"));
        Goods goods = goodsService.getGoodsById(goodsId);
        ResultUtil.toResult(om.writeValueAsString(goods),response);
    }

    private void delGoodsById(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        int goodsId = Integer.parseInt(request.getParameter("goodsId"));
        if (goodsService.deleteGoods(goodsId)>0){
            ResultUtil.toResult(ResultEnum.SUCCESS.getCode(),response);
        }else {
            ResultUtil.toResult(ResultEnum.ERROR.getCode(),response);
        }
    }

    private void getAllGoods(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        HashMap<String, Object> map = new HashMap<>();
        //获取页码、大小
        int currentPage = request.getParameter("page")==null ? 1 : Integer.parseInt(request.getParameter("page"));
        int size = request.getParameter("limit")==null ? 10 : Integer.parseInt(request.getParameter("limit"));
        //分页查询
        List<Goods> goods = goodsService.getAllGoods(currentPage, size);
        map.put("code",0);
        map.put("msg","");
        map.put("count",goodsService.getCount(NO_TYPE));
        map.put("data",goods);

        ResultUtil.toResult(om.writeValueAsString(map),response);
    }

    private void addGoods(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String imgUrl = request.getParameter("imgUrl");
        Goods goods = getFields(request);
        goods.setGoodsImg(imgUrl);
        if (goodsService.addGoods(goods)>0){
           ResultUtil.toResult(ResultEnum.SUCCESS.getCode(),response);
        }else {
            ResultUtil.toResult(ResultEnum.ERROR.getCode(),response);

        }
    }

    private Goods getFields(HttpServletRequest request) throws IOException {
        String name = request.getParameter("goodsName");
        double price = Float.parseFloat(request.getParameter("goodsPrice"));
        int goodsNum = Integer.parseInt(request.getParameter("goodsNum"));
        String desc = request.getParameter("goodsDesc");
        String detail = request.getParameter("goodsDetail");
        String goodsImg = request.getParameter("goodsImg")==null ?"":request.getParameter("goodsImg");
        int tid = request.getParameter("type")==null ?-1:Integer.parseInt(request.getParameter("type"));
        Goods good = Goods.builder().goodsName(name).goodsPrice(price).goodsNum(goodsNum)
                .goodsDesc(desc).goodsDetail(detail).typeId(tid).goodsImg(goodsImg).build();
        return good;
    }
}
