package library.avenir.test.service;

import library.avenir.test.dto.author.AuthorCreateDto;
import library.avenir.test.dto.author.AuthorDto;
import library.avenir.test.dto.book.BookIdsDto;
import library.avenir.test.entity.Author;
import library.avenir.test.filterrequest.author.AuthorFilterRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AuthorService {
    void bindBooksWithAuthor(Long id, BookIdsDto dto);

    List<AuthorDto> findAll();

    Page<AuthorDto> search(AuthorFilterRequest filterRequest);

    Author getById(Long id);

    AuthorDto create(AuthorCreateDto authorCreateDto);
}
