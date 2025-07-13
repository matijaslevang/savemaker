package com.example.savemaker.transactions.controllers;

import com.example.savemaker.transactions.dtos.CategoryDTO;
import com.example.savemaker.transactions.models.Category;
import com.example.savemaker.transactions.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<Category> categories = categoryService.getAll();
        List<CategoryDTO> dtos = categories.stream().map(CategoryDTO::new).toList();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
