package com.etfbl.ipbackend.repositories;

import com.etfbl.ipbackend.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
    Optional<ProductImage> findById(Integer id);
    List<ProductImage> getProductImagesByFkProductId(Integer id);
    List<ProductImage> getProductImageByFkProductIdAndCover(Integer id, Integer cover);
    byte[] getProductImageByFkProductIdAndName(Integer id, String name);
    ProductImage getProductImageByCoverAndFkProductId(Integer cover, Integer id);
    List<ProductImage> findAll();
    ProductImage save(ProductImage productImage);
    void deleteById(Integer id);
}
