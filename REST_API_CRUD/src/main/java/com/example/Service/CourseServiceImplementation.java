package com.example.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Model.Course;
import com.example.Repository.CourseRepository;

@Service
public class CourseServiceImplementation implements CourseService {

    @Autowired
    private CourseRepository repo;

    @Override
    public Course addCourse(Course course) {
        return repo.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        Course existing = repo.findById(id).orElse(null);

        if (existing != null) {
            existing.setTitle(course.getTitle());
            existing.setDuration(course.getDuration());
            existing.setFee(course.getFee());
            return repo.save(existing);
        }
        return null;
    }

    @Override
    public void deleteCourse(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Course getCourseById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Course> getAllCourses() {
        return repo.findAll();
    }

    @Override
    public List<Course> searchByTitle(String title) {
        return repo.findByTitleContainingIgnoreCase(title);
    }
}