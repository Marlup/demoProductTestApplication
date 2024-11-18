package com.marlo.demoProductTest.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository<T> extends JpaRepository<T, Long> {
}
