package com.etfbl.ipbackend.repositories;

import com.etfbl.ipbackend.models.Product;
import com.etfbl.ipbackend.models.requests.ResponseFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    //@Query("SELECT av.fkProizvod, COUNT(pr.id) as numOfProduct FROM Product pr JOIN AttributeValue av WHERE pr.fkCategory = :categoryId AND av.fkAttributeId IN (:attributeIds) AND av.value IN (:attributeValues) GROUP BY av.fkProizvod HAVING numOfProduct = :numberOfAttribute")
    @Query("SELECT p FROM Product p JOIN AttributeValue av ON p.id = av.fkProizvod JOIN Supply s ON p.id = s.fkProd WHERE p.fkCategory = :categoryId AND s.active = 1 AND (:conProduct IS NULL OR p.conditionProduct = :conProduct) AND (:priceOd IS NULL OR p.price >= :priceOd) AND (:priceDo IS NULL OR p.price <= :priceDo) AND (:location IS NULL OR p.location = :location) AND av.fkAttributeId IN (:attributeIds) AND av.value IN (:attributeValues) GROUP BY p.id HAVING COUNT(p.id) = :numberOfAttribute")
    Page<Product> filter(@Param("categoryId") Integer categoryId, @Param("attributeIds") List<Integer> attributeIds, @Param("attributeValues") List<String> attributeValues, @Param("numberOfAttribute") Integer numberOfAttribute, @Param("conProduct") String conProduct, @Param("priceOd") Double priceOd, @Param("priceDo") Double priceDo, @Param("location") String location, Pageable pageable);

    Optional<Product> findById(Integer id);
    List<Product> findAll();
    List<Product> findAllByDeleted(Integer delete);
    @Query("SELECT DISTINCT p FROM Product p " +
            "JOIN Supply s ON p.id = s.fkProd " +
            "WHERE s.active = 1 AND LOWER(p.title) LIKE LOWER(concat('%', :titleSearch,'%'))")
    Page<Product> searchProductsByTitle(@Param("titleSearch") String titleSearch, Pageable pageable);

    @Query("SELECT DISTINCT p FROM Product p " +
            "JOIN Supply s ON p.id = s.fkProd " +
            "WHERE (:categoryId IS NULL OR p.fkCategory = :categoryId) AND s.active = 1 AND (:condition IS NULL OR p.conditionProduct = :condition) AND (:minPrice IS NULL OR p.price >= :minPrice) AND (:maxPrice IS NULL OR p.price <= :maxPrice) AND (:location IS NULL OR p.location = :location)")
    Page<Product> findProductsByCategoryIdAndActiveInSupply(@Param("categoryId") Integer categoryId, @Param("condition") String condition, @Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice, @Param("location") String location, Pageable pageable);
    List<Product> getProductsByFkUserAndDeleted(Integer id, Integer deleted);
    Product save(Product product);
    void deleteById(Integer id);
}
