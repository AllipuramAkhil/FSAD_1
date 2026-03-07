package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.Model.Course;
import com.example.Service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService service;

    // CREATE
    @PostMapping
    public ResponseEntity<?> addCourse(@RequestBody Course course) {
        Course saved = service.addCourse(course);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(service.getAllCourses());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCourse(@PathVariable Long id) {
        Course course = service.getCourseById(id);

        if (course != null)
            return ResponseEntity.ok(course);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Course not found with ID: " + id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id,
                                          @RequestBody Course course) {

        Course updated = service.updateCourse(id, course);

        if (updated != null)
            return ResponseEntity.ok(updated);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Course not found for update");
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {

        Course course = service.getCourseById(id);

        if (course == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Course not found for deletion");

        service.deleteCourse(id);
        return ResponseEntity.ok("Course deleted successfully");
    }

    // SEARCH
    @GetMapping("/search/{title}")
    public ResponseEntity<?> search(@PathVariable String title) {

        List<Course> list = service.searchByTitle(title);

        if (list.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No courses found with title: " + title);

        return ResponseEntity.ok(list);
    }
}