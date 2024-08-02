package sk.stuba.fei.uim.oop.assignment3.product.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;
import sk.stuba.fei.uim.oop.assignment3.product.logic.IProductService;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductAmountRequest;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductResponse;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductUpdateRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService service;

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return this.service.getAll().stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody Product body) {
        return new ResponseEntity<>(new ProductResponse(this.service.createProduct(body)), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ProductResponse getProduct(@PathVariable("id") Long productId) throws NotFoundException {
        return new ProductResponse(this.service.getById(productId));
    }
    @PutMapping(value = "/{id}")
    public ProductResponse updateProduct(@PathVariable("id") Long productId, @RequestBody ProductUpdateRequest body) throws NotFoundException {
        return new ProductResponse(this.service.update(productId, body));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProduct(@PathVariable("id") Long productId) throws NotFoundException{
        this.service.delete(productId);
    }
    @PutMapping(value = "/{id}/amount")
    public ProductAmountRequest addAmount(@PathVariable("id") long productId, @RequestBody ProductAmountRequest body) throws NotFoundException{
        return new ProductAmountRequest(this.service.addAmount(productId, body.getAmount()));
    }

    @GetMapping(value = "/{id}/amount")
    public ProductAmountRequest getAmount(@PathVariable("id") Long productId) throws NotFoundException{
        return new ProductAmountRequest(this.service.getAmount(productId));
    }

}

