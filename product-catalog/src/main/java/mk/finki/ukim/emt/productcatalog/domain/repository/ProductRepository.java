package mk.finki.ukim.emt.productcatalog.domain.repository;

import mk.finki.ukim.emt.productcatalog.domain.model.Product;
import mk.finki.ukim.emt.productcatalog.domain.model.ProductId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, ProductId> {
}
