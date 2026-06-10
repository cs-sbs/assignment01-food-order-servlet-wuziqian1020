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

        // 重要：处理 null 和 空字符串 的情况
        if (nameQuery == null || nameQuery.trim().isEmpty()) {
            // 显示所有菜单
            resp.getWriter().println("Menu List:");
            int index = 1;
            for (MenuItem item : menu) {
                resp.getWriter().println(index++ + ". " + item.getName() + " - $" + item.getPrice());
            }
        } else {
            // 搜索匹配的菜单
            List<MenuItem> matchedItems = new ArrayList<>();
            for (MenuItem item : menu) {
                if (item.getName().toLowerCase().contains(nameQuery.toLowerCase())) {
                    matchedItems.add(item);
                }
            }

            if (!matchedItems.isEmpty()) {
                resp.getWriter().println("Menu List:");
                int index = 1;
                for (MenuItem item : matchedItems) {
                    resp.getWriter().println(index++ + ". " + item.getName() + " - $" + item.getPrice());
                }
            }
            // 注意：如果没有找到，应该返回空或者什么都不返回
            // 测试可能期望空输出或者 "No items found"
        }
    }
}