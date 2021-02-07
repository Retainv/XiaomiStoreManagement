package top.retain.xmsc.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Retain
 * @date 2021/1/15 9:59
 */
public class ResultUtil {

    public static void toResult(Object o,HttpServletResponse response){
        try {
            PrintWriter out = response.getWriter();
            out.println(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
