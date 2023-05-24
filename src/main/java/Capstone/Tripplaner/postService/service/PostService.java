package Capstone.Tripplaner.postService.service;

import Capstone.Tripplaner.postService.data.Post;
import Capstone.Tripplaner.postService.data.PostEntity;
import Capstone.Tripplaner.postService.data.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostService(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    public List<Post> getAllPosts() {
        return mapEntitiesToDtos(postRepository.findAll());
    }

    public Post getPostById(Integer id) {
        return postRepository.getPostById(id).map(e -> modelMapper.map(e, Post.class)).orElse(null);
    }

    public Integer savePost(Post post) {
        PostEntity save = postRepository.save(modelMapper.map(post, PostEntity.class));
        return save.getId();
    }

    public void updatePost(Integer id, Post post) {
        PostEntity entity = modelMapper.map(post, PostEntity.class);
        entity.setId(id);
        postRepository.save(entity);
    }

    public void deletePost(String id) {
        postRepository.deleteById(id);
    }

    private List<Post> mapEntitiesToDtos(List<PostEntity> entities) {
        return entities.stream()
                .map(entity -> modelMapper.map(entity, Post.class))
                .collect(Collectors.toList());
    }
}