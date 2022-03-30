package library.avenir.test.dto.author;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class AuthorCreateDto {

    private String  firstName;
    private String  lastName;
    private LocalDate birthDate;
    private LocalDate deathDate;
    private String biography;


}
