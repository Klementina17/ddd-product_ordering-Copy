package mk.finki.ukim.emt.productcatalog.config;

import lombok.AllArgsConstructor;
import mk.finki.ukim.emt.productcatalog.domain.model.Product;
import mk.finki.ukim.emt.sharedkernel.domain.types.TypesCth;
import mk.finki.ukim.emt.sharedkernel.domain.types.TypesofClothes;
import mk.finki.ukim.emt.productcatalog.domain.repository.ProductRepository;
import mk.finki.ukim.emt.sharedkernel.domain.financial.Currency;
import mk.finki.ukim.emt.sharedkernel.domain.financial.Money;
import mk.finki.ukim.emt.sharedkernel.domain.sizes.Sizes;
import mk.finki.ukim.emt.sharedkernel.domain.sizes.SizesCth;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final ProductRepository productRepository;

    @PostConstruct
    public void initData() {
        Product p1 =Product.build("Long Skirt","Long skirt with high waist.", TypesCth.valueOf(TypesofClothes.Skirt), SizesCth.valueOf(Sizes.XS),Money.valueOf(Currency.MKD,1290),10);
        Product p2 = Product.build("Floral Dress", "Short dress with neck tie.",TypesCth.valueOf(TypesofClothes.Dresses),SizesCth.valueOf(Sizes.S),Money.valueOf(Currency.MKD,2000), 5);
        if (productRepository.findAll().isEmpty()) {
            productRepository.saveAll(Arrays.asList(p1,p2));
        }
    }

}
