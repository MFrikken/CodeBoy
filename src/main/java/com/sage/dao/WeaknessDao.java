package com.sage.dao;

import com.sage.model.weakness.WeaknessModel;

public class WeaknessDao extends Dao<WeaknessModel, Integer> {

    public WeaknessDao() {
        super();
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
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public WeaknessModel read(Integer key) {
        try {
            return em.find(WeaknessModel.class, key);
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

}
