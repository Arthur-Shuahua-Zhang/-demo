package com.example.courseservice.controller;

import lombok.Data;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class CourseController {


    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_student')")
    @GetMapping("/course")
    public List<Course> retrieveAllCourses(Authentication authentication) {
        System.out.println(authentication);

        List<Course> courses = new ArrayList<>(Arrays.asList(
                new Course("Java Basics", "Arthur", "Java is a robust, object-oriented programming language widely used for building platform-independent applications, ranging from mobile apps to enterprise systems.", "https://1000logos.net/wp-content/uploads/2020/09/Java-Logo.jpg"),
                new Course("Spring Boot Intro", "Ted", "Spring Boot is a Java-based framework that simplifies Spring application setup for rapid development. It offers auto-configuration, standalone-code, and production-ready features", "https://miro.medium.com/v2/resize:fit:900/0*t61rpXrvkpesX_8U.jpg"),
                new Course("React","Bill","React is a JavaScript library for building user interfaces, particularly single-page applications, by creating reusable UI components and efficiently updating views.","https://kinsta.com/wp-content/uploads/2022/06/what-is-react-js-feature-image.png"),
                new Course("Angular","Zack","Angular is a powerful, TypeScript-based open-source framework for building web applications, offering features like two-way data binding, modular architecture, and dependency injection.","https://www.syncfusion.com/blogs/wp-content/uploads/2022/02/Top-10-Features-of-Angular-13.png"),
                new Course("AWS","Musk","Amazon Web Services (AWS) is a comprehensive cloud computing platform offering a suite of services for computing, storage, databases, analytics, and more.","https://i.ytimg.com/vi/JIbIYCM48to/maxresdefault.jpg")
        ));
        return courses;
    }
}





@Data
class Course  {
    String title;
    String author;
    String description;
    String url;

    public Course(String title, String author, String description, String url) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.url = url;
    }
}
