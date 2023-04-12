package Capstone.Tripplaner;

import Capstone.Tripplaner.data.dto.PostDto;
import Capstone.Tripplaner.data.dto.UserDto;
import Capstone.Tripplaner.data.entity.PostEntity;
import Capstone.Tripplaner.data.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mapper {
    private static ModelMapper modelMapper = new ModelMapper();

    public static UserDto UserMapper(Optional<UserEntity> userEntity) {
        UserDto userDto = modelMapper.map(userEntity, UserDto.class);
        return userDto;
    }
    public static PostDto PostMapper(Optional<PostEntity> postEntity) {
        PostDto postDto = modelMapper.map(postEntity, PostDto.class);
        return postDto;
    }

}
