package com.etfbl.ipbackend.services;

import com.etfbl.ipbackend.exceptions.ExistingException;
import com.etfbl.ipbackend.models.Supply;
import com.etfbl.ipbackend.models.requests.SupplyRequest;
import org.springframework.data.domain.Page;


import java.util.List;
import java.util.Optional;

public interface SupplyService {
    Supply createSupply(SupplyRequest supplyRequest);
    Supply softDeleteProduct(Supply supply);
    Optional<Supply> getSupplyById(Integer id);
    Optional<Supply> getSupplyByProductId(Integer id);

    List<Supply> getSupplies();

    List<Supply> getAllActiveSupply();

    Page<Supply> getAllActiveSupply(Integer pageNo, Integer pageSize, String sortBy, Integer active);


    Supply updateSupply(Integer id, SupplyRequest supplyRequest) throws ExistingException;

    void deleteSupply(Integer id);
}
