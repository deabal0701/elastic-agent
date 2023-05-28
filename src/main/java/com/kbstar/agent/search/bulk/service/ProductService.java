package com.kbstar.agent.search.bulk.service;

import org.elasticsearch.search.aggregations.metrics.Sum;
import org.springframework.stereotype.Service;

import com.kbstar.agent.search.bulk.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Sum getSumPriceByProduct() {
        return repository.getSumPriceByProduct();
    }
}