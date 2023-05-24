package Capstone.Tripplaner.data.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "posts")
    public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "userID")
    private String userID;

    @Column(name = "likes")
    private Integer likes;

    @Column(name = "views")
    private Integer views;

    @PreUpdate
    @PrePersist
    public void prePersist() {
        this.created=LocalDateTime.now();
        this.likes=0;
        this.views=0;
    }
}