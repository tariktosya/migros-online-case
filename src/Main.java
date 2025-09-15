import com.sun.net.httpserver.HttpServer;
import controller.BaseController;

import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        BaseController.registerAll(server);

        server.start();
    }
}
