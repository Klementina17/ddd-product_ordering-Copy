package mk.finki.ukim.emt.productcatalog.xport.rest;

import lombok.AllArgsConstructor;
import mk.finki.ukim.emt.productcatalog.domain.model.Product;
import mk.finki.ukim.emt.productcatalog.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductResource {

    private final ProductService productService;

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

}
