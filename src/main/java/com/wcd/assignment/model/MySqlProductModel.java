package com.wcd.assignment.model;

import com.wcd.assignment.entity.Product;
import com.wcd.assignment.entity.myenum.CategoryStatus;
import com.wcd.assignment.entity.myenum.ProductStatus;
import com.wcd.assignment.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class MySqlProductModel implements ProductModel {

    @Override
    public Product save(Product obj) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "insert into products " +
                    "(categoryId, name, description, detail, thumbnail, price, createdAt, updatedAt, status) " +
                    "values " +
                    "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, obj.getCategoryId());
            preparedStatement.setString(2, obj.getName());
            preparedStatement.setString(3, obj.getDescription());
            preparedStatement.setString(4, obj.getDetail());
            preparedStatement.setString(5, obj.getThumbnail());
            preparedStatement.setDouble(6, obj.getPrice());
            preparedStatement.setString(7, obj.getCreatedAt().toString());
            preparedStatement.setString(8, obj.getUpdatedAt().toString());
            preparedStatement.setInt(9, obj.getStatus().getValue());
            preparedStatement.execute();
            System.out.println("Action success!");
            return obj;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "select * from products where status = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, CategoryStatus.ACTIVE.getValue());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int categoryId = resultSet.getInt("categoryId");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String detail = resultSet.getString("detail");
                String thumbnail = resultSet.getString("thumbnail");
                double price = resultSet.getDouble("price");
                LocalDateTime createdAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("createdAt").toInstant(), ZoneId.systemDefault());
                LocalDateTime updatedAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("updatedAt").toInstant(), ZoneId.systemDefault());
                int intStatus = resultSet.getInt("status");
                Product obj = new Product();
                obj.setId(id);
                obj.setCategoryId(categoryId);
                obj.setName(name);
                obj.setDescription(description);
                obj.setThumbnail(thumbnail);
                obj.setDetail(detail);
                obj.setPrice(price);
                obj.setCreatedAt(createdAt);
                obj.setUpdatedAt(updatedAt);
                obj.setStatus(ProductStatus.of(intStatus));
                list.add(obj);
            }
            System.out.println("Action success!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Product findById(int id) {
        Product obj = null;
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "select * from products where status = ? and id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, CategoryStatus.ACTIVE.getValue());
            preparedStatement.setInt(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int categoryId = resultSet.getInt("categoryId");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String detail = resultSet.getString("detail");
                String thumbnail = resultSet.getString("thumbnail");
                double price = resultSet.getDouble("price");
                LocalDateTime createdAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("createdAt").toInstant(), ZoneId.systemDefault());
                LocalDateTime updatedAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("updatedAt").toInstant(), ZoneId.systemDefault());
                int intStatus = resultSet.getInt("status");
                obj = new Product();
                obj.setId(id);
                obj.setCategoryId(categoryId);
                obj.setName(name);
                obj.setDescription(description);
                obj.setThumbnail(thumbnail);
                obj.setDetail(detail);
                obj.setPrice(price);
                obj.setCreatedAt(createdAt);
                obj.setUpdatedAt(updatedAt);
                obj.setStatus(ProductStatus.of(intStatus));
            }
            System.out.println("Action success!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Product update(int id, Product updateObj) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "update products " +
                    "set categoryId = ?, name = ?, description = ?, detail = ?, thumbnail = ?, price = ?, createdAt = ?, updatedAt = ?, status = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, updateObj.getCategoryId());
            preparedStatement.setString(2, updateObj.getName());
            preparedStatement.setString(3, updateObj.getDescription());
            preparedStatement.setString(4, updateObj.getDetail());
            preparedStatement.setString(5, updateObj.getThumbnail());
            preparedStatement.setDouble(6, updateObj.getPrice());
            preparedStatement.setString(7, updateObj.getCreatedAt().toString());
            preparedStatement.setString(8, updateObj.getUpdatedAt().toString());
            preparedStatement.setInt(9, updateObj.getStatus().getValue());
            preparedStatement.setInt(10, id);
            preparedStatement.execute();
            System.out.println("Action success!");
            return updateObj;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "update products " +
                    "set status = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, ProductStatus.DELETED.getValue());
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
            System.out.println("Action success!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
