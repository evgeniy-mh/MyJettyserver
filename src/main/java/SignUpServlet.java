import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by evgeniy on 09.02.17.
 */
public class SignUpServlet extends HttpServlet{
    private final AccountService accountService;

    public SignUpServlet(AccountService accountService){
        this.accountService=accountService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String pass = request.getParameter("pass");

        if (login == null || pass == null) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println(":(((");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if(accountService.getUserByLogin(login)!=null){
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("user with same login exist :(");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile profile=new UserProfile(login,pass);
        accountService.addUser(profile);
        response.getWriter().println("you are registered :)\n now you can singin");
    }

}
