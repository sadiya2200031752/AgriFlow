package com.agriflow.agriflow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agriflow.agriflow.exception.FarmAreaExceededException;
import com.agriflow.agriflow.model.Field;
import com.agriflow.agriflow.model.Farm;
import com.agriflow.agriflow.repository.FieldRepository;

@Service
public class FieldService {

    private final FieldRepository fieldRepository;

    public FieldService(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    public Field saveField(Field field) {

        Farm farm = field.getFarm();

        double existingFieldArea =
                fieldRepository.getTotalAreaByFarmId(farm.getId());

        double totalArea = existingFieldArea + field.getArea();

        if (totalArea > farm.getArea()) {
            throw new FarmAreaExceededException(
                    "Field area exceeds the available farm area. "
                    + "Farm area: " + farm.getArea()
                    + " acres, already used: " + existingFieldArea
                    + " acres.");
        }

        return fieldRepository.save(field);
    }

    public List<Field> getAllFields() {
        return fieldRepository.findAll();
    }

    public Field getFieldById(Long id) {
        return fieldRepository.findById(id).orElse(null);
    }

    public Field updateField(Long id, Field field) {

        Field existingField =
                fieldRepository.findById(id).orElse(null);

        if (existingField != null) {

            existingField.setArea(field.getArea());
            existingField.setCropSeason(field.getCropSeason());
            existingField.setGrowthDuration(field.getGrowthDuration());
            existingField.setSoilType(field.getSoilType());
            existingField.setFarm(field.getFarm());
            existingField.setCrop(field.getCrop());

            return fieldRepository.save(existingField);
        }

        return null;
    }

    public void deleteField(Long id) {
        fieldRepository.deleteById(id);
    }
}