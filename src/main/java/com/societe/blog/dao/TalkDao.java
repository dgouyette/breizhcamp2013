package com.societe.blog.dao;

import com.google.common.base.Optional;
import com.societe.blog.domain.Talk;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
@Transactional
public class TalkDao {


    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public Optional<Talk> findById(long id) {
        return Optional.fromNullable(em.find(Talk.class, id));
    }

    public void save(Collection<Talk> talks) {
        for (Talk t : talks) {
            em.persist(t);
        }
    }

    public void remove(Talk talk) {
        em.remove(em.merge(talk));
    }

    public Collection<Talk> findAll() {
        return em.createQuery("select t from Talk t").getResultList();
    }

}
