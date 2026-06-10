package cs.sbs.web.servlet;

import cs.sbs.web.model.MenuItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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

        PrintWriter out = resp.getWriter();
        String nameQuery = req.getParameter("name");

        if (nameQuery == null || nameQuery.trim().isEmpty()) {
            // 显示所有菜单
            out.println("Menu List:");
            out.println("1. Fried Rice - $8");
            out.println("2. Fried Noodles - $9");
            out.println("3. Burger - $10");
        } else {
            // 搜索匹配的菜单
            int index = 1;
            boolean found = false;
            for (MenuItem item : menu) {
                // 修正：toLowerCase() 不是 toLowerCaseCase
                if (item.getName().toLowerCase().contains(nameQuery.toLowerCase().trim())) {
                    if (!found) {
                        out.println("Menu List:");
                        found = true;
                    }
                    out.println(index++ + ". " + item.getName() + " - $" + item.getPrice());
                }
            }
        }
    }
}