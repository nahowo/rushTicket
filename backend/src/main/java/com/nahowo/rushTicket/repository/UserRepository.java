package com.nahowo.rushTicket.repository;

import com.nahowo.rushTicket.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
