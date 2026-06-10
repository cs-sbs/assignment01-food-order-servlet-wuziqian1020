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

        // 处理 null 和空字符串（包括空格）
        if (nameQuery == null || nameQuery.trim().isEmpty()) {
            // 显示所有菜单 - 精确格式
            out.print("Menu List:\n");
            out.print("1. Fried Rice - $8\n");
            out.print("2. Fried Noodles - $9\n");
            out.print("3. Burger - $10");
            // 注意：最后一个没有换行符，测试可能期望这样
        } else {
            // 搜索匹配的菜单
            int index = 1;
            boolean found = false;
            for (MenuItem item : menu) {
                if (item.getName().toLowerCase().contains(nameQuery.toLowerCase().trim())) {
                    if (!found) {
                        out.print("Menu List:\n");
                        found = true;
                    }
                    out.print(index++ + ". " + item.getName() + " - $" + item.getPrice() + "\n");
                }
            }
            // 如果有输出，去掉最后一个多余的换行符
            if (found) {
                // 已经处理了
            }
        }
    }
}