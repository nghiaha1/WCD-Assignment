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
import java.time.LocalDateTime;
import java.util.List;

public class EditCategoryServlet extends HttpServlet {

    private CategoryModel categoryModel;

    public EditCategoryServlet() {
        this.categoryModel = new MySqlCategoryModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Category category = categoryModel.findById(id);
        List<Category> list = categoryModel.findAll();
        if (category == null){
            req.setAttribute("message","Data not found!");
            req.getRequestDispatcher("/admin/errors/404.jsp").forward(req,resp);
        }else {
            req.setAttribute("title", "Edit Category");
            req.setAttribute("list", list);
            req.setAttribute("obj", category);
            req.setAttribute("action",2);
            req.getRequestDispatcher("/admin/categories/form.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String detail = req.getParameter("detail");
        int status = Integer.parseInt(req.getParameter("status"));
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        category.setDescription(description);
        category.setDetail(detail);
        category.setUpdatedAt(LocalDateTime.now());
        category.setStatus(CategoryStatus.of(status));

        if (!category.isValid()){
            req.setAttribute("obj", category);
            req.setAttribute("action",2);
            req.setAttribute("title","Edit Category");
            req.setAttribute("errors",category.getErrors());
            req.getRequestDispatcher("/admin/categories/form.jsp").forward(req,resp);
        }
        if (categoryModel.update(id, category) != null){
            resp.sendRedirect("/admin/categories/list");
        }else {
            req.setAttribute("obj", category);
            req.setAttribute("action",2);
            req.setAttribute("title","Edit Category");
            req.setAttribute("errors",category.getErrors());
            req.getRequestDispatcher("/admin/categories/form.jsp").forward(req,resp);
        }
    }
}

