package com.agriflow.agriflow.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.agriflow.agriflow.model.Field;
import com.agriflow.agriflow.service.FieldService;

@RestController
public class FieldController {

    private final FieldService fieldService;

    public FieldController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @PostMapping("/fields")
    public Field saveField(@RequestBody Field field) {
        return fieldService.saveField(field);
    }

    @GetMapping("/fields")
    public List<Field> getAllFields() {
        return fieldService.getAllFields();
    }

    @GetMapping("/fields/{id}")
    public Field getFieldById(@PathVariable Long id) {
        return fieldService.getFieldById(id);
    }

    @PutMapping("/fields/{id}")
    public Field updateField(@PathVariable Long id, @RequestBody Field field) {
        return fieldService.updateField(id, field);
    }

    @DeleteMapping("/fields/{id}")
    public String deleteField(@PathVariable Long id) {
        fieldService.deleteField(id);
        return "Field deleted successfully";
    }
}