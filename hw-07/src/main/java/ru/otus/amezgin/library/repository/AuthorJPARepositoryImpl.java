package ru.otus.amezgin.library.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.amezgin.library.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorJPARepositoryImpl implements AuthorJPARepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Author> getById(Long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public Optional<Author> getByName(String name) {
        TypedQuery<Author> query = em.createQuery(
                "select a from Author a where a.fullName = :name", Author.class);
        return query.setParameter("name", name).getResultList().stream().findFirst();
    }

    @Override
    public List<Author> getAll() {
        return em.createQuery("select a from Author a", Author.class).getResultList();
    }

    @Override
    public Author save(Author author) {
        em.persist(author);
        return author;
    }

    @Override
    public void deleteById(Long id) {
        Optional.ofNullable(em.find(Author.class, id)).ifPresent(em::remove);
    }
}
