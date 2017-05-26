package edu.mum.coffee.controller;

import edu.mum.coffee.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sherxon on 5/23/17.
 */
@RestController
@RequestMapping(value = "/persons")
public class PersonController {

  final
  private PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }


}
