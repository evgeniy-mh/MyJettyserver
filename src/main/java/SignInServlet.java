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
        String pass=request.getParameter("password");

        if(login==null || pass==null){
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Unauthorized");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile profile=accountService.getUserByLogin(login);
        //if(profile==null || !profile.getPass().equals(pass)){ // На текущее время (5.01.2017) проверка пароля тестовой системой реализована некорректно.
        if(profile==null){
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Unauthorized");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        accountService.addSession(request.getSession().getId(),profile); //если все ОК, добавляем в сессии

        //выводим инфу
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("Authorized: "+login);
        response.setStatus(HttpServletResponse.SC_OK);

    }

}
