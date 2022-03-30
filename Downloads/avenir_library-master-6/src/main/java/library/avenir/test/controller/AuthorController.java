package library.avenir.test.controller;

import io.swagger.annotations.Api;
import library.avenir.test.dto.author.AuthorCreateDto;
import library.avenir.test.dto.author.AuthorDto;
import library.avenir.test.dto.book.BookIdsDto;
import library.avenir.test.entity.Author;
import library.avenir.test.filterrequest.author.AuthorFilterRequest;
import library.avenir.test.service.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(description = "Author api",tags = {"Author"})
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    private List<AuthorDto> findAll() {
        return authorService.findAll();
    }

    @PostMapping("/{id}")
    private void bindBooksWithAuthor(@PathVariable Long id,
                                     @RequestBody BookIdsDto dto) {
        authorService.bindBooksWithAuthor(id, dto);
    }

    @PostMapping("/search")
    private Page<AuthorDto> search(@RequestBody AuthorFilterRequest filterRequest) {
        return authorService.search(filterRequest);

    }
    @PostMapping("/CreateAuthor")
    private AuthorDto create(AuthorCreateDto authorCreateDto){
        return authorService.create(authorCreateDto);


    }
}
