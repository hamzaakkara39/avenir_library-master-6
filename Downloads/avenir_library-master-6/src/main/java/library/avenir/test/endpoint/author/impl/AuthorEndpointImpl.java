package library.avenir.test.endpoint.author.impl;

import library.avenir.test.dto.author.AuthorDto;
import library.avenir.test.endpoint.author.AuthorEndpoint;
import library.avenir.test.entity.Author;
import library.avenir.test.entity.Book;
import library.avenir.test.mapper.AuthorMapper;
import library.avenir.test.service.AuthorService;
import library.avenir.test.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorEndpointImpl implements AuthorEndpoint {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;
    private final BookService bookService;

    public AuthorEndpointImpl(AuthorService authorService,
                              AuthorMapper authorMapper,
                              BookService bookService) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
        this.bookService = bookService;
    }

    @Override
    public List<AuthorDto> findAll() {
        List<AuthorDto> authors = authorService.findAll();
        return null;
    }

    @Override
    public AuthorDto findById(Long id) {
        Author author = authorService.getById(id);
        List<Book> booksByAuthor = bookService.getBooksByAuthor(author);
        return authorMapper.toAuthorDto(author, booksByAuthor);
    }
}
