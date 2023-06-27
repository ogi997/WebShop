package com.etfbl.ipbackend.services.impl;

import com.etfbl.ipbackend.exceptions.ExistingException;
import com.etfbl.ipbackend.models.Attribute;
import com.etfbl.ipbackend.models.requests.AttributeRequest;
import com.etfbl.ipbackend.repositories.AttributeRepository;
import com.etfbl.ipbackend.services.AttributeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttributeServiceImpl implements AttributeService {
    private final AttributeRepository attributeRepository;

    public AttributeServiceImpl(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }

    @Override
    public Attribute createAttribute(AttributeRequest attributeRequest) {
        Attribute attribute = new Attribute();

        attribute.setName(attributeRequest.getName());

        return attributeRepository.save(attribute);
    }

    @Override
    public Optional<Attribute> getAttributeById(Integer id) {
        return attributeRepository.findById(id);
    }

    @Override
    public List<Attribute> getAllAttributes() {
        return attributeRepository.findAll();
    }

    @Override
    public List<Attribute> getAllAttributesByCategoryId(Integer id) {
        return attributeRepository.getAttributesByFkCategoryId(id);
    }

    @Override
    public Attribute updateAttribute(Integer id, AttributeRequest attributeRequest) throws ExistingException {
        Optional<Attribute> optionalAttribute = attributeRepository.findById(id);
        if (optionalAttribute.isEmpty())
            throw new ExistingException("Atribut sa ID: " + id + " ne postoji u bazi podataka");

        Attribute existingAttribute = optionalAttribute.get();
        existingAttribute.setName(attributeRequest.getName());

        return attributeRepository.save(existingAttribute);
    }

    @Override
    public void deleteAttribute(Integer id) {
        attributeRepository.deleteById(id);
    }
}
