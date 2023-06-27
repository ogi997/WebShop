package com.etfbl.ipbackend.services.impl;

import com.etfbl.ipbackend.exceptions.ExistingException;
import com.etfbl.ipbackend.models.Supply;
import com.etfbl.ipbackend.models.requests.SupplyRequest;
import com.etfbl.ipbackend.repositories.SupplyRepository;
import com.etfbl.ipbackend.services.SupplyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplyServiceImpl implements SupplyService {
    private final SupplyRepository supplyRepository;

    public SupplyServiceImpl(SupplyRepository supplyRepository) {
        this.supplyRepository = supplyRepository;
    }

    @Override
    public Supply createSupply(SupplyRequest supplyRequest) {
        Supply supply = new Supply();
        supply.setActive(supplyRequest.getActive());
        supply.setFkProd(supplyRequest.getFkProd());
        return supplyRepository.save(supply);
    }

    @Override
    public Supply softDeleteProduct(Supply supply) {
        return supplyRepository.save(supply);
    }

    @Override
    public Optional<Supply> getSupplyById(Integer id) {
        return supplyRepository.findById(id);
    }

    @Override
    public Optional<Supply> getSupplyByProductId(Integer id) {
        return supplyRepository.findByFkProd(id);
    }

    @Override
    public List<Supply> getSupplies() {
        return supplyRepository.findAll();
    }

    @Override
    public List<Supply> getAllActiveSupply() {
        return supplyRepository.getSupplyByActive(1);
    }

    @Override
    public Page<Supply> getAllActiveSupply(Integer pageNo, Integer pageSize, String sortBy, Integer active) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return supplyRepository.getSupplyByActive(pageable, active);
    }


    @Override
    public Supply updateSupply(Integer id, SupplyRequest supplyRequest) throws ExistingException {
        Optional<Supply> supplyOptional = supplyRepository.findByFkProd(id);
//        Optional<Product> product = productRepository.findById(id);

        if (supplyOptional.isEmpty())
            throw new ExistingException("Product sa ID: " + id + " ne postoji u bazi podataka");

        Supply supply = supplyOptional.get();
        supply.setActive(supplyRequest.getActive());
        supply.setFkProd(supplyRequest.getFkProd());
        return supplyRepository.save(supply);
    }

    @Override
    public void deleteSupply(Integer id) {
        supplyRepository.deleteById(id);
    }
}
