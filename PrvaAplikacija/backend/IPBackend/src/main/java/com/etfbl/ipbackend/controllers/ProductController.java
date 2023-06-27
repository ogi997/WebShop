package com.etfbl.ipbackend.controllers;

import com.etfbl.ipbackend.models.Product;
import com.etfbl.ipbackend.models.Supply;
import com.etfbl.ipbackend.models.requests.ProductRequest;
import com.etfbl.ipbackend.models.requests.RequestAttFilter;
import com.etfbl.ipbackend.services.ProductService;
import com.etfbl.ipbackend.services.SupplyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final SupplyService supplyService;

    private static final Logger logger = LogManager.getLogger(ProductController.class);

    public ProductController(ProductService productService, SupplyService supplyService) {
        this.productService = productService;
        this.supplyService = supplyService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest) {
        productRequest.setDeleted((byte)0);
        Product product = productService.createProduct(productRequest);
        logger.info("POST dodan je novi product");
        return ResponseEntity.ok(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) {
        Optional<Product> productOptional = productService.getProductById(id);
        logger.info("GET product ID: " + id);
        return productOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Product>> getProductsByUserId(@PathVariable("userId") Integer id) {
        List<Product> products = productService.getProductsByUserId(id);
        logger.info("GET product by userID: " + id);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search-title")
    public Page<Product> searchProductByTitle(
            @RequestParam("title") String title,
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        logger.info("Pretraga proizvoda po title: " + title);
        return productService.searchProductByTitle(title, pageable);
    }

    @PostMapping("/filter")
    public ResponseEntity<Page<Product>> filter(
            @RequestParam("categoryId") Integer categoryId,
            @RequestBody List<RequestAttFilter> attributes,
            @RequestParam(value = "conProduct", required = false) String conditionProduct,
            @RequestParam(value = "priceOd", required = false) Double priceOd,
            @RequestParam(value = "priceDo", required = false) Double priceDo,
            @RequestParam(value = "location", required = false) String location,
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size){

        Pageable pageable = PageRequest.of(page, size);
        logger.info("Filtriranje proizvoda po product condition, min price, max price and location");
        if (location.isBlank()) location = null;
        if (conditionProduct.isBlank()) conditionProduct = null;

       Page<Product> lists = productService.filterProductsByAttributes(
               categoryId,
               attributes,
               conditionProduct,
               priceOd,
               priceDo,
               location,
               pageable);
        logger.info("GET filtered data.");
        return ResponseEntity.ok(lists);
    }


    @GetMapping("/filter-category")
    public Page<Product> filterProductsByCategoryIdAndActiveInSupply(
            @RequestParam(value = "categoryId", required = false) Integer categoryId,
            @RequestParam(value = "conProduct", required = false) String conditionProduct,
            @RequestParam(value = "priceOd", required = false) Double priceOd,
            @RequestParam(value = "priceDo", required = false) Double priceDo,
            @RequestParam(value = "location", required = false) String location,
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        logger.info("Filtriranje po kategoriji, stanju proizvoda, min price, max price i location");
        if (location.isBlank()) location = null;
        if (conditionProduct.isBlank()) conditionProduct = null;

        logger.info("GET filtered data.");
        return productService.findProductsByCategoryIdAndActiveInSupply(
                categoryId,
                conditionProduct,
                priceOd,
                priceDo,
                location,
                pageable);
    }


    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> products = productService.getProducts();

        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @RequestBody ProductRequest productRequest) {
        try {
            Product product = productService.updateProduct(id, productRequest);

            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("delete/{productId}/{deleteValue}")
    public ResponseEntity<Product> softDeleteProduct(@PathVariable("productId") Integer id, @PathVariable("deleteValue") Byte delete) {
        Optional<Product> productOptional = productService.getProductById(id);

        if (productOptional.isPresent()) {
            //postoji product
            //nadji supply
            Product product = productOptional.get();
            Optional<Supply> optionalSupply = supplyService.getSupplyByProductId(product.getId());

            //provjeri da li postoji supply
            if (optionalSupply.isPresent()) {
                Supply supply = optionalSupply.get();
                supply.setActive((byte)0);
                logger.info("DELETE product je povucen sa supply");
            } else {
                logger.error("DELETE no content");
                return ResponseEntity.noContent().build();
            }

            product.setDeleted(delete);
            logger.info("DELETE product je obrisan");
            Product p = productService.softDeleteProduct(product);
            return ResponseEntity.ok(p);
        } else {
            logger.error("DELETE no content");
            return ResponseEntity.noContent().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }
}
