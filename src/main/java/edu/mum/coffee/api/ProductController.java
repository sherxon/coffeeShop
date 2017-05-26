package edu.mum.coffee.api;

import edu.mum.coffee.domain.Product;
import edu.mum.coffee.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sherxon on 5/23/17.
 */
@RestController(value = "RestProductController")
@RequestMapping(value = "/api/products")
public class ProductController {

  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping({"", "/"})
  public List<Product> get() {
    return productService.findAll();
  }


  @PostMapping("")
  @PreAuthorize(value = "hasAuthority('ADMIN')")
  public Product create(Product product) {
    productService.save(product);
    return product;
  }

  @DeleteMapping("/{id}")
  @PreAuthorize(value = "hasAuthority('ADMIN')")
  public Product create(@PathVariable Integer id) {
    Product product=productService.find(id);
    productService.delete(product);
    return product;
  }

  @PutMapping("/{id}")
  @PreAuthorize(value = "hasAuthority('ADMIN')")
  public Product put(@PathVariable Integer id,  Product product) {
    Product product1=productService.find(id);
    product1.setDescription(product.getDescription());
    product1.setPrice(product.getPrice());
    product1.setProductName(product.getProductName());
    product1.setProductType(product.getProductType());
    productService.save(product1);
    return product;
  }

}
