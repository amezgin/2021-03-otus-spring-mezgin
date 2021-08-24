package ru.otus.amezgin.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.amezgin.library.domain.Comment;
import ru.otus.amezgin.library.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<Comment> getById(Long id) {
        return commentRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Transactional
    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> getCommentByBookId(Long bookId) {
        return commentRepository.getCommentByBookId(bookId);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}
