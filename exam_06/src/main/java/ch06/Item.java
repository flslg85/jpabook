package ch06;

import javax.naming.Reference;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by we on 2015. 11. 17..
 */
@Entity
public class Item {
    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<CategoryItem> categoryItems = new ArrayList<CategoryItem>();

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public List<CategoryItem> getCategoryItems() {
        return categoryItems;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
}
