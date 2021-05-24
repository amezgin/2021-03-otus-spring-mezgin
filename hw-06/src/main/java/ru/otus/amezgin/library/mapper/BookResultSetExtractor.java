package ru.otus.amezgin.library.mapper;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.otus.amezgin.library.domain.Author;
import ru.otus.amezgin.library.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BookResultSetExtractor implements
        ResultSetExtractor<Map<Long, Book>> {
    @Override
    public Map<Long, Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long, Book> books = new HashMap<>();
        while (rs.next()) {
            long id = rs.getLong("id");
            Book book = books.get(id);
            if (book == null) {
                book = new Book(id, rs.getString("title"),
                        new Author(rs.getLong("author_id"), rs.getString("full_name")));
                books.put(book.getId(), book);
            }
        }
        return books;
    }
}
