package com.etfbl.ipbackend.services;

import com.etfbl.ipbackend.exceptions.ExistingException;
import com.etfbl.ipbackend.models.ProductImage;
import com.etfbl.ipbackend.models.requests.ProductImageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductImageService {
    ProductImage createProductImage(ProductImageRequest productImageRequest);

    String saveImage(Integer userId, MultipartFile multipartFile);
//
    byte[] getImage(String name);

    byte[] getCoverProductImage(Integer id);

    List<ProductImage> getProductImagesByFkProductId(Integer id);

    List<ProductImage> getProductImageNonCover(Integer id);

    byte[] getProductImageByFkProductIdAndName(Integer id, String name);

    List<ProductImage> getProductImages();

    ProductImage updateProductImage(Integer id, ProductImageRequest productImageRequest) throws ExistingException;

    void deleteProductImage(Integer id);
}
