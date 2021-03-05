package ht6;

import javax.persistence.*;

@Entity
@Table(name = "products_users")
public class UserProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column(name = "user_id")
    private int userId;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "current_cost")
    private double currentCost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getCurrentCost() {
        return currentCost;
    }

    public void setCurrentCost(double currentCost) {
        this.currentCost = currentCost;
    }

    @Override
    public String toString() {
        return "UserProducts{" +
                "product=" + product +
                ", currentCost=" + currentCost +
                '}';
    }
}
