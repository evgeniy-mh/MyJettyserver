import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by evgeniy on 22.01.17.
 */
public class staticPageTest extends HttpServlet{//сервлет

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{

        String page=new String(Files.readAllBytes(Paths.get("pages/pageTest.html")));

        response.getWriter().println(page);
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public  void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{


    }
}
