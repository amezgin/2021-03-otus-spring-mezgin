package ru.otus.amezgin.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.amezgin.library.domain.Comment;
import ru.otus.amezgin.library.repository.CommentJPA;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentJPA commentJPA;

    @Override
    public Optional<Comment> getById(Long id) {
        return commentJPA.getById(id);
    }

    @Override
    public List<Comment> getAll() {
        return commentJPA.getAll();
    }

    @Transactional
    @Override
    public Comment save(Comment comment) {
        return commentJPA.save(comment);
    }

    @Override
    public List<Comment> getCommentByBookId(Long bookId) {
        return commentJPA.getCommentByBookId(bookId);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        commentJPA.deleteById(id);
    }
}
