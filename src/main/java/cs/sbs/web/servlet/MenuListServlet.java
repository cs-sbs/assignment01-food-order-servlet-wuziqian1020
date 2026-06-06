package cs.sbs.web.servlet;

import cs.sbs.web.model.MenuItem;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;  // 添加这一行！

public class MenuListServlet extends HttpServlet {

    private List<MenuItem> menu = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        menu.add(new MenuItem("Fried Rice", 8));
        menu.add(new MenuItem("Fried Noodles", 9));
        menu.add(new MenuItem("Burger", 10));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain; charset=UTF-8");

        String nameQuery = req.getParameter("name");
        int index = 1;  // 添加序号

        for (MenuItem item : menu) {
            if (nameQuery == null || nameQuery.isEmpty()) {
                // 显示所有菜品，格式：1. Fried Rice - $8
                resp.getWriter().println(index++ + ". " + item.getName() + " - $" + item.getPrice());
            } else if (item.getName().toLowerCase().contains(nameQuery.toLowerCase())) {
                // 显示匹配的菜品
                resp.getWriter().println(index++ + ". " + item.getName() + " - $" + item.getPrice());
            }
        }
    }
}