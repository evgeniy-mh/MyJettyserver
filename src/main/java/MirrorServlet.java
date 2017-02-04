import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by evgeniy on 04.02.17.
 */
public class MirrorServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String , Object> pageVariables= new HashMap<>();
        pageVariables.put("key",request.getParameter("key"));


        response.getWriter().println(PageGenerator.instance().getPage("mirror.html",pageVariables) );
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public  void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{


    }

}
