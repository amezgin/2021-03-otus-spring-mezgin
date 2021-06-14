package ru.otus.amezgin.library.repository;

import ru.otus.amezgin.library.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Optional<Comment> getById(Long id);

    List<Comment> getAll();

    Comment save(Comment comment);

    public List<Comment> getCommentByBookId(Long bookId);

    void deleteById(Long id);
}
