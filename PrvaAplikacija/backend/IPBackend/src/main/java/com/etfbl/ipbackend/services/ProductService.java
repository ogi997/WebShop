package com.etfbl.ipbackend.services;

import com.etfbl.ipbackend.exceptions.ExistingException;
import com.etfbl.ipbackend.models.Product;
import com.etfbl.ipbackend.models.requests.ProductRequest;
import com.etfbl.ipbackend.models.requests.RequestAttFilter;
import com.etfbl.ipbackend.models.requests.ResponseFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {
   Page<Product> filterProductsByAttributes(Integer categoryId,List<RequestAttFilter> attributeValueMap, String conProduct, Double priceOd, Double priceDo, String location, Pageable pageable);

    Product createProduct(ProductRequest productRequest);

    Product softDeleteProduct(Product product);

    Optional<Product> getProductById(Integer id);

    List<Product> getProducts();
    List<Product> getProductsByUserId(Integer id);

    Page<Product> searchProductByTitle(String title, Pageable pageable);
    Page<Product> findProductsByCategoryIdAndActiveInSupply(
            Integer categoryId,
            String conditionProduct,
            Double priceOd,
            Double priceDo,
            String location,
            Pageable pageable);

    Product updateProduct(Integer id, ProductRequest productRequest) throws ExistingException;

    void deleteProduct(Integer id);
}
