package sk.stuba.fei.uim.oop.assignment3.product.logic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.product.data.IProductRepository;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;
import sk.stuba.fei.uim.oop.assignment3.exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductUpdateRequest;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository repository;

    @Override
    public List<Product> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Product createProduct(Product body) {
        Product p = new Product();
        p.setName(body.getName());
        p.setUnit(body.getUnit());
        p.setPrice(body.getPrice());
        p.setAmount(body.getAmount());
        p.setDescription(body.getDescription());

        return this.repository.save(p);
    }

    @Override
    public Product getById(long productId) throws NotFoundException {
        Product p = this.repository.findProductById(productId);
        if (p == null) {
            throw new NotFoundException();
        }
        return p;
    }

    @Override
    public Product update(long productId, ProductUpdateRequest body) throws NotFoundException {
        Product p = this.getById(productId);
        if (body.getName() != null) {
            p.setName(body.getName());
        }
        if (body.getDescription() != null) {
            p.setDescription(body.getDescription());
        }

        return this.repository.save(p);
    }

    @Override
    public void delete(long productId) throws NotFoundException {
        this.repository.delete(this.getById(productId));
    }

    @Override
    public long addAmount(long productId, long number) throws NotFoundException {
        Product p = this.getById(productId);
        p.setAmount(p.getAmount() + number);
        this.repository.save(p);
        return p.getAmount();
    }

    @Override
    public long getAmount(long productId) throws NotFoundException {
        return this.getById(productId).getAmount();
    }
}



