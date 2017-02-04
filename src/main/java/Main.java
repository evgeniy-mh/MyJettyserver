import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Created by evgeniy on 22.01.17.
 */
public class Main {
    public static void main(String[] args) throws  Exception{

        Server server=new Server(8080); //jetty сервер, его порт
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
        server.join();



    }
}
