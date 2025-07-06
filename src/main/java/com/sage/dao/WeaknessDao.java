package com.sage.dao;

import com.sage.model.weakness.WeaknessModel;

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
        try {
            tx.begin();
            em.persist(entity);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive())
                tx.rollback();

            LOGGER.severe(
                    "[WeaknessDao] Error while trying to persist entity: " + entity.toString() + "\nRolling back: " + e.getMessage());
            return false;
        }
    }

    @Override
    public WeaknessModel read(Integer key) {
        try {
            tx.begin();
            WeaknessModel weaknessModel = em.find(WeaknessModel.class, key);
            tx.commit();
            return weaknessModel;
        } catch (Exception e) {
            LOGGER.severe("[WeaknessDao] Error while trying to fetch entity with (id)=" + key);
            return null;
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
        String query = "SELECT * FROM weaknesses;";

        List<Object[]> results = em.createQuery(query).getResultList();
        List<WeaknessModel> weaknessModels = new ArrayList<>();

        for (Object[] row : results) {
            WeaknessModel weaknessModel = new WeaknessModel(
                    ((Number) row[0]).intValue(),
                    ((Number) row[1]).intValue(),
                    (String) row[2],
                    (String) row[3],
                    (String) row[4],
                    (String) row[5]
            );
            weaknessModels.add(weaknessModel);
        }

        return weaknessModels;
    }

    public List<WeaknessModel> fetchAllByVulnerabilityId(Integer vulnerabilityId) {
        String query = "SELECT * FROM weaknesses WHERE vulnerability_id = " + vulnerabilityId + ";";

        List<Object[]> results = em.createQuery(query).getResultList();
        List<WeaknessModel> weaknessModels = new ArrayList<>();

        for (Object[] row : results) {
            WeaknessModel weaknessModel = new WeaknessModel(
                    ((Number) row[0]).intValue(),
                    ((Number) row[1]).intValue(),
                    (String) row[2],
                    (String) row[3],
                    (String) row[4],
                    (String) row[5]
            );
            weaknessModels.add(weaknessModel);
        }
        return weaknessModels;
    }
}
