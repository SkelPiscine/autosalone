package com.example.autosalone.service;

import com.example.autosalone.entity.users.Admin;
import com.example.autosalone.entity.users.Customer;
import com.example.autosalone.entity.users.Seller;
import com.example.autosalone.repository.AdminRepository;
import com.example.autosalone.repository.CustomerRepository;
import com.example.autosalone.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;
    private final AdminRepository adminRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Cerca l'utente tra gli Admin
        Admin admin = adminRepository.findByEmail(username);
        if (admin != null) {
            return User.withUsername(admin.getEmail())
                    .password(admin.getPassword())
                    .authorities("ADMIN")
                    .build();
        }

        // Cerca l'utente tra i Customer
        Customer customer = customerRepository.findByEmail(username);
        if (customer != null) {
            return User.withUsername(customer.getEmail())
                    .password(customer.getPassword())
                    .authorities("CUSTOMER")
                    .build();
        }

        // Cerca l'utente tra i Seller
        Seller seller = sellerRepository.findByEmail(username);
        if (seller != null) {
            return User.withUsername(seller.getEmail())
                    .password(seller.getPassword())
                    .authorities("SELLER")
                    .build();
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
