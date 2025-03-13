package com.sage.controller;

import com.sage.model.weakness.WeaknessDto;
import com.sage.service.WeaknessService;

public class WeaknessController {
    private static WeaknessService weaknessService;
    private static WeaknessController instance;


    private WeaknessController() {
         weaknessService = new WeaknessService();
    }

    public static WeaknessController getInstance() {
        if (instance == null)
            instance = new WeaknessController();
        return instance;
    }
    
    public WeaknessDto fetchById(String id) {
        return weaknessService.getById(id);
    }

    public boolean save(WeaknessDto weaknessDto) {
        return weaknessService.save(weaknessDto);
    }
}
