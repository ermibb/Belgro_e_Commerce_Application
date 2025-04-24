package com.ecommerce.belgro.Service.Impl;

import com.ecommerce.belgro.Model.Seller;
import com.ecommerce.belgro.Model.SellerReport;
import com.ecommerce.belgro.Repository.SellerReportRepository;
import com.ecommerce.belgro.Service.SellerReportService;
import com.ecommerce.belgro.Service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerReportImpl implements SellerReportService {
    private  final SellerReportRepository sellerReportRepository;
    @Override
    public SellerReport getSellerReport(Seller seller) {
        SellerReport sellerReport = sellerReportRepository.findBySellerId(seller.getId());

        if(sellerReport ==null){
            SellerReport newReport = new SellerReport();
            newReport.setSeller(seller);
            return sellerReportRepository.save(newReport);
        }
        return sellerReport;
    }

    @Override
    public SellerReport updateSellerReport(SellerReport sellerReport) {
        return sellerReportRepository.save(sellerReport);
    }
}
