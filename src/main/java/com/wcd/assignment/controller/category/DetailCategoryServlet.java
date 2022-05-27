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

public class DetailCategoryServlet extends HttpServlet {

    private CategoryModel categoryModel;

    public DetailCategoryServlet() {
        this.categoryModel = new MySqlCategoryModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getAttribute("message"));
        List<Category> list = categoryModel.findAll();
        req.setAttribute("title", "List Category");
        req.setAttribute("list", list);
        req.getRequestDispatcher("/admin/categories/list.jsp").forward(req, resp);
    }
}
