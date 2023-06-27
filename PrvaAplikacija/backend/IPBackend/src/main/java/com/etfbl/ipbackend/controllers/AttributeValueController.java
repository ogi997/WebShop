package com.etfbl.ipbackend.controllers;

import com.etfbl.ipbackend.models.AttributeValue;
import com.etfbl.ipbackend.models.requests.AttributeValueReqeust;
import com.etfbl.ipbackend.services.AttributeValueService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/attribute-values")
public class AttributeValueController {
    private final AttributeValueService attributeValueService;
    private static final Logger logger = LogManager.getLogger(AttributeValueController.class);

    public AttributeValueController(AttributeValueService attributeValueService) {
        this.attributeValueService = attributeValueService;
    }

    @PostMapping
    public ResponseEntity<AttributeValue> createAttributeValue(@RequestBody AttributeValueReqeust attributeValueReqeust) {
        AttributeValue attributeValue = attributeValueService.createAttributeValue(attributeValueReqeust);
        logger.info("Dodana je nova vrijednost za atribut u odredjeni proizvod.");
        return ResponseEntity.ok(attributeValue);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttributeValue> getAttributeValueById(@PathVariable("id") Integer id) {
        Optional<AttributeValue> attributeValueOptional = attributeValueService.getAttributeValueById(id);
        logger.info("GET za vrijednost atributa po ID: " + id);
        return attributeValueOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<List<AttributeValue>> getAttributeValueByProductId(@PathVariable("id") Integer id) {
        List<AttributeValue> attributeValues = attributeValueService.getAllAttributeValueByProductId(id);
        logger.info("GET vrijednost atributa po PRODUCT ID: " + id);
        return ResponseEntity.ok(attributeValues);
    }

    @GetMapping
    public ResponseEntity<List<AttributeValue>> getAllAttributeValues() {
        List<AttributeValue> attributeValues = attributeValueService.getAllAttributeValues();

        return ResponseEntity.ok(attributeValues);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttributeValue> updateAttributeValue(@PathVariable("id") Integer id, @RequestBody AttributeValueReqeust attributeValueReqeust) {
        try {
            AttributeValue attributeValue = attributeValueService.updateAttributeValue(id, attributeValueReqeust);

            return ResponseEntity.ok(attributeValue);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttributeValue(@PathVariable("id") Integer id) {
        attributeValueService.deleteAttributeValue(id);

        return ResponseEntity.noContent().build();
    }
}
