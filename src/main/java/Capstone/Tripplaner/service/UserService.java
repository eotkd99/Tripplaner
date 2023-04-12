package Capstone.Tripplaner.service;

import Capstone.Tripplaner.Mapper;
import Capstone.Tripplaner.data.dto.UserDto;
import Capstone.Tripplaner.data.entity.PostEntity;
import Capstone.Tripplaner.data.entity.UserEntity;
import Capstone.Tripplaner.data.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(UserDto userDto){

        UserDto dto = Mapper.UserMapper(userRepository.findById(userDto.getId()));

        if (dto.getId() != null && dto.getPassword().equals(dto.getPassword())) {
            return true;
        }
        return false;
    }
    public boolean register(UserDto userDto){

        UserDto dto = Mapper.UserMapper(userRepository.findById(userDto.getId()));

        if (dto.getId() == null) {
        }
        return false;
    }
}
