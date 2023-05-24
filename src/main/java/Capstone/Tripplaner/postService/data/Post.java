package Capstone.Tripplaner.postService.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Integer id;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    private LocalDateTime created;
    private String userID;
    private Integer likes;
    private Integer views;
}