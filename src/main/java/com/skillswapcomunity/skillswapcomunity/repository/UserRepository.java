package com.skillswapcomunity.skillswapcomunity.repository;

import com.skillswapcomunity.skillswapcomunity.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByUsername(String username);
}
