package edu.mum.coffee.service;

import edu.mum.coffee.domain.Product;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.repository.PersonRepository;

@Service
@Transactional
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public Person savePerson(Person person) {
		return personRepository.save(person);
	}

	public Person findByEmail(String email) {
		return personRepository.findByEmail(email);
	}


	public Person findById(Long id) {
		return personRepository.findOne(id);
	}

	public void removePerson(Person person) {
		personRepository.delete(person);
	}

	public List<Person> findAll() {
		return 	personRepository.findAll();
	}

	public Person find(Integer id) {
		return 	personRepository.findOne(id.longValue());
	}

	public void delete(Person person) {
		personRepository.delete(person);
	}

	public void save(Person person) {
		personRepository.save(person);
	}
}
