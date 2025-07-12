package com.codeboy.controller;

import com.codeboy.model.weakness.WeaknessDto;
import com.codeboy.service.WeaknessService;

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
