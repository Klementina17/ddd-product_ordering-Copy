package mk.finki.ukim.emt.productcatalog.services;

import mk.finki.ukim.emt.productcatalog.domain.model.Product;
import mk.finki.ukim.emt.productcatalog.domain.model.ProductId;
import mk.finki.ukim.emt.productcatalog.services.form.ProductForm;

import java.util.List;

public interface ProductService {

    Product findById(ProductId id);
    Product createProduct(ProductForm form);
    Product orderItemCreated(ProductId productId, int quantity);
    Product orderItemRemoved(ProductId productId, int quantity);
    List<Product> getAll();

}
