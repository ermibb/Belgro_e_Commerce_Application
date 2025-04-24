package com.ecommerce.belgro.Service;

import com.ecommerce.belgro.Model.Seller;
import com.ecommerce.belgro.Model.SellerReport;

public interface SellerReportService {
    SellerReport getSellerReport(Seller seller);
    SellerReport updateSellerReport(SellerReport sellerReport);
}
