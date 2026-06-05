package cs.sbs.web.servlet;

import cs.sbs.web.model.MenuItem;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain; charset=UTF-8");
        String nameQuery = req.getParameter("name");
        for (MenuItem item : menu) {
            if (nameQuery == null || item.getName().contains(nameQuery)) {
                resp.getWriter().println(item.getName() + " - $" + item.getPrice());
            }
        }
    }
}