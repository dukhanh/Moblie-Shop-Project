package ajax;

import dao.CartDAO;
import model.Account;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddProductToCart", value = "/add_product_to_cart")
public class AddProductToCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Account account = (Account) session.getAttribute("account");

        if (account != null) {
            int userId = account.getId();
            if (quantity == 0) {
                CartDAO.getInstance().addProductToCart(userId, productId, 1);
            } else {
                CartDAO.getInstance().updateProductInCart(userId, productId, quantity + 1);
            }
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
