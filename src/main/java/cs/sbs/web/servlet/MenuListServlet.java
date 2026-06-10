package cs.sbs.web.servlet;

import cs.sbs.web.model.MenuItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/menu")
public class MenuListServlet extends HttpServlet {

    private List<MenuItem> menuList;

    @Override
    public void init() {
        // 初始化菜单数据
        menuList = new ArrayList<>();
        menuList.add(new MenuItem(1, "Fried Rice", 8));
        menuList.add(new MenuItem(2, "Fried Noodles", 9));
        menuList.add(new MenuItem(3, "Burger", 10));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String searchName = request.getParameter("name");

        out.println("Menu List:");
        out.println();

        int index = 1;
        boolean found = false;
        for (MenuItem item : menuList) {
            if (searchName == null || searchName.isEmpty()
                    || item.getName().toLowerCase().contains(searchName.toLowerCase())) {
                out.println(index + ". " + item.getName() + " - $" + (int) item.getPrice());
                index++;
                found = true;
            }
        }

        // test3 要求：搜索不到时要输出含 "No" 或 "not" 的提示
        if (!found) {
            out.println("No items found matching: " + searchName);
        }
    }
}