package com.nocontry.ecommerce.services;

import com.nocontry.ecommerce.entities.AppUser;
import com.nocontry.ecommerce.entities.CartDetailEntity;
import com.nocontry.ecommerce.entities.CartEntity;
import com.nocontry.ecommerce.repositories.CartDetailRepository;
import com.nocontry.ecommerce.repositories.CartRepository;
import com.nocontry.ecommerce.repositories.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
@AllArgsConstructor
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CartDetailRepository cartDetailRepository;

    public Optional<CartEntity> save(CartEntity cart) {
        CartEntity newCart = cartRepository.save(cart);

        return Optional.of(newCart);
    }

    private Optional<AppUser> getUser(String email) throws Exception {
        Optional<AppUser> user = userRepo.findByEmail(email);
        if (user.isEmpty()) {
            throw new Exception("The user is not found.");
        }

        return user;
    }

    public Optional<CartEntity> getActive(String userEmail) throws Exception {
        Optional<AppUser> user = getUser(userEmail);
        Optional<CartEntity> activeCart = cartRepository
                .findOne(
                        Example.of(
                                CartEntity.builder()
                                        .isPurchased(false)
                                        .user(user.get())
                                        .build()
                        )
                );
        if (activeCart.isEmpty()) {
            activeCart = Optional.of(
                    cartRepository.save(
                            CartEntity.builder()
                                    .createdAt(new Date())
                                    .user(user.get())
                                    .build()
                    )
            );
        }
        try {
            Float total = activeCart.get().getProducts().stream()
                    .map(product -> product.getPrice()*product.getQuantity().floatValue())
                    .reduce(0F, (subtotal, price) -> subtotal + price);
            activeCart.get().setTotal(total);
        } catch (Exception err) {
            log.error(err.getMessage());
        }

        return activeCart;
    }

    public List<CartDetailEntity> addProducts(String userEmail, List<CartDetailEntity> products) throws Exception {
        Optional<CartEntity> activeCart = getActive(userEmail);

        products.stream().forEach(product -> {
            product.setCart(activeCart.get());
            CartDetailEntity prod = cartDetailRepository.save(product);
            product.setProductId(product.getId());
        });
        activeCart.get().setProducts(products);

        return products;
    }

}
