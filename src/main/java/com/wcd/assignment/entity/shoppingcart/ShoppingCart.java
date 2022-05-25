package com.wcd.assignment.entity.shoppingcart;

import com.wcd.assignment.entity.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class ShoppingCart implements ShoppingCartAction {

    private int userId; // ai đặt
    private String shipName;
    private String shipPhone;
    private String shipAddress;
    private String shipNote;
    private double totalPrice;
    private HashMap<Integer, CartItem> items;

    public ShoppingCart() {
        items = new HashMap<>();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getShipPhone() {
        return shipPhone;
    }

    public void setShipPhone(String shipPhone) {
        this.shipPhone = shipPhone;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getShipNote() {
        return shipNote;
    }

    public void setShipNote(String shipNote) {
        this.shipNote = shipNote;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public HashMap<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(HashMap<Integer, CartItem> items) {
        this.items = items;
    }

    // hàm này khác hàm update ở chỗ là + thêm số lượng
    @Override
    public void add(Product product, int quantity) {
        if (items.containsKey(product.getId())) {
            CartItem currentItem = items.get(product.getId());
            int updateQuantity = currentItem.getQuantity() + quantity;
            update(product, updateQuantity);
        } else {
            update(product, quantity);
        }
    }

    // thay đổi số lượng
    @Override
    public void update(Product product, int quantity) {
        if (items.containsKey(product.getId())) {
            CartItem currentItem = items.get(product.getId());
            currentItem.setQuantity(quantity);
            items.put(product.getId(), currentItem);
        } else {
            CartItem item = new CartItem();
            item.setProductId(product.getId());
            item.setProductName(product.getName());
            item.setProductThumbnail(product.getThumbnail());
            item.setUnitPrice(product.getPrice());
            item.setQuantity(quantity);
            items.put(product.getId(), item);
        }
    }

    @Override
    public void remove(Product product) {
        if(items.containsKey(product.getId())){
            items.remove(product.getId());
        }
    }

    @Override
    public ArrayList<CartItem> getListItems() {
        return new ArrayList<>(items.values());
    }
}
