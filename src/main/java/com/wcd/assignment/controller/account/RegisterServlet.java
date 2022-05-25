package com.wcd.assignment.controller.account;

import com.wcd.assignment.entity.Account;
import com.wcd.assignment.model.AccountModel;
import com.wcd.assignment.model.MySqlAccountModel;
import com.wcd.assignment.util.SHA512Hasher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    private AccountModel accountModel;

    public RegisterServlet() {
        this.accountModel = new MySqlAccountModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("account", new Account());
        req.getRequestDispatcher("/user/pages/register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String name = firstName + " " + lastName;
        String username = req.getParameter("username");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String profileThumbnail = req.getParameter("profileThumbnail");
        String password = req.getParameter("password");
        String rePassword = req.getParameter("rePassword");
        Account account = new Account();
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setName(name);
        account.setUsername(username);
        account.setPhone(phone);
        account.setEmail(email);
        account.setProfileThumbnail(profileThumbnail);
        account.setPassword(password);
        account.setPasswordConfirm(rePassword);
        if (accountModel.findByEmail(email) != null) {
            account.addErrors("email", "Email is already existed");
        }
        if (accountModel.findByUsername(username) != null) {
            account.addErrors("username", "Username is already existed");
        }
        if (account.isValid()) {
            account.setSalt(SHA512Hasher.randomString(10));
            account.setPasswordHash(SHA512Hasher.encode(account.getPassword(), account.getSalt()));
            accountModel.save(account);
            resp.sendRedirect("/login");
        }else {
            req.setAttribute("account", account);
            req.getRequestDispatcher("/user/pages/register.jsp").forward(req, resp);
        }
    }
}
