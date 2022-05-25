package com.wcd.assignment.controller.category;

import com.wcd.assignment.entity.Category;
import com.wcd.assignment.model.CategoryModel;
import com.wcd.assignment.model.MySqlCategoryModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListCategoryUserServlet extends HttpServlet {
    private CategoryModel categoryModel;

    public ListCategoryUserServlet() {
        this.categoryModel = new MySqlCategoryModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> products = categoryModel.findAll();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/user/page/categories/products.jsp").forward(req, resp);
    }
}
