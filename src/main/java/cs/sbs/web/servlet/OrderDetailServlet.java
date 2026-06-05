package cs.sbs.web.servlet;

import cs.sbs.web.model.Order;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class OrderDetailServlet extends HttpServlet {

    private List<Order> orders = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain; charset=UTF-8");

        String path = req.getPathInfo(); // /1001
        if (path == null || path.length() <= 1) {
            resp.getWriter().println("Error: missing order id");
            return;
        }

        int id = Integer.parseInt(path.substring(1));
        Order order = null;
        for (Order o : orders) {
            if (o.getId() == id) {
                order = o;
                break;
            }
        }

        if (order == null) {
            resp.getWriter().println("Error: order not found");
            return;
        }

        resp.getWriter().println("Order Detail");
        resp.getWriter().println("Order ID: " + order.getId());
        resp.getWriter().println("Customer: " + order.getCustomer());
        resp.getWriter().println("Food: " + order.getFood());
        resp.getWriter().println("Quantity: " + order.getQuantity());
    }
}