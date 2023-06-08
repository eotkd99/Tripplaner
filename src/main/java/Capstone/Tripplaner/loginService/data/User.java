package Capstone.Tripplaner.loginService.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;
    @NotBlank(message="아이디는 공백일 수 없습니다.")
    private String username;
    @NotBlank(message="비밀번호는 공백일 수 없습니다.")
    private String password;

    private String role;
}