package edu.mum.coffee.api;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.service.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController(value = "RestPersonController")
@RequestMapping(value = "/api/persons")
public class PersonController {

  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping({"", "/"})
  public List<Person> get() {
    return personService.findAll();
  }

  @PostMapping("")
  public Person create(Person person) {
    personService.savePerson(person);
    return person;
  }

  @DeleteMapping("/{id}")
  public Person delete(@PathVariable Integer id) {
    Person person=personService.find(id);
    personService.delete(person);
    return person;
  }

  @PutMapping("/{id}")
  public Person put(@PathVariable Integer id, Person person) {
   Person person1=personService.find(id);
   person1.setPhone(person.getPhone());
   person1.setLastName(person.getLastName());
   person1.setFirstName(person.getFirstName());
   person1.setAddress(person.getAddress());
   person1.setEnable(person.isEnable());
    return person;
  }

}
