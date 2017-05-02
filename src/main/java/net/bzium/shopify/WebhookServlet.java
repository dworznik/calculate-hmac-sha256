package net.bzium.shopify;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Created by dwuziu on 02/05/17.
 */
public class WebhookServlet extends HttpServlet {

    public static final String HMAC_HEADER = "X-Shopify-Hmac-Sha256";

    private static String secret;

    @Override
    protected void doGet(HttpServletRequest reqest, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().println("Hello World!");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String webhookHmac = req.getHeader(HMAC_HEADER);
        String body = req.getReader().lines().collect(Collectors.joining());
        String hmac;
        try {
            hmac = Hmac.calculateHmac(body, secret);
        } catch (Exception e) {
            throw new ServletException(e);
        }
        System.out.println("Received a webhook request with hmac=" + webhookHmac);
        System.out.println("Calculated hmac=" + hmac);
    }

    @Override
    public void init() throws ServletException {
        System.out.println("Servlet " + this.getServletName() + " has started with secret=" + secret);
    }

    @Override
    public void destroy() {
        System.out.println("Servlet " + this.getServletName() + " has stopped");
    }


    public static void main(String[] args) throws Exception {
        if(args.length < 1) {
            throw new IllegalArgumentException("Missing secret");
        }

        secret = args[0];

        Server server = new Server(8080);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);

        handler.addServletWithMapping(WebhookServlet.class, "/*");
        ServletHolder servletHolder = new ServletHolder(new WebhookServlet());
        handler.addServlet(servletHolder);

        server.start();
        server.join();
    }

}
