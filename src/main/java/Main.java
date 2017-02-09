import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


/**
 * Created by evgeniy on 22.01.17.
 */

public class Main {
    public static void main(String[] args) throws  Exception{

        /* 1 */ /*Server server=new Server(8080); //jetty сервер, его порт
        ServletContextHandler context=
                new ServletContextHandler(ServletContextHandler.SESSIONS);
        server.setHandler(context);

        staticPageTest staticPageTest = new staticPageTest(); //то что будет обрабатывать запросы(сервлет)
        context.addServlet(new ServletHolder(staticPageTest), "/pageTest");

        FreemarkerPageTest freemarkerPageTest=new FreemarkerPageTest();
        context.addServlet(new ServletHolder(freemarkerPageTest),"/freemarkerPageTest");

        MirrorServlet mirrorServlet=new MirrorServlet();
        context.addServlet(new ServletHolder(mirrorServlet),"/mirror");

        server.start();

        java.util.logging.Logger.getGlobal().info("Server started");

        server.join();*/

        /* 2 */

        AccountService accountService = new AccountService();
        accountService.addUser(new UserProfile("admin"));

        Server server=new Server(8080); //jetty сервер, его порт
        ServletContextHandler context=
                new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new SignInServlet(accountService)),"/signin");
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)),"/signup");


        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("pages");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context}); //сначала обраб. статическу стр, потом сервлеты

        server.setHandler(handlers);

        server.start();
        server.join();
    }
}
