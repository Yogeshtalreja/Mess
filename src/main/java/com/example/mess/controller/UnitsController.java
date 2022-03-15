package com.example.mess.controller;

import com.example.mess.exception.GeneralException;
import com.example.mess.model.Dashboard;
import com.example.mess.model.UnitOff;
import com.example.mess.service.UnitsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/units")
public class UnitsController {

    private final UnitsService unitsService;

    @PostMapping("/offUnits")
    public String addUnitsOff(@RequestBody UnitOff model) throws GeneralException {
        return unitsService.addUnitsOff(model);
    }

    @GetMapping("/totalUnitsOfMonth/{userId}")
    public Integer totalUnitsOfMonth(@PathVariable("userId") Integer userId) throws GeneralException {
        return unitsService.totalUnitsOfCurrentMonth(userId);
    }

    @GetMapping("/getDashboardData/{userId}")
    public Dashboard getDashboardData(@PathVariable("userId") Integer userId) throws GeneralException {
        return unitsService.findDashboardApis(userId);
    }
}
