package edu.mum.coffee.api;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.service.OrderService;
import edu.mum.coffee.service.PersonService;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
@RestController(value = "RestOrderController")
@RequestMapping(value = "/api/orders")
public class OrderController {

  private final OrderService orderService;

  private final PersonService personService;

  @Autowired
  public OrderController(PersonService personService, OrderService orderService) {
    this.personService = personService;
    this.orderService = orderService;
  }

  @GetMapping({"/", ""})
  public List<Order> get() {
    Collection<? extends GrantedAuthority> authorities=SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    boolean isAdmin = authorities.contains(new SimpleGrantedAuthority("ADMIN"));
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Person person=personService.findByEmail(user.getUsername());
    if(isAdmin){
      return orderService.findAll();
    }else{
      return orderService.findByPerson(person);
    }
  }
  @PostMapping("")
  public Order create(Order order) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    order.setPerson(personService.findByEmail(user.getUsername()));
    orderService.save(order);
    return order;
  }

  @DeleteMapping("/{id}")
  public Order delete(@PathVariable Integer id) {
    Order order = orderService.find(id);
    orderService.delete(order);
    return order;
  }

  @PutMapping("/{id}")
  public Order put(@PathVariable Integer id, Order order) {
    Order order1=orderService.find(id);
    order1.setOrderDate(order.getOrderDate());
    order1.setPerson(order.getPerson());
    order1.getOrderLines().clear();
    order1.getOrderLines().addAll(order.getOrderLines());
    orderService.save(order1);
    return order;
  }

}
