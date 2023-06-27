package com.etfbl.ipbackend.services;


import com.etfbl.ipbackend.exceptions.ExistingException;
import com.etfbl.ipbackend.models.Attribute;
import com.etfbl.ipbackend.models.requests.AttributeRequest;

import java.util.List;
import java.util.Optional;

public interface AttributeService {
    Attribute createAttribute(AttributeRequest attributeRequest);

    Optional<Attribute> getAttributeById(Integer id);

    List<Attribute> getAllAttributes();

    List<Attribute> getAllAttributesByCategoryId(Integer id);

    Attribute updateAttribute(Integer id, AttributeRequest attributeRequest) throws ExistingException;

    void deleteAttribute(Integer id);

}
