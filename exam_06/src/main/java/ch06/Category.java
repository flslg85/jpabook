package ch06;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by we on 2015. 11. 18..
 */
@Entity
public class Category {
    @Id @GeneratedValue
    @Column(name = "CATEGORY_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<CategoryItem> categoryItems = new ArrayList<CategoryItem>();

    public void setName(String name) {
        this.name = name;
    }
}
