package com.codeboy.repository;

import java.util.List;

import com.codeboy.dao.WeaknessDao;
import com.codeboy.model.weakness.WeaknessModel;

public class WeaknessRepository implements Repository<WeaknessModel, Integer> {
    private final WeaknessDao weaknessDao;

    public WeaknessRepository() {
        weaknessDao = WeaknessDao.getInstance();
    }

    public List<WeaknessModel> getAllByVulnerabilityId(Integer vulnerabilityId) {
        return this.weaknessDao.fetchAllByVulnerabilityId(vulnerabilityId);
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
    public void saveAll(List<WeaknessModel> entities) {
        entities.forEach(this::save);
    }

    @Override
    public WeaknessModel getByID(Integer key) {
        return weaknessDao.read(key);
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
        return weaknessDao.readAll();
    }

}
