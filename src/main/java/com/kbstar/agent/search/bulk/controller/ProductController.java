package com.kbstar.agent.search.bulk.controller;

import org.elasticsearch.search.aggregations.metrics.Sum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kbstar.agent.search.bulk.service.ProductService;

@RestController
@RequestMapping("/api/bulk")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/sum-price")
    public Sum getSumPriceByProduct() {
        return service.getSumPriceByProduct();
    }
}
