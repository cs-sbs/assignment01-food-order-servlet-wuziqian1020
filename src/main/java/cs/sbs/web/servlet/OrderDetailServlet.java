package cs.sbs.web.servlet;

import cs.sbs.web.model.Order;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class OrderDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain; charset=UTF-8");

        // 从 OrderCreateServlet 获取共享的订单列表
        List<Order> orders = OrderCreateServlet.getOrders();

        String path = req.getPathInfo();
        if (path == null || path.length() <= 1) {
            resp.getWriter().println("Error: missing order id");
            return;
        }

        try {
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

        } catch (NumberFormatException e) {
            resp.getWriter().println("Error: invalid order id");
        }
    }
}