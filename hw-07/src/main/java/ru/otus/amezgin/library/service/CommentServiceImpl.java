package ru.otus.amezgin.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.amezgin.library.domain.Comment;
import ru.otus.amezgin.library.repository.CommentJPARepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentJPARepository commentJPARepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<Comment> getById(Long id) {
        return commentJPARepository.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> getAll() {
        return commentJPARepository.getAll();
    }

    @Transactional
    @Override
    public Comment save(Comment comment) {
        return commentJPARepository.save(comment);
    }

    @Override
    public List<Comment> getCommentByBookId(Long bookId) {
        return commentJPARepository.getCommentByBookId(bookId);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        commentJPARepository.deleteById(id);
    }
}
