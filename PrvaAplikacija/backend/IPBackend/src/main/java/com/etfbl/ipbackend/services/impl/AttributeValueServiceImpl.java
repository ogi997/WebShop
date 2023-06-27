package com.etfbl.ipbackend.services.impl;

import com.etfbl.ipbackend.exceptions.ExistingException;
import com.etfbl.ipbackend.models.AttributeValue;
import com.etfbl.ipbackend.models.requests.AttributeValueReqeust;
import com.etfbl.ipbackend.repositories.AttributeValueRepository;
import com.etfbl.ipbackend.services.AttributeValueService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttributeValueServiceImpl implements AttributeValueService {
    private final AttributeValueRepository attributeValueRepository;

    public AttributeValueServiceImpl(AttributeValueRepository attributeValueRepository) {
        this.attributeValueRepository = attributeValueRepository;
    }

    @Override
    public AttributeValue createAttributeValue(AttributeValueReqeust attributeValueRequest) {
        AttributeValue attributeValue = new AttributeValue();
        attributeValue.setValue(attributeValueRequest.getValue());
        attributeValue.setFkAttributeId(attributeValueRequest.getFkAttributeId());
        attributeValue.setFkProizvod(attributeValueRequest.getFkProizvod());
        return attributeValueRepository.save(attributeValue);
    }

    @Override
    public Optional<AttributeValue> getAttributeValueById(Integer id) {
        return attributeValueRepository.findById(id);
    }

    @Override
    public List<AttributeValue> getAllAttributeValues() {
        return attributeValueRepository.findAll();
    }

    @Override
    public List<AttributeValue> getAllAttributeValueByProductId(Integer id) {
        return attributeValueRepository.getAttributeValueByFkProizvod(id);
    }

    @Override
    public AttributeValue updateAttributeValue(Integer id, AttributeValueReqeust attributeValueRequest) throws ExistingException {
        Optional<AttributeValue> optionalAttributeValue = attributeValueRepository.findById(id);
        if (optionalAttributeValue.isEmpty())
            throw new ExistingException("AtributValue sa ID: " + id + " ne postoji u bazi podataka");

        AttributeValue existingAttributeValue = optionalAttributeValue.get();
        existingAttributeValue.setValue(attributeValueRequest.getValue());

        return attributeValueRepository.save(existingAttributeValue);
    }

    @Override
    public void deleteAttributeValue(Integer id) {
        attributeValueRepository.deleteById(id);
    }
}
