package com.elasticsearch.repository;

import com.elasticsearch.document.CourseDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CourseRepository extends ElasticsearchRepository<CourseDocument, String> {

}
