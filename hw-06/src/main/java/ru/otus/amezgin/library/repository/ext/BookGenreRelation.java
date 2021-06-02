package ru.otus.amezgin.library.repository.ext;

import lombok.Data;

@Data
public class BookGenreRelation {

    private final long book_id;

    private final long genre_id;
}
