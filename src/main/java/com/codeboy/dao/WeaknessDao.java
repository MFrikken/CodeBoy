package com.codeboy.dao;

import com.codeboy.model.weakness.WeaknessModel;
import com.codeboy.utility.JPAManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.List;

public class WeaknessDao extends Dao<WeaknessModel, Integer> {

    private static WeaknessDao instance;

    private WeaknessDao() {
        super();
    }

    public static WeaknessDao getInstance() {
        if (instance == null)
            instance = new WeaknessDao();
        return instance;
    }

    @Override
    public boolean create(WeaknessModel entity) {
        EntityManager entityManager = JPAManager.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive())
                transaction.rollback();

            LOGGER.severe(
                    "[WeaknessDao] Error while trying to persist entity: " + entity.toString() + "\nRolling back: " + e.getMessage());
            return false;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public WeaknessModel read(Integer key) {
        EntityManager entityManager = JPAManager.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            WeaknessModel weaknessModel = entityManager.find(WeaknessModel.class, key);
            transaction.commit();
            return weaknessModel;
        } catch (Exception e) {
            LOGGER.severe("[WeaknessDao] Error while trying to fetch entity with (id)=" + key);
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    WeaknessModel update(Integer key, WeaknessModel newEntity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    boolean delete(Integer key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    public List<WeaknessModel> readAll() {
        EntityManager entityManager = JPAManager.getEntityManager();

        String query = "SELECT w FROM WeaknessModel w";

        try {
            List<WeaknessModel> weaknessModels = entityManager.createQuery(query, WeaknessModel.class).getResultList();
            return weaknessModels;
        } catch (Exception e) {
            LOGGER.warning("An error occured while trying to fetch all weaknesses: " + e.getMessage());
            return new ArrayList<>();
        } finally {
            entityManager.close();
        }

    }

    public List<WeaknessModel> fetchAllByVulnerabilityId(Integer vulnerabilityId) {
        EntityManager entityManager = JPAManager.getEntityManager();

        String query = "SELECT w FROM WeaknessModel w WHERE w.vulnerability.id = :vulnerabilityId";

        try {
            List<WeaknessModel> weaknessModels = entityManager.createQuery(query, WeaknessModel.class)
                    .setParameter("vulnerabilityId", vulnerabilityId)
                    .getResultList();
            return weaknessModels;
        } catch (Exception e) {
            LOGGER.warning("An error occured while trying to fetch weaknesses: " + e.getMessage());
            return new ArrayList<>();
        } finally {
            entityManager.close();
        }
    }
}
