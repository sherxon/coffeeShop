package edu.mum.coffee.repository;

import edu.mum.coffee.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sherxon on 5/24/17.
 */
@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {
  Role findByRole(String role);
}
