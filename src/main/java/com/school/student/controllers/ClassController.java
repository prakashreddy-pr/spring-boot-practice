package com.school.student.controllers;

import com.school.student.Services.ClassService;
import com.school.student.entities.ClassEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClassController {

    @Autowired
    ClassService classService;

    @PostMapping(value = "/class/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveClass(@Validated @RequestBody ClassEntity classEntity){
      return ResponseEntity.status(201).body(classService.saveClass(classEntity));
    }

    @GetMapping(value = "/class/{className}/details")
    public ResponseEntity<Object> getClass(@PathVariable String className){
        return ResponseEntity.status(200).body(classService.getClass(className));

    }

    @DeleteMapping(value="/class/delete")
    public ResponseEntity<String> deleteClass(@Validated @RequestBody ClassEntity classEntity){
        return ResponseEntity.status(200).body(classService.deleteClass(classEntity.getClassName()));
    }
}
