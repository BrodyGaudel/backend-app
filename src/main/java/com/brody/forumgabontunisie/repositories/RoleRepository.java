package com.brody.forumgabontunisie.repositories;

import com.brody.forumgabontunisie.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
