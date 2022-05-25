package com.wcd.assignment.controller.product;

import com.wcd.assignment.entity.Product;
import com.wcd.assignment.entity.shoppingcart.ShoppingCart;
import com.wcd.assignment.model.MySqlProductModel;
import com.wcd.assignment.model.ProductModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddCartServlet extends HttpServlet {

    private ProductModel productModel;

    public AddCartServlet() {
        this.productModel = new MySqlProductModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
        if(shoppingCart == null){
            shoppingCart = new ShoppingCart();
        }
        try {
            int productId = Integer.parseInt(req.getParameter("productId"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            Product product = productModel.findById(productId);
            if(product == null){
                req.getRequestDispatcher("/user/error/404.jsp").forward(req, resp);
                return;
            }
            if(quantity <= 0){
                req.getRequestDispatcher("/user/error/404.jsp").forward(req, resp);
                return;
            }
            shoppingCart.add(product, quantity);
            session.setAttribute("shoppingCart", shoppingCart);
            resp.sendRedirect("/cart/show");
        }catch (Exception ex){
            ex.printStackTrace();
            req.getRequestDispatcher("/user/error/500.jsp").forward(req, resp);
        }
    }
}
