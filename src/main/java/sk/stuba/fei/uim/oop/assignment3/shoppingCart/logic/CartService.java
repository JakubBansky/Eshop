package sk.stuba.fei.uim.oop.assignment3.shoppingCart.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;
import sk.stuba.fei.uim.oop.assignment3.product.logic.IProductService;
import sk.stuba.fei.uim.oop.assignment3.shoppingCart.data.Cart;
import sk.stuba.fei.uim.oop.assignment3.shoppingCart.data.ICartRepository;
import sk.stuba.fei.uim.oop.assignment3.shoppingCart.web.body.ProductIdRequest;

@Service
public class CartService implements ICartService {

    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private IProductService productService;

    @Override
    public Cart create() {
        return this.cartRepository.save(new Cart());
    }

    @Override
    public Cart getById(long cartId) throws NotFoundException {
        Cart c = this.cartRepository.findCartById(cartId);
        if (c == null) {
            throw new NotFoundException();
        }
        return c;
    }

    @Override
    public void delete(long cartId) throws NotFoundException{
        Cart c = this.cartRepository.findCartById(cartId);
        if (c == null) {
            throw new NotFoundException();
        }
        this.cartRepository.delete(c);
    }


    @Override
    public Cart addProductToCart(long cartId, ProductIdRequest body) throws NotFoundException, IllegalOperationException {
        Cart cart = this.getById(cartId);
        Product product = this.productService.getById(body.getProductId());
        if (product == null) {
            throw new NotFoundException();
        }
        if (cart == null) {
            throw new NotFoundException();
        }
        if (cart.getPayed()) {
            throw new IllegalOperationException();
        }
        if (product.getAmount() <= 0) {
            throw new IllegalOperationException();
        }
        if (cart.getShoppingList().contains(product)) {
            int tmp = cart.getShoppingList().indexOf(product);
            cart.getShoppingList().get(tmp).setAmount(cart.getShoppingList().get(tmp).getAmount() + 1);
        } else {
            cart.getShoppingList().add(product);
            product.setAmount(product.getAmount() - 1);
        }
        return this.cartRepository.save(cart);
    }

    @Override
    public String payForCart(Long cartID) throws NotFoundException, IllegalOperationException {
        Cart cart = this.getById(cartID);
        double price;
        if (cart == null) {
            throw new NotFoundException();
        }
        if (cart.getPayed()) {
            throw new IllegalOperationException();
        }
        else {
            price = 0;
            for (Product product : cart.getShoppingList()) {
                price += product.getPrice();
            }
            cart.setPayed(true);
        }
        this.cartRepository.save(cart);
        return String.valueOf(price);
    }
}
