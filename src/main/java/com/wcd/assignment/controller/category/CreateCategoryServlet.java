package com.wcd.assignment.controller.category;

import com.wcd.assignment.entity.Category;
import com.wcd.assignment.entity.myenum.CategoryStatus;
import com.wcd.assignment.model.CategoryModel;
import com.wcd.assignment.model.MySqlCategoryModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateCategoryServlet extends HttpServlet {

    private CategoryModel categoryModel;

    public CreateCategoryServlet() {
        this.categoryModel = new MySqlCategoryModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // trả về form.
        req.setAttribute("obj", new Category());
        req.setAttribute("action", 1);
        req.getRequestDispatcher("/admin/categories/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // xử lý validate và save.
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String detail = req.getParameter("detail");
        int status = Integer.parseInt(req.getParameter("status"));
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        category.setDetail(detail);
        category.setStatus(CategoryStatus.of(status));
        if (categoryModel.findByName(name) != null) {
            category.addErrors("name", "Product name is already existed");
        }
        if (!category.isValid()) {
            req.setAttribute("obj", category);
            req.setAttribute("action", 1);
            req.setAttribute("errors", category.getErrors());
            req.getRequestDispatcher("/admin/categories/form.jsp").forward(req, resp);
            return;
        }
        if (categoryModel.save(category) != null) {
            resp.sendRedirect("/admin/categories/list");
        } else {
            req.setAttribute("action", 1);
            req.setAttribute("title", "Create Category");
            req.getRequestDispatcher("/admin/categories/form.jsp").forward(req, resp);
        }
    }
}
