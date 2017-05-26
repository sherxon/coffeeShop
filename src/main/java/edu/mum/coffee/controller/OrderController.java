package edu.mum.coffee.controller;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Orderline;
import edu.mum.coffee.service.OrderService;
import edu.mum.coffee.service.PersonService;
import edu.mum.coffee.service.ProductService;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by sherxon on 5/23/17.
 */
@RestController
@RequestMapping(value = "/orders")
public class OrderController {

  @Autowired
  private OrderService orderService;

  @Autowired
  private PersonService personService;

  @Autowired
  private ProductService productService;

  @GetMapping("/view")
  public ModelAndView homePage() {
    ModelAndView modelAndView= new ModelAndView("orders");
    modelAndView.addObject("orders", new ArrayList<>());
    return modelAndView;
  }

  @PostMapping("")
  public void create1(@RequestParam Map<String, String> map) {
    System.out.println(map);
    Order order=new Order();
    if(!map.isEmpty()){
      User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      order.setPerson(personService.findByEmail(user.getUsername()));
      order.setOrderDate(new Date());
      for (String productId : map.keySet()) {
        Orderline orderline= new Orderline();
        orderline.setOrder(order);
        orderline.setProduct(productService.find(Integer.parseInt(productId)));
        orderline.setQuantity(Integer.parseInt(map.get(productId)));
        order.addOrderLine(orderline);
      }
      orderService.save(order);
    }
  }


}
