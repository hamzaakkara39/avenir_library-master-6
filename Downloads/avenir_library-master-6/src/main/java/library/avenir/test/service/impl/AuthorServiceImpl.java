package library.avenir.test.service.impl;

import com.querydsl.core.BooleanBuilder;
import library.avenir.test.dto.author.AuthorCreateDto;
import library.avenir.test.dto.author.AuthorDto;
import library.avenir.test.dto.book.BookIdsDto;
import library.avenir.test.entity.Author;
import library.avenir.test.entity.Book;
import library.avenir.test.entity.QAuthor;
import library.avenir.test.filterrequest.author.AuthorFilterRequest;
import library.avenir.test.mapper.AuthorMapper;
import library.avenir.test.repository.AuthorRepository;
import library.avenir.test.service.AuthorService;
import library.avenir.test.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookService bookService;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository,
                             BookService bookService,
                             AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.bookService = bookService;
        this.authorMapper = authorMapper;
    }

    @Override
    public void bindBooksWithAuthor(Long id, BookIdsDto dto) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no Author with id " + id));
        List<Book> books = bookService.findAllById(dto.getIds());
        for (Book book : books) {
            bookService.updateAuthor(book, author);
        }
    }

    @Override
    public List<AuthorDto> findAll() {
        List<Author> authors = authorRepository.findAll();
        List<AuthorDto> dtos = new ArrayList<>();
        for (Author author : authors) {
            List<Book> booksByAuthor = bookService.getBooksByAuthor(author);
            AuthorDto authorDto = authorMapper.toAuthorDto(author, booksByAuthor);
            dtos.add(authorDto);
        }
        return dtos;
    }

    @Override
    public Page<AuthorDto> search(AuthorFilterRequest filterRequest) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (filterRequest.getSearchRequest().getSearchString() != null) {
            predicate.andAnyOf(QAuthor.author.firstName.containsIgnoreCase(
                    filterRequest.getSearchRequest().getSearchString()),
                    QAuthor.author.lastName.containsIgnoreCase(
                            filterRequest.getSearchRequest().getSearchString())
            );
        }

        if (filterRequest.getSearchRequest().getBirthDateMin() != null) {
            predicate.and(QAuthor.author.birthDate.after(
                    filterRequest.getSearchRequest().getBirthDateMin()
            ));
        }

        if (filterRequest.getSearchRequest().getBirthDateMax() != null) {
            predicate.and(QAuthor.author.birthDate.before(
                    filterRequest.getSearchRequest().getBirthDateMax()
            ));
        }

        Integer size = filterRequest.getPageRequest().getSize();
        Integer pageNumber = filterRequest.getPageRequest().getPageNumber();
        PageRequest page = PageRequest.of(pageNumber, size);

        return authorRepository.findAll(predicate, page)
                .map(x -> authorMapper.toAuthorDto(x, bookService.getBooksByAuthor(x)));
    }

    @Override
    public Author getById(Long id) {
        return authorRepository.getOne(id);
    }

    @Override
    public AuthorDto create(AuthorCreateDto authorCreateDto) {
        return authorRepository.create;
    }
}