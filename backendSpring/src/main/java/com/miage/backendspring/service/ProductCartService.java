package com.miage.backendspring.service;

import com.miage.backendspring.repositories.OpenFoodFactsProductRepository;
import com.miage.backendspring.repositories.ProductCartRepository;
import com.miage.backendspring.repositories.UserRepository;
import com.miage.backendspring.entity.ProductCart;
import com.miage.backendspring.entity.diet.OpenFoodFactsProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@RequiredArgsConstructor
@Component
public class ProductCartService {

    private final UserRepository userRepository;
    private final ProductCartRepository productCartRepository;
    private final OpenFoodFactsProductRepository openFoodFactsProductRepository;

    public void addOpenFoodFactsProductsToCart(ProductCart productCart){

        ProductCart cart = userRepository.getOne(productCart.getUser().getUsername()).getProductCart();
        boolean b = openFoodFactsProductRepository.existsById(productCart.getOpenFoodFactsProducts().iterator().next().get_id());

        if(!b){
            cart.getOpenFoodFactsProducts().addAll(productCart.getOpenFoodFactsProducts());
        }
        productCartRepository.save(cart);

    }

    public Set<OpenFoodFactsProduct> getCartElements(String userId){
        ProductCart cart = userRepository.getOne(userId).getProductCart();
        Set<OpenFoodFactsProduct> openFoodFactsProducts = cart.getOpenFoodFactsProducts();
        return openFoodFactsProducts;
    }


    public void deleteProduct(String userId, long productId){
        ProductCart cart = userRepository.getOne(userId).getProductCart();
        cart.getOpenFoodFactsProducts().removeIf(o -> o.get_id() == productId);
        productCartRepository.save(cart);
    }

}
