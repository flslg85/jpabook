package ch06;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by we on 2015. 11. 18..
 */
@Entity
public class CategoryItem {
    @Id @GeneratedValue
    @Column(name = "CATEGORY_ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public void setCategory(Category category) {
        this.category = category;
        category.getCategoryItems().add(this);
    }

    public void setItem(Item item) {
        this.item = item;
        item.getCategoryItems().add(this);
    }
}
