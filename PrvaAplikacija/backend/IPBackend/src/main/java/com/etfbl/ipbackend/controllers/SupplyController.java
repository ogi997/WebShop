package com.etfbl.ipbackend.controllers;

import com.etfbl.ipbackend.models.Supply;
import com.etfbl.ipbackend.models.requests.SupplyRequest;
import com.etfbl.ipbackend.services.SupplyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/supplies")
public class SupplyController {
    private final SupplyService supplyService;
    private static final Logger logger = LogManager.getLogger(SupplyController.class);

    public SupplyController(SupplyService supplyService) {
        this.supplyService = supplyService;
    }

    @PostMapping
    public ResponseEntity<Supply> createSupply(@RequestBody SupplyRequest supplyRequest) {
        Supply supply = supplyService.createSupply(supplyRequest);
        logger.info("POST created supply");
        return ResponseEntity.ok(supply);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supply> getSupplyById(@PathVariable("id") Integer id) {
        Optional<Supply> supplyOptional = supplyService.getSupplyById(id);
        logger.info("GET supply by ID");
        return supplyOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

   @GetMapping("/active-supply")
   public ResponseEntity<Page<Supply>> getAllActiveSupply(@RequestParam(defaultValue = "0") Integer pageNo,
                                                          @RequestParam(defaultValue = "1") Integer pageSize,
                                                          @RequestParam(defaultValue = "id") String sortBy) {
       Page<Supply> supplies = supplyService.getAllActiveSupply(pageNo, pageSize, sortBy, 1);
        logger.info("GET all active supply");
       return ResponseEntity.ok(supplies);

   }

    @PutMapping("/change-active/{productId}")
    public void changeActiveBySupplyId(@PathVariable("productId") Integer productId, @RequestBody SupplyRequest supplyRequest) {
        try {
            logger.info("PUT change active supply by product id");
            supplyService.updateSupply(productId, supplyRequest);
            logger.info("PUT changed active supply by product id");
        } catch (Exception e) {
            logger.error("PUT not changed active supply by product id");
        }
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Supply> getSupplyByProductId(@PathVariable("productId") Integer id) {
        Optional<Supply> supplyOptional = supplyService.getSupplyByProductId(id);
        logger.info("GET product by product id");
        return supplyOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping
    public ResponseEntity<List<Supply>> getAllSupply() {
        List<Supply> supplies = supplyService.getSupplies();

        return ResponseEntity.ok(supplies);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supply> updateSupply(@PathVariable("id") Integer id, @RequestBody SupplyRequest supplyRequest) {
        try {
            Supply supply = supplyService.updateSupply(id, supplyRequest);

            return ResponseEntity.ok(supply);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupply(@PathVariable("id") Integer id) {
        supplyService.deleteSupply(id);

        return ResponseEntity.noContent().build();
    }
}
