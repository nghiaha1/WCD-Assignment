<%@ page import="com.wcd.assignment.entity.Account" %><%--
  Created by IntelliJ IDEA.
  User: Nghĩa Hà
  Date: 5/25/2022
  Time: 3:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Account account = (Account) request.getAttribute("account");
    if (account == null) {
        account = new Account();
    }
%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="/user/includes/head.jsp"></jsp:include>

<body>

    <jsp:include page="/user/includes/sidenav.jsp"></jsp:include>

    <div id="wrapper">

        <jsp:include page="/user/includes/header.jsp"></jsp:include>

        <jsp:include page="/user/includes/discount.jsp"></jsp:include>

        <div class="checkout_area">
            <div class="container">
                <div class="row">
                    <div class="col-12 col-md-12">
                        <div class="checkout_details_area mt-50 clearfix">
                            <div class="cart-page-heading">
                                <h5>Login</h5>
                            </div>
                            <form action="/login" method="post">
                                <div class="row">
                                    <%
                                        if(account.getListErrors().size() > 0) {
                                    %>
                                    <h6 class="error_code">
                                        <ul>
                                            <% for (int i = 0; i < account.getListErrors().size(); i++) { %>
                                            <li><%=account.getListErrors().get(i)%></li>
                                            <% } %>
                                        </ul>
                                    </h6>
                                    <% } %>
                                </div>
                                <div class="row">
                                    <div class="col-12 mb-3">
                                        <label>Username<span>*</span></label>
                                        <input type="text" class="form-control" name="username" value="<%= account.getUsername() %>" required>
                                    </div>
                                    <div class="col-12 mb-3">
                                        <label>Password <span>*</span></label>
                                        <input type="password" class="form-control" name="password">
                                    </div>
                                </div>
                                <div class="text-center">
                                    <button type="submit" class="col-2 btn karl-checkout-btn">Login</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="/user/includes/footer.jsp"></jsp:include>

    </div>

<jsp:include page="/user/includes/script.jsp"></jsp:include>

</body>

</html>
