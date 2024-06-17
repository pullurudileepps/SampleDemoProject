package com.example.SampleDemoProject.Model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "learners")
public class learner extends BaseModel {
    private String name;
    private String email;
}
