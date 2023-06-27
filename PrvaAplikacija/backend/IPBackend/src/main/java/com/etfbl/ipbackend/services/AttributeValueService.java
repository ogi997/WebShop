package com.etfbl.ipbackend.services;

import com.etfbl.ipbackend.exceptions.ExistingException;
import com.etfbl.ipbackend.models.AttributeValue;
import com.etfbl.ipbackend.models.requests.AttributeValueReqeust;

import java.util.List;
import java.util.Optional;

public interface AttributeValueService {
    AttributeValue createAttributeValue(AttributeValueReqeust attributeValueRequest);

    Optional<AttributeValue> getAttributeValueById(Integer id);

    List<AttributeValue> getAllAttributeValues();
    List<AttributeValue> getAllAttributeValueByProductId(Integer id);

    AttributeValue updateAttributeValue(Integer id, AttributeValueReqeust attributeValueRequest) throws ExistingException;

    void deleteAttributeValue(Integer id);
}
