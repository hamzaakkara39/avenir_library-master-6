package library.avenir.test.endpoint.author;

import library.avenir.test.dto.author.AuthorDto;

import java.util.List;

public interface AuthorEndpoint {
    List<AuthorDto> findAll();
    AuthorDto findById(Long id);
}
