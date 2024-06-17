package com.example.SampleDemoProject.Repository;

import com.example.SampleDemoProject.Model.learner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<learner, Integer> {
    Optional<learner> findById(int id);
    Optional<learner> findByNameEqualsAndEmail(String name, String email);
}
