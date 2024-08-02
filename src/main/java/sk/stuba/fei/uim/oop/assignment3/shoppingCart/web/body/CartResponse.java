package sk.stuba.fei.uim.oop.assignment3.shoppingCart.web.body;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductResponse;
import sk.stuba.fei.uim.oop.assignment3.shoppingCart.data.Cart;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CartResponse {
    private long id;
    private List<ProductResponse> shoppingList;
    private Boolean payed;

    public CartResponse(Cart cart) {
        this.id = cart.getId();
        this.shoppingList = cart.getShoppingList().stream().map(ProductResponse::new).collect(Collectors.toList());
        this.payed = cart.getPayed();
    }
}
