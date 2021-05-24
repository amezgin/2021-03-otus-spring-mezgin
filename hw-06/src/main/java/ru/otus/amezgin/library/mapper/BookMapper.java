package ru.otus.amezgin.library.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.amezgin.library.domain.Author;
import ru.otus.amezgin.library.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Author author = new Author(resultSet.getLong("id"), resultSet.getString("full_name"));
        return new Book(resultSet.getLong("id"), resultSet.getString("title"), author);
    }
}
