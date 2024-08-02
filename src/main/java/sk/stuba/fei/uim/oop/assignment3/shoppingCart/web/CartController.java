package sk.stuba.fei.uim.oop.assignment3.shoppingCart.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.exception.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.shoppingCart.logic.ICartService;
import sk.stuba.fei.uim.oop.assignment3.shoppingCart.web.body.CartResponse;
import sk.stuba.fei.uim.oop.assignment3.shoppingCart.web.body.ProductIdRequest;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICartService service;

    @PostMapping
    public ResponseEntity<CartResponse> addCart() {
        return new ResponseEntity<>(new CartResponse(this.service.create()), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public CartResponse getCart(@PathVariable("id") long cartId) throws NotFoundException {
        return new CartResponse(this.service.getById(cartId));
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") long cartId) throws NotFoundException, IllegalOperationException {
        this.service.delete(cartId);
    }

    @PostMapping(value = "/{id}/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CartResponse addProductToCart(@PathVariable("id") Long cartId, @RequestBody ProductIdRequest body) throws NotFoundException, IllegalOperationException{
        return new CartResponse(this.service.addProductToCart(cartId,body));
    }
    @GetMapping(value = "/{id}/pay")
    public String payForCart(@PathVariable("id") Long cartID) throws NotFoundException, IllegalOperationException{
        return service.payForCart(cartID);
    }


}
