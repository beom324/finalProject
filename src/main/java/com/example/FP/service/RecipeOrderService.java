package com.example.FP.service;

import com.example.FP.repository.RecipeOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeOrderService {

    private final RecipeOrderRepository ror;
}
