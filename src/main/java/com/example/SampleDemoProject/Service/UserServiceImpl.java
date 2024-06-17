package com.example.SampleDemoProject.Service;

import com.example.SampleDemoProject.Dtos.UserRequest;
import com.example.SampleDemoProject.Dtos.UserResponse;
import com.example.SampleDemoProject.Exceptions.InvalidUserRequestException;
import com.example.SampleDemoProject.Model.learner;
import com.example.SampleDemoProject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse fetchUser(int Id) throws InvalidUserRequestException {
        learner learner = this.userRepository.findById(Id).orElseThrow(() -> new InvalidUserRequestException("User Not Found"));
        UserResponse userResponse = new UserResponse();
        userResponse.setId(learner.getId());
        userResponse.setName(learner.getName());
        userResponse.setEmail(learner.getEmail());
        return userResponse;
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) throws InvalidUserRequestException {
        Optional<learner> userAlreadyExists = this.userRepository.findByNameEqualsAndEmail(userRequest.getName(), userRequest.getEmail());
        if (userAlreadyExists.isPresent()) {
            throw new InvalidUserRequestException("User Already Exists");
        }
        UserResponse response = new UserResponse();
        learner learner = new learner();
        learner.setName(userRequest.getName());
        learner.setEmail(userRequest.getEmail());
        learner user = this.userRepository.save(learner);
        response.setId(user.getId());
        response.setName(userRequest.getName());
        response.setEmail(userRequest.getEmail());
        return response;
    }

    @Override
    public void deleteUser(int Id) throws InvalidUserRequestException {
        try {
            Optional<learner> userAlreadyExists = this.userRepository.findById(Id);
            if (userAlreadyExists.isEmpty()) {
                throw new InvalidUserRequestException("User Not Found");
            }
            this.userRepository.deleteById(Id);
        }catch (Exception ex){
            throw new InvalidUserRequestException("User Not Found");
        }
    }
}
