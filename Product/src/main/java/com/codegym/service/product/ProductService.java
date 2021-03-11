package com.codegym.service.product;

import com.codegym.exception.NotFoundException;
import com.codegym.model.Product;
import com.codegym.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public Iterable<Product> findALl() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findALl(Pageable pageable) {
        return productRepository.findAllByOrderByIdAsc(pageable);
    }

    @Override
    public Product findById(Long id) throws NotFoundException {
        Product product = productRepository.findOne(id);
        if (product != null) {
            return product;
        }
        else throw new NotFoundException();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.delete(id);
    }

    @Override
    public List<Product> findProductName(String name) {
        name = "%" + name + "%";
        return productRepository.findProductName(name);
    }
}
