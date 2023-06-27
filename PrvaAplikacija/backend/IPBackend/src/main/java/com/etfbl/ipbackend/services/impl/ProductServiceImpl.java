package com.etfbl.ipbackend.services.impl;

import com.etfbl.ipbackend.exceptions.ExistingException;
import com.etfbl.ipbackend.models.Product;
import com.etfbl.ipbackend.models.requests.ProductRequest;
import com.etfbl.ipbackend.models.requests.RequestAttFilter;
import com.etfbl.ipbackend.models.requests.ResponseFilter;
import com.etfbl.ipbackend.repositories.ProductRepository;
import com.etfbl.ipbackend.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;


    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> filterProductsByAttributes(Integer categoryId, List<RequestAttFilter> attributeValueMap, String conProduct, Double priceOd, Double priceDo, String location, Pageable pageable) {


//        List<Integer> attributeIds = Arrays.asList(1, 2, 3);
//        List<String> attributeValues = Arrays.asList("Stan", "2", "75");
        List<Integer> attributeIds = new ArrayList<>();
        List<String> attributeValues = new ArrayList<>();

        for (RequestAttFilter r : attributeValueMap) {
            attributeIds.add(r.getAttributeId());
            attributeValues.add(r.getAttributeValue());
        }

        Page<Product> lists = productRepository.filter(categoryId, attributeIds, attributeValues, attributeValues.size(), conProduct, priceOd, priceDo, location, pageable);

        return lists;
        //   Specification<Product> spec = ProductSpecifications.withAttributes(attributeValueMap);
        //    return productRepository.filter(spec);
    }

    @Override
    public Product createProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setConditionProduct(productRequest.getConditionProduct());
        product.setDeleted(productRequest.getDeleted());
        product.setContact(productRequest.getContact());
        product.setDescription(productRequest.getDescription());
        product.setLocation(productRequest.getLocation());
        product.setFkUser(productRequest.getFkUser());
        product.setPrice(productRequest.getPrice());
        product.setFkCategory(productRequest.getFkCategory());
        product.setTitle(productRequest.getTitle());
        return productRepository.save(product);
    }

    @Override
    public Product softDeleteProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAllByDeleted(0);
    }

    @Override
    public List<Product> getProductsByUserId(Integer id) {
        return productRepository.getProductsByFkUserAndDeleted(id, 0);
    }

    @Override
    public Page<Product> searchProductByTitle(String title, Pageable pageable) {
        return productRepository.searchProductsByTitle(title, pageable);
    }

    @Override
    public Page<Product> findProductsByCategoryIdAndActiveInSupply(
            Integer categoryId,
            String conditionProduct,
            Double priceOd,
            Double priceDo,
            String location,
            Pageable pageable) {
        return productRepository.findProductsByCategoryIdAndActiveInSupply(
                categoryId,
                conditionProduct,
                priceOd,
                priceDo,
                location,
                pageable);
    }

    @Override
    public Product updateProduct(Integer id, ProductRequest productRequest) throws ExistingException {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isEmpty())
            throw new ExistingException("Product sa ID: " + id + " ne postoji u bazi podataka");

        Product product = productOptional.get();
        product.setDeleted(productRequest.getDeleted());
        product.setConditionProduct(productRequest.getConditionProduct());
        product.setContact(productRequest.getContact());
        product.setDescription(productRequest.getDescription());
        product.setLocation(productRequest.getLocation());
        product.setFkUser(productRequest.getFkUser());
        product.setPrice(productRequest.getPrice());
        product.setFkCategory(productRequest.getFkCategory());
        product.setTitle(productRequest.getTitle());
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
}
