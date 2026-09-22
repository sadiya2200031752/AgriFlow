package com.agriflow.agriflow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.agriflow.agriflow.model.WaterAnalysis;
import com.agriflow.agriflow.service.WaterAnalysisService;

@RestController
public class WaterAnalysisController {

    private final WaterAnalysisService waterAnalysisService;

    public WaterAnalysisController(WaterAnalysisService waterAnalysisService) {
        this.waterAnalysisService = waterAnalysisService;
    }

    @GetMapping("/water-analysis/{fieldId}")
    public WaterAnalysis analyzeField(@PathVariable Long fieldId) {

        return waterAnalysisService.analyzeField(fieldId);
    }
}