package edu.mum.coffee.controller;

import edu.mum.coffee.domain.Product;
import edu.mum.coffee.service.ProductService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by sherxon on 5/23/17.
 */
@RestController
@RequestMapping(value = "/products")
public class ProductController {

  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/add")
  @PreAuthorize(value = "hasAuthority('ADMIN')")
  public ModelAndView add() {
    ModelAndView modelAndView= new ModelAndView("add");

    Collection<? extends GrantedAuthority> authorities= SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    boolean isAdmin = authorities.contains(new SimpleGrantedAuthority("ADMIN"));
    return modelAndView;
  }

  @PostMapping("/add")
  @PreAuthorize(value = "hasRole('ADMIN')")
  public ModelAndView crea(Product product) {
    ModelAndView modelAndView= new ModelAndView("add");
    productService.save(product);
    modelAndView.addObject("successMessage", "Product has been saved successfully");
    return modelAndView;
  }
}
