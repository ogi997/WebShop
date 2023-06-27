package com.etfbl.ipbackend.controllers;

import com.etfbl.ipbackend.models.Attribute;
import com.etfbl.ipbackend.models.requests.AttributeRequest;
import com.etfbl.ipbackend.services.AttributeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/attributes")
public class AttributeController {
    private final AttributeService attributeService;

    public AttributeController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @PostMapping
    public ResponseEntity<Attribute> createAttribute(@RequestBody AttributeRequest attributeRequest) {
        Attribute attribute = attributeService.createAttribute(attributeRequest);
        return ResponseEntity.ok(attribute);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attribute> getAttributeById(@PathVariable("id") Integer id) {
        Optional<Attribute> attributeOptional = attributeService.getAttributeById(id);
        return attributeOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Attribute>> getAttributesByCategoryId(@PathVariable("id") Integer id) {
        List<Attribute> attributes = attributeService.getAllAttributesByCategoryId(id);

        return ResponseEntity.ok(attributes);
    }

    @GetMapping
    public ResponseEntity<List<Attribute>> getAllAttributes(){
        List<Attribute> attributes = attributeService.getAllAttributes();
        return ResponseEntity.ok(attributes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attribute> updateAttribute(@PathVariable("id") Integer id, @RequestBody AttributeRequest attributeRequest) {
        try {
            Attribute attribute = attributeService.updateAttribute(id, attributeRequest);
            return ResponseEntity.ok(attribute);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttribute(@PathVariable("id") Integer id) {
        attributeService.deleteAttribute(id);
        return ResponseEntity.noContent().build();
    }
}
