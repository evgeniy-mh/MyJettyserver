import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by evgeniy on 09.02.17.
 */
public class SignInServlet extends HttpServlet {

    public final AccountService accountService;

    public SignInServlet(AccountService accountService){
        this.accountService=accountService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login=request.getParameter("login");
        String pass=request.getParameter("pass");

        if(login==null || pass==null){
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println(":(((");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile profile=accountService.getUserByLogin(login);
        if(profile==null || !profile.getPass().equals(pass)){
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println(":(((");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        accountService.addSession(request.getSession().getId(),profile); //если все ОК, добавляем в сессии

        //выводим инфу
        Gson gson= new Gson();
        String json=gson.toJson(profile);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(json+" ^_^");
        response.setStatus(HttpServletResponse.SC_OK);

    }

}
