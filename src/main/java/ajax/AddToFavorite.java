package ajax;

import bean.Account;
import dao.FavoriteProductDAO;
import service.FavoriteService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddToFavorite", value = "/add_favorite")
public class AddToFavorite extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("id");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account != null) {
            int userId = account.getId();
            if (!FavoriteProductDAO.getInstance().checkExistFavorite(userId, Integer.parseInt(productId))) {
                FavoriteProductDAO.getInstance().addFavorite(userId, Integer.parseInt(productId));
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
