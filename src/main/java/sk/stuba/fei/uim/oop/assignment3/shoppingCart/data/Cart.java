package sk.stuba.fei.uim.oop.assignment3.shoppingCart.data;


import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean payed;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Product> shoppingList;

    public Cart(){
        this.shoppingList = new ArrayList<>();
        this.setPayed(false);
    }
}
