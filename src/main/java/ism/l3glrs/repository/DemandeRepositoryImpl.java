package ism.l3glrs.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import ism.l3glrs.config.JpaUtil;
import ism.l3glrs.entity.DemandeRendezVous;

public class DemandeRepositoryImpl implements DemandeRepository {

    @Override
    public Long insert(DemandeRendezVous demande) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(demande);
            tx.commit();
            return demande.getId();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public DemandeRendezVous findById(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(DemandeRendezVous.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<DemandeRendezVous> selectAll() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("SELECT d FROM DemandeRendezVous d", DemandeRendezVous.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public DemandeRendezVous update(DemandeRendezVous demande) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            DemandeRendezVous merged = em.merge(demande);
            tx.commit();
            return merged;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            DemandeRendezVous d = em.find(DemandeRendezVous.class, id);
            if (d != null) em.remove(d);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
