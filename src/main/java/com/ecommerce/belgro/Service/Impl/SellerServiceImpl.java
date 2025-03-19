package com.ecommerce.belgro.Service.Impl;

import com.ecommerce.belgro.Config.JwtProvider;
import com.ecommerce.belgro.Domain.AccountStatus;
import com.ecommerce.belgro.Domain.USER_ROLE;
import com.ecommerce.belgro.Model.Address;
import com.ecommerce.belgro.Model.Seller;
import com.ecommerce.belgro.Repository.AddressRepository;
import com.ecommerce.belgro.Repository.SellerRepository;
import com.ecommerce.belgro.Service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {
    private  final SellerRepository sellerRepository;
    private  final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;

    @Override
    public Seller getSellerProfile(String jwt) throws Exception {
        String email = jwtProvider.getEmailFormJwtToken(jwt);
        return this.getSellerByEmail(email);
    }

    @Override
    public Seller createSeller(Seller seller) throws Exception {
        Seller sellerExist = sellerRepository.findByEmail(seller.getEmail());
        if (sellerExist != null) {
            throw  new Exception("Seller already exist with email - "+seller.getEmail());
        }
        Address savedAddress = addressRepository.save(seller.getPickupAddress());
        Seller newSeller = new Seller();
        newSeller.setSellerName(seller.getSellerName());
        newSeller.setMobile(seller.getMobile());
        newSeller.setEmail(seller.getEmail());
        newSeller.setPassword(passwordEncoder.encode(seller.getPassword()));
        newSeller.setBusinessDetails(seller.getBusinessDetails());
        newSeller.setBankDetails(seller.getBankDetails());
        newSeller.setPickupAddress(savedAddress);
        newSeller.setGSTIN(seller.getGSTIN());
        newSeller.setRole(USER_ROLE.ROLE_SELLER);
        return sellerRepository.save(newSeller);
    }

    @Override
    public Seller getSellerById(Long id) throws Exception {
        Seller seller = sellerRepository.findById(id).orElseThrow(()-> new Exception("Seller not found with id - "+id));
        return seller;
    }

    @Override
    public Seller getSellerByEmail(String email) throws Exception {
        Seller seller = sellerRepository.findByEmail(email);
        if (seller ==null) {
            throw new Exception("Seller not found with email - "+email);
        }
        return seller;
    }

    @Override
    public List<Seller> getAllSellers(AccountStatus status) {
        return sellerRepository.findByAccountStatus(status);
    }

    @Override
    public Seller updateSeller(Long id, Seller seller) throws Exception {
        return null;
    }

    @Override
    public void deleteSeller(Long id) {

    }

    @Override
    public Seller verifyEmail(String email, String otp) {
        return null;
    }

    @Override
    public Seller updateSellerAccountStatus(Long id, AccountStatus status) {
        return null;
    }
}
