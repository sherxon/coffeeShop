package edu.mum.coffee.service;

import edu.mum.coffee.domain.Role;
import edu.mum.coffee.domain.User;
import edu.mum.coffee.repository.RoleRepository;
import edu.mum.coffee.repository.UserRepository;
import java.util.Arrays;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by sherxon on 5/24/17.
 */
@Service("userService")
public class UserService {

  @Qualifier("userRepository")
  @Autowired
  private UserRepository userRepository;


  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  public User findUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }
  public void saveUser(User user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    user.setActive(1);
    userRepository.save(user);
  }

}
