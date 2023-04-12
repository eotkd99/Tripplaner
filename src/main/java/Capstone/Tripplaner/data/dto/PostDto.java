package Capstone.Tripplaner.data.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {
    private String id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime created;
    private Integer likes;
    private Integer views;
}