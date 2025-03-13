package com.sage.repository;

import java.util.List;

import com.sage.dao.WeaknessDao;
import com.sage.model.weakness.WeaknessModel;

public class WeaknessRepository implements Repository<WeaknessModel, Integer> {
    private final WeaknessDao weaknessDao;

    public WeaknessRepository() {
        weaknessDao = new WeaknessDao();
    }

    @Override
    public boolean deleteAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
    }

    @Override
    public boolean save(WeaknessModel entity) {
        return weaknessDao.create(entity);
    }

    @Override
    public WeaknessModel getByID(Integer key) {
        return weaknessDao.receive(key);
    }

    @Override
    public WeaknessModel update(Integer key, WeaknessModel newEntity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean delete(Integer key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<WeaknessModel> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }
    
}
