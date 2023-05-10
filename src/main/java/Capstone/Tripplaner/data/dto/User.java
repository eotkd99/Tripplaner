package Capstone.Tripplaner.data.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @NotEmpty
    private String id;
    @NotEmpty
    private String password;
}