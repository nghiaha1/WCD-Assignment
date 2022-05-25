package com.wcd.assignment.controller.product;

import com.wcd.assignment.entity.Product;
import com.wcd.assignment.model.MySqlProductModel;
import com.wcd.assignment.model.ProductModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListProductUserServlet extends HttpServlet {
    private ProductModel productModel;

    public ListProductUserServlet() {
        this.productModel = new MySqlProductModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productModel.findAll();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/user/pages/products.jsp").forward(req, resp);
    }
}
