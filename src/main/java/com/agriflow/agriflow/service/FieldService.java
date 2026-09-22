package com.agriflow.agriflow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agriflow.agriflow.model.Field;
import com.agriflow.agriflow.repository.FieldRepository;

@Service
public class FieldService {

    private final FieldRepository fieldRepository;

    public FieldService(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    public Field saveField(Field field) {
        return fieldRepository.save(field);
    }

    public List<Field> getAllFields() {
        return fieldRepository.findAll();
    }

    public Field getFieldById(Long id) {
        return fieldRepository.findById(id).orElse(null);
    }

    public Field updateField(Long id, Field field) {

        Field existingField = fieldRepository.findById(id).orElse(null);

        if (existingField != null) {
            existingField.setFieldName(field.getFieldName());
            existingField.setArea(field.getArea());
            existingField.setCropName(field.getCropName());
            existingField.setFarm(field.getFarm());

            return fieldRepository.save(existingField);
        }

        return null;
    }

    public void deleteField(Long id) {
        fieldRepository.deleteById(id);
    }
}