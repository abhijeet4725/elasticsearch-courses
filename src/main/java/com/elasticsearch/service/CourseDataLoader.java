package com.elasticsearch.service;

import com.elasticsearch.document.CourseDocument;
import com.elasticsearch.repository.CourseRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class CourseDataLoader {

    private final ObjectMapper objectMapper;
    private final CourseRepository courseRepository;

    @Autowired
    public CourseDataLoader(ObjectMapper objectMapper, CourseRepository courseRepository) {
        this.objectMapper = objectMapper;
        this.courseRepository = courseRepository;
    }

    @PostConstruct
    public void loadData() {
        try {
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            InputStream inputStream = new ClassPathResource("sample-courses.json").getInputStream();
            List<CourseDocument> courses = objectMapper.readValue(
                    inputStream, new TypeReference<List<CourseDocument>>() {}
            );

            courseRepository.saveAll(courses);
            System.out.println("Courses indexed successfully into Elasticsearch");
        } catch (Exception e) {
            System.err.println("Failed to load sample course data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
