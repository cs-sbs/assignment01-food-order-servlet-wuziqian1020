package cs.sbs.web.servlet;

import cs.sbs.web.model.Order;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/order")
public class OrderCreateServlet extends HttpServlet {

    @Override
    public void init() {
        ServletContext ctx = getServletContext();
        if (ctx.getAttribute("orderDB") == null) {
            ctx.setAttribute("orderDB", new HashMap<Integer, Order>());
            ctx.setAttribute("autoId", 1001);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String customer = request.getParameter("customer");
        String food = request.getParameter("food");
        String qtyStr = request.getParameter("quantity");

        // 空参数校验
        if (customer == null || customer.isBlank()
                || food == null || food.isBlank()
                || qtyStr == null || qtyStr.isBlank()) {
            out.println("Error: Missing required parameters");
            return;
        }

        // 数量校验
        int quantity;
        try {
            quantity = Integer.parseInt(qtyStr.trim());
            if (quantity <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            out.println("Error: quantity must be a valid number");
            return;
        }

        // 存入全局上下文
        ServletContext ctx = getServletContext();
        @SuppressWarnings("unchecked")
        Map<Integer, Order> orderDB = (Map<Integer, Order>) ctx.getAttribute("orderDB");
        int autoId = (Integer) ctx.getAttribute("autoId");

        Order newOrder = new Order(autoId, customer, food, quantity);
        orderDB.put(autoId, newOrder);
        ctx.setAttribute("autoId", autoId + 1);

        out.println("Order Created: " + autoId);
    }
}