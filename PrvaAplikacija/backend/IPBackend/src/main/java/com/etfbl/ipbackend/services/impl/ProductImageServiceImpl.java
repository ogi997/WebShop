package com.etfbl.ipbackend.services.impl;

import com.etfbl.ipbackend.exceptions.ExistingException;
import com.etfbl.ipbackend.models.ProductImage;
import com.etfbl.ipbackend.models.requests.ProductImageRequest;
import com.etfbl.ipbackend.repositories.ProductImageRepository;
import com.etfbl.ipbackend.services.ProductImageService;
import com.etfbl.ipbackend.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ProductImageServiceImpl implements ProductImageService {
    @Value("${productimagedir:}")
    private String dir;
    private final ProductImageRepository productImageRepository;

    public ProductImageServiceImpl(ProductImageRepository productImageRepository) {
        this.productImageRepository = productImageRepository;
    }

    @Override
    public String saveImage(Integer userId, MultipartFile multipartFile) {
        try {
            return ImageUtils.saveFile(dir, multipartFile);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public byte[] getImage(String name) {
        try {
            return ImageUtils.getImage(dir, name);
        } catch (Exception e) {
            return new byte[0];
        }
    }

    @Override
    public byte[] getCoverProductImage(Integer id) {
        ProductImage productImage = productImageRepository.getProductImageByCoverAndFkProductId(1, id);

        try {
            return ImageUtils.getImage(dir, productImage.getName());
        } catch (Exception e) {
            return new byte[0];
        }
//        return productImageRepository.getProductImageByCoverAndFkProductId(1, id);
    }

    @Override
    public List<ProductImage> getProductImagesByFkProductId(Integer id) {
        return productImageRepository.getProductImagesByFkProductId(id);
    }

    @Override
    public List<ProductImage> getProductImageNonCover(Integer id) {
        return productImageRepository.getProductImageByFkProductIdAndCover(id, 0);
    }

    @Override
    public byte[] getProductImageByFkProductIdAndName(Integer id, String name) {
        try {
            return ImageUtils.getImage(dir, name);
        } catch (Exception e) {
            return new byte[0];
        }
//        return productImageRepository.getProductImageByFkProductIdAndName(id, name);
    }

    @Override
    public ProductImage createProductImage(ProductImageRequest productImageRequest) {
        ProductImage productImage = new ProductImage();
        productImage.setFkProductId(productImageRequest.getFkProductId());
        productImage.setCover(productImageRequest.getCover());
        productImage.setName(productImageRequest.getName());
        return productImageRepository.save(productImage);
    }

//    @Override
//    public Optional<ProductImage> getProductImageById(Integer id) {
//        return productImageRepository.findById(id);
//    }

    @Override
    public List<ProductImage> getProductImages() {
        return productImageRepository.findAll();
    }

    @Override
    public ProductImage updateProductImage(Integer id, ProductImageRequest productImageRequest) throws ExistingException {
        Optional<ProductImage> productImageOptional = productImageRepository.findById(id);

        if (productImageOptional.isEmpty())
            throw new ExistingException("Slika za product ID: " + id + " ne postoji u bazi podataka");

        ProductImage productImage =productImageOptional.get();
        productImage.setFkProductId(productImageRequest.getFkProductId());
        productImage.setCover(productImageRequest.getCover());
        productImage.setName(productImageRequest.getName());
        return productImageRepository.save(productImage);
    }

    @Override
    public void deleteProductImage(Integer id) {
        productImageRepository.deleteById(id);
    }
}
