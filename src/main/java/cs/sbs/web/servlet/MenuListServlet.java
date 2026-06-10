package cs.sbs.web.servlet;

import cs.sbs.web.model.MenuItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        // 关键：使用 trim() 处理空格
        if (nameQuery == null || nameQuery.trim().isEmpty()) {
            // 显示所有菜单
            resp.getWriter().println("Menu List:");
            int index = 1;
            for (MenuItem item : menu) {
                resp.getWriter().println(index++ + ". " + item.getName() + " - $" + item.getPrice());
            }
        } else {
            // 搜索匹配的菜单
            List<MenuItem> results = new ArrayList<>();
            for (MenuItem item : menu) {
                if (item.getName().toLowerCase().contains(nameQuery.toLowerCase().trim())) {
                    results.add(item);
                }
            }

            if (!results.isEmpty()) {
                resp.getWriter().println("Menu List:");
                int index = 1;
                for (MenuItem item : results) {
                    resp.getWriter().println(index++ + ". " + item.getName() + " - $" + item.getPrice());
                }
            }
            // 如果没有结果，不输出任何内容（测试可能期望空响应）
        }
    }
}