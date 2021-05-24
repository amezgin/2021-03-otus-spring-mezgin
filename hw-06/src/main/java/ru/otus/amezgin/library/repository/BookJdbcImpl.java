package ru.otus.amezgin.library.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.amezgin.library.repository.ext.BookGenreRelation;
import ru.otus.amezgin.library.domain.Book;
import ru.otus.amezgin.library.domain.Genre;
import ru.otus.amezgin.library.mapper.BookResultSetExtractor;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class BookJdbcImpl implements BookJdbc {

    private final NamedParameterJdbcOperations jdbcOperations;

    private final GenreJdbc genreJdbc;

    public BookJdbcImpl(NamedParameterJdbcOperations jdbcOperations, GenreJdbc genreJdbc) {
        this.jdbcOperations = jdbcOperations;
        this.genreJdbc = genreJdbc;
    }

    @Override
    public Optional<Book> getById(long id) {
        List<Genre> genres = genreJdbc.getAll();
        List<BookGenreRelation> relations = getAllRelations();

        Map<Long, Book> books =
                jdbcOperations.query("select b.id, b.title, a.id author_id, a.full_name " +
                                "from (book b left join author a on b.author_id = a.id) " +
                                "where b.id = :id", Map.of("id", id),
                        new BookResultSetExtractor());

        mergeBooksInfo(books, genres, relations);
        return Optional.ofNullable(Objects.requireNonNull(books).get(id));
    }

    @Override
    public List<Book> getAll() {
        List<Genre> genres = genreJdbc.getAll();
        List<BookGenreRelation> relations = getAllRelations();
        Map<Long, Book> books =
                jdbcOperations.query("select b.id, b.title, a.id author_id, a.full_name " +
                                "from (book b left join author a on b.author_id = a.id) ",
                        new BookResultSetExtractor());

        mergeBooksInfo(books, genres, relations);
        return new ArrayList<>(Objects.requireNonNull(books).values());
    }

    @Override
    public Book save(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", book.getTitle());
        params.addValue("author_id", book.getAuthor().getId());
        KeyHolder key = new GeneratedKeyHolder();
        jdbcOperations.update("insert into book (title, author_id) values (:title, :author_id)", params, key);

        for (Genre genre : book.getGenres()) {
            jdbcOperations.update("insert into book_genre (book_id, genre_id) values (:book_id, :genre_id)",
                    Map.of("book_id", key.getKey().longValue(), "genre_id", genre.getId()));
        }
        book.setId(key.getKey().longValue());
        return book;
    }

    @Override
    public void update(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", book.getId());
        params.addValue("title", book.getTitle());
        if (book.getAuthor().getId() != 0) {
            params.addValue("author_id", book.getAuthor().getId());
            jdbcOperations.update("update book set title = :title, author_id = :author_id where id = :id", params);
        } else {
            jdbcOperations.update("update book set title = :title where id = :id", params);
        }

        if (!book.getGenres().isEmpty()) {
            jdbcOperations.update("delete from book_genre where book_id = :id", Map.of("id", book.getId()));
            for (Genre genre : book.getGenres()) {
                jdbcOperations.update("insert into book_genre (book_id, genre_id) values (:book_id, :genre_id)",
                        Map.of("book_id", book.getId(), "genre_id", genre.getId()));
            }
        }
    }

    @Override
    public void deleteById(long id) {
        jdbcOperations.update("delete from book_genre where book_id = :id", Map.of("id", id));
        jdbcOperations.update("delete from book where id = :id", Map.of("id", id));
    }

    private List<BookGenreRelation> getAllRelations() {
        return jdbcOperations.query("select book_id, genre_id from book_genre sc order by book_id, genre_id",
                (rs, i) -> new BookGenreRelation(rs.getLong(1), rs.getLong(2)));
    }

    private void mergeBooksInfo(Map<Long, Book> books, List<Genre> genres,
                                List<BookGenreRelation> relations) {
        Map<Long, Genre> genreMap = genres.stream().collect(Collectors.toMap(Genre::getId, Function.identity()));
        relations.forEach(r -> {
            if (books.containsKey(r.getBook_id()) && genreMap.containsKey(r.getGenre_id())) {
                books.get(r.getBook_id()).getGenres().add(genreMap.get(r.getGenre_id()));
            }
        });
    }
}
