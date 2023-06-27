package com.etfbl.ipbackend.models.requests;

public class ResponseFilter {
    private Integer fkProduct;
    private Integer numOfProduct;

    public Integer getFkProduct() {
        return fkProduct;
    }

    public void setFkProduct(Integer fkProduct) {
        this.fkProduct = fkProduct;
    }

    public Integer getNumOfProduct() {
        return numOfProduct;
    }

    public void setNumOfProduct(Integer numOfProduct) {
        this.numOfProduct = numOfProduct;
    }
}
