package Capstone.Tripplaner.ItemService.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
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
    private List<MultipartFile> images;
}