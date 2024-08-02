package sk.stuba.fei.uim.oop.assignment3.shoppingCart.logic;

import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.shoppingCart.data.Cart;
import sk.stuba.fei.uim.oop.assignment3.shoppingCart.web.body.ProductIdRequest;

public interface ICartService {
    Cart create();

    Cart getById(long cartId) throws NotFoundException;

    void delete(long cartId) throws NotFoundException, IllegalOperationException;

    Cart addProductToCart(long cartId, ProductIdRequest body) throws NotFoundException, IllegalOperationException;

    String payForCart(Long cartID) throws NotFoundException, IllegalOperationException;

}
