// package com.example.demo;
// import org.junit.jupiter.api.*;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// import com.example.demo.model.Student;
// import com.example.demo.repository.StudentRepository;

// // AssertJ for fluent assertions
// import static org.assertj.core.api.Assertions.assertThat;
// import java.util.List;
// // Indicates this is a Spring Boot test that loads the full application context
// @SpringBootTest
// // Specifies the order in which test methods will be executed
// @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
// class ControllerTest {
// // Injects the StudentRepository bean from the Spring context
// @Autowired
// private StudentRepository studentRepository;
// // First test: verifies that a student can be saved to the repository
// @Test
// @Order(1) // Ensures this test runs first
// void shouldSaveStudent() {
// // Create a new student object  
// Student student = new Student();
// student.setName("Charlie");
// student.setAddress("Algeria");
// // Save the student to the H2 in-memory database
// studentRepository.save(student);
// // Assert that the repository now contains exactly one record
// assertThat(studentRepository.count()).isEqualTo(1);
// }
// // Second test: verifies that all students can be retrieved correctly
// @Test
// @Order(2) // Ensures this test runs after shouldSaveStudent()
// void shouldFindAllStudents() {
// // Fetch all students from the repository
// List<Student> students = studentRepository.findAll();
// // Assert that there is exactly one student in the list
// assertThat(students).hasSize(1);

// // Assert that the student's name is "Charlie"
// assertThat(students.get(0).getName()).isEqualTo("Charlie");
// }
// }

package com.example.demo;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ControllerTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @Order(1)
    void shouldSaveStudent() {
        studentRepository.deleteAll(); // important pour isolation
        Student student = new Student("Charlie","Algeria");
        studentRepository.save(student);
        assertThat(studentRepository.count()).isEqualTo(1);
    }

    @Test
    @Order(2)
    void shouldFindAllStudents() {
        List<Student> students = studentRepository.findAll();
        assertThat(students).hasSize(1);
        assertThat(students.get(0).getName()).isEqualTo("Charlie");
    }
}
