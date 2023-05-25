package Capstone.Tripplaner.ItemService.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Item {

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
    private List<MultipartFile> images;
}