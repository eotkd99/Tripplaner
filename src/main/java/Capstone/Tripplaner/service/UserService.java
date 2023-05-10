package Capstone.Tripplaner.service;

import Capstone.Tripplaner.data.dto.User;
import Capstone.Tripplaner.data.entity.UserEntity;
import Capstone.Tripplaner.data.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository UserRepository, ModelMapper modelMapper) {
        this.userRepository = UserRepository;
        this.modelMapper = modelMapper;
    }

    public User login(String id, String password) {
        UserEntity userEntity = userRepository.findById(id)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
        if(userEntity==null) return null;
        else return modelMapper.map(userEntity, User.class);
    }

    public void saveUser(User user) {
        userRepository.save(modelMapper.map(user, UserEntity.class));
    }

    public void updateUser(User user) {
        userRepository.save(modelMapper.map(user, UserEntity.class));
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

}