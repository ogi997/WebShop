package com.etfbl.ipbackend.controllers;

import com.etfbl.ipbackend.models.ProductImage;
import com.etfbl.ipbackend.models.requests.ProductImageRequest;
import com.etfbl.ipbackend.services.ProductImageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product-images")
public class ProductImageController {
    private final ProductImageService productImageService;
    private static final Logger logger = LogManager.getLogger(ProductImageController.class);
    public ProductImageController(ProductImageService productImageService) {
        this.productImageService = productImageService;
    }

    @PostMapping
    public ResponseEntity<ProductImage> createProductImage(@RequestParam("image") MultipartFile file, @RequestParam("productId") Integer productId, @RequestParam("cover") Byte cover) {
        ProductImageRequest productImageRequest = new ProductImageRequest();
        productImageRequest.setFkProductId(productId);
        productImageRequest.setCover(cover);
        String name = productImageService.saveImage(productId, file);
        if (name == null) {
            logger.error("POST product image bad request");
            return ResponseEntity.badRequest().build();
        }

        productImageRequest.setName(name);
        logger.info("POST product image successfully");
        ProductImage productImage = productImageService.createProductImage(productImageRequest);
        return ResponseEntity.ok(productImage);

    }

    @GetMapping(value = "/{productId}")
    public @ResponseBody List<String> getProductImageById(@PathVariable("productId") Integer productId) {
        List<ProductImage> productImage = productImageService.getProductImagesByFkProductId(productId);
        List<String> slike = new ArrayList<>();
        for(ProductImage p : productImage) {
            slike.add(p.getName());
        }
        logger.info("GET product images for product ID");
        return (slike);
    }

    @GetMapping(value= "/{productId}/non-cover")
    public List<String> getAllProductImageByIdNonCover(@PathVariable("productId") Integer id ) {
        List<ProductImage> list = productImageService.getProductImageNonCover(id);
        List<String> slike = new ArrayList<>();
        for(ProductImage p : list) {
            slike.add(p.getName());
        }
        logger.info("GET getting all non cover images for product ID: " + id);
        return slike;
    }

    @GetMapping(value = "/{productId}/{pictureName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getProductImage(@PathVariable("productId") Integer productId, @PathVariable("pictureName") String imageName) {
        logger.info("GET product image for product id by picture name");
        return productImageService.getProductImageByFkProductIdAndName(productId, imageName);
    }

    @GetMapping(value = "/{productId}/cover", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getCoverProductImage(@PathVariable Integer productId) {
        logger.info("GET cover image for product id");
        return productImageService.getCoverProductImage(productId);
    }



    @GetMapping
    public ResponseEntity<List<ProductImage>> getAllProductImage() {
        List<ProductImage> productImages = productImageService.getProductImages();

        return ResponseEntity.ok(productImages);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductImage> updateProductImage(@PathVariable("id") Integer id, @RequestBody ProductImageRequest productImageRequest) {
        try {
            ProductImage productImage = productImageService.updateProductImage(id, productImageRequest);

            return ResponseEntity.ok(productImage);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductImage(@PathVariable("id") Integer id) {
        productImageService.deleteProductImage(id);

        return ResponseEntity.noContent().build();
    }
}
