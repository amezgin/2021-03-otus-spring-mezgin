package ru.otus.amezgin.library.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.amezgin.library.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GenreRepositoryImpl implements GenreRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Genre> getById(Long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public Optional<Genre> getByName(String name) {
        TypedQuery<Genre> query = em.createQuery(
                "select g from Genre g where g.genreName = :name", Genre.class);
        return query.setParameter("name", name).getResultList().stream().findFirst();
    }

    @Override
    public List<Genre> getAll() {
        return em.createQuery("select g from Genre g", Genre.class).getResultList();
    }

    @Override
    public Genre save(Genre genre) {
        em.persist(genre);
        return genre;
    }

    @Override
    public void deleteById(Long id) {
        Query q = em.createQuery("delete from Genre g where g.id = :id");
        q.setParameter("id", id);
        q.executeUpdate();
    }
}
