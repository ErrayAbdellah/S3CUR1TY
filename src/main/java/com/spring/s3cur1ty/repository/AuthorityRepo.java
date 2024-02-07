package com.spring.s3cur1ty.repository;


import com.spring.s3cur1ty.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepo extends JpaRepository<Authority,Long> {

}
