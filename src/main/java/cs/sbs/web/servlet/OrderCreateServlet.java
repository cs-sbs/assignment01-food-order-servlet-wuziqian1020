package cs.sbs.web.servlet;

import cs.sbs.web.model.Order;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderCreateServlet extends HttpServlet {

    // 改为 static，让所有实例共享同一个订单列表
    private static List<Order> orders = new ArrayList<>();

    // 提供一个公共方法供 OrderDetailServlet 访问
    public static List<Order> getOrders() {
        return orders;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain; charset=UTF-8");

        String customer = req.getParameter("customer");
        String food = req.getParameter("food");
        String quantityStr = req.getParameter("quantity");

        if (customer == null || customer.isEmpty() ||
                food == null || food.isEmpty() ||
                quantityStr == null || quantityStr.isEmpty()) {
            resp.getWriter().println("Error: missing parameters");
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            resp.getWriter().println("Error: quantity must be a valid number");
            return;
        }

        Order order = new Order(customer, food, quantity);
        orders.add(order);
        resp.getWriter().println("Order Created: " + order.getId());
    }
}