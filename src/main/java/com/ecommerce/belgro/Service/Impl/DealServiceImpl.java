package com.ecommerce.belgro.Service.Impl;

import com.ecommerce.belgro.Model.Deal;
import com.ecommerce.belgro.Model.HomeCategory;
import com.ecommerce.belgro.Repository.DealRepository;
import com.ecommerce.belgro.Repository.HomeCategoryRepository;
import com.ecommerce.belgro.Service.DealService;
import com.ecommerce.belgro.Service.HomeCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DealServiceImpl implements DealService {
    private final DealRepository dealRepository;
    private final HomeCategoryRepository homeCategoryRepository;
    @Override
    public List<Deal> getDeals() {
        return dealRepository.findAll();
    }

    @Override
    public Deal createDeal(Deal deal) {
        HomeCategory category = homeCategoryRepository.findById(deal.getCategory().getId()).orElse(null);
        Deal newDeal = dealRepository.save(deal);
        newDeal.setCategory(category);
        newDeal.setDiscount(deal.getDiscount());
        return dealRepository.save(newDeal);
    }

    @Override
    public Deal updateDeal(Deal deal, Long id) throws Exception {
        Deal existingDeal = dealRepository.findById(id).orElse(null);
        HomeCategory category = homeCategoryRepository.findById(deal.getCategory().getId()).orElse(null);

        if (existingDeal != null) {
            if (deal.getDiscount() != null) {
                existingDeal.setDiscount(deal.getDiscount());
            }
            if (category != null) {
                existingDeal.setCategory(category);
            }
            return dealRepository.save(existingDeal);
        }
        throw new Exception("Deal not found with id " + id);
    }

    @Override
    public void deleteDeal(Long id) {
        Deal deal = dealRepository.findById(id).orElseThrow(() -> new RuntimeException("Deal not found with id " + id));
        dealRepository.delete(deal);
    }
}
