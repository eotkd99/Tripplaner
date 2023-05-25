package Capstone.Tripplaner.ItemService.data.dto;

import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Data
public class ItemOneImage {

    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    private Integer views;
    private Integer likes;

    @NotNull
    @Digits(integer = 10, fraction = 0, message = "정수 값이어야 합니다.")
    private Integer price;
    private String image;

    public ItemOneImage(String title, String content, Integer price, String image) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}