<%@ page import="com.wcdexercise.wcdexercise4.entity.Product" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wcdexercise.wcdexercise4.entity.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.wcdexercise.wcdexercise4.entity.myenum.ProductStatus" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Product obj = (Product) request.getAttribute("obj");
    List<Category> categories = (List<Category>) request.getAttribute("categories");
    int action = (int) request.getAttribute("action");
    HashMap<String, String> errors = (HashMap<String, String>) request.getAttribute("errors");
    String url = "/admin/products/create";
    if (categories == null) {
        categories = new ArrayList<>();
    }
    if (obj == null) {
        obj = new Product();
    }
    if (errors == null) {
        errors = new HashMap<>();
    }
    if (action == 2) {
        url = "/admin/products/edit";
    }
%>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="../includes/head.jsp"></jsp:include>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <!-- Navbar -->
    <jsp:include page="../includes/navbar.jsp"></jsp:include>

    <!-- Main Sidebar Container -->
    <jsp:include page="../includes/sidebar.jsp"></jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1>Product management</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="/admin/products/list">Product Management</a></li>
                            <li class="breadcrumb-item active">Create new</li>
                        </ol>
                    </div>
                </div>
            </div><!-- /.container-fluid -->
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="container-fluid">
                <%
                    if (errors != null && errors.size() > 0) {
                %>
                <div class="row">
                    <div class="col-12">
                        <div class="callout callout-danger">
                            <h5>Please fix error below</h5>
                            <ul>
                            <%
                                for (String msg: errors.values()){
                            %>
                                <li class="text-danger"><%= msg %></li>
                            <%
                                }
                            %>
                            </ul>
                        </div>
                    </div>
                </div>
                <% } %>
                <div class="row">
                    <div class="col-12">
                        <div class="card card-warning">
                            <div class="card-header">
                                <h3 class="card-title">Please enter information below</h3>
                            </div>
                            <!-- /.card-header -->
                            <div class="card-body">
                                <form action="<%= url %>" method="post" name="product-form">
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <!-- select -->
                                            <div class="form-group">
                                                <label>Status</label>
                                                <select class="custom-select" name="status">
                                                    <% for (int i = 0; i< ProductStatus.values().length; i++){ %>
                                                    <option <%= obj.getStatus() == ProductStatus.values()[i] ? "selected" : "" %> value="<%= ProductStatus.values()[i].getValue() %>"><%= ProductStatus.values()[i].name() %></option>
                                                    <% } %>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <!-- select -->
                                            <div class="form-group">
                                                <label>Select product type</label>
                                                <select class="custom-select" name="categoryID">
                                                    <option value="0">All</option>
                                                    <% for (int i=0; i<categories.size(); i++) { %>
                                                        <option value="<%= categories.get(i).getId() %>"><%= categories.get(i).getName() %></option>
                                                    <% } %>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <!-- text input -->
                                            <div class="form-group">
                                                <label>Product name</label>
                                                <input type="text" name="name" value="<%= obj.getName() %>" class="form-control" placeholder="Please enter product's name">
                                                <% if (errors.containsKey("name")) { %>
                                                    <span class="text-danger">* <%= errors.get("name") %></span>
                                                <% } %>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <div class="form-group">
                                                <label>Description</label>
                                                <input type="text" name="description" value="<%= obj.getDescription() %>" class="form-control" placeholder="Please enter product's description">
                                                <% if (errors.containsKey("description")) { %>
                                                    <span class="text-danger">* <%= errors.get("description") %></span>
                                                <% } %>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-10">
                                            <div class="form-group">
                                                <label>Detail</label>
                                                <textarea id="summernote" name="detail"><%= obj.getDetail() %></textarea>
                                                <% if (errors.containsKey("detail")) { %>
                                                <span class="text-danger">* <%= errors.get("detail") %></span>
                                                <% } %>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <div class="form-group">
                                                <label>Thumbnail</label>
                                                <div class="input-group">
                                                    <div class="custom-file">
                                                        <input type="text" name="thumbnail" value="<%= obj.getThumbnail() %>" class="form-control" placeholder="Please enter product's thumbnail">
                                                    </div>
                                                    <div class="input-group-append" id="upload_widget">
                                                        <span class="input-group-text">Upload</span>
                                                    </div>
                                                </div>
                                                <img src="" alt="." style="display: none" sizes="200px" id="preview-img" class="img-bordered mt-2">
                                                <% if (errors.containsKey("thumbnail")) { %>
                                                <span class="text-danger">* <%= errors.get("thumbnail") %></span>
                                                <% } %>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <div class="form-group">
                                                <label>Price</label>
                                                <input type="number" name="price" value="<%= obj.getPrice() %>" class="form-control" placeholder="Please enter product's price">
                                                <% if (errors.containsKey("price")) { %>
                                                <span class="text-danger">* <%= errors.get("price") %></span>
                                                <% } %>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-sm-4">
                                            <div class="form-group">
                                                <button class="btn btn-primary">Save</button>
                                                <input type="reset" class="btn btn-default" value="Reset">
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <!-- /.card-body -->
                        </div>
                    </div>
                    <!-- /.col -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <jsp:include page="../includes/footer.jsp"></jsp:include>
    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <!-- Control sidebar content goes here -->
    </aside>
    <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

<jsp:include page="../includes/script.jsp"></jsp:include>
<script src="https://upload-widget.cloudinary.com/global/all.js" type="text/javascript"></script>
<script>
    $(function () {
        // Summernote
        $('#summernote').summernote()

        var myWidget = cloudinary.createUploadWidget({
                cloudName: 'dab9sgwgk',
                uploadPreset: 'hgbett7h'}, (error, result) => {
                if (!error && result && result.event === "success") {
                    console.log('Done! Here is the image info: ', result.info.secure_url);
                    document.forms['product-form']['thumbnail'].value = result.info.secure_url;
                    document.getElementById('preview-img').src = result.info.secure_url;
                    document.getElementById('preview-img').style.display = "block";
                }
            }
        )

        document.getElementById("upload_widget").addEventListener("click", function(){
            myWidget.open();
        }, false);
    })
</script>

<script type="text/javascript">

</script>
</body>
</html>