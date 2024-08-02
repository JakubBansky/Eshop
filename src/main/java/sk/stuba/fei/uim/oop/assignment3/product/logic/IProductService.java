package sk.stuba.fei.uim.oop.assignment3.product.logic;

import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductUpdateRequest;

import java.util.List;


public interface IProductService {


    List<Product> getAll();

    Product createProduct(Product body);

    Product getById(long productId) throws NotFoundException;

    Product update(long productId, ProductUpdateRequest body) throws NotFoundException;

    void delete(long productId) throws NotFoundException;

    long addAmount(long productId, long number) throws NotFoundException;

    long getAmount(long productId) throws NotFoundException;
}
