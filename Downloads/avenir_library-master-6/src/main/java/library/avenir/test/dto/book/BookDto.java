package library.avenir.test.dto.book;

import library.avenir.test.dto.author.AuthorDto;
import library.avenir.test.enums.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {
    private Long id;
    private String name;
    private Category category;
    private AuthorDto author;
    private Integer quantity;
}
