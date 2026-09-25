package com.berkay.dtopractice.repository;

import com.berkay.dtopractice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query(value = """
            SELECT *
            FROM student
            WHERE department = :department
            """, nativeQuery = true)
    List<Student> findByDepartment(@Param("department") String department);


    @Query("""
        SELECT s
        FROM Student s
        WHERE s.department = :department
        """)
    List<Student> findByDepartmentJpql(@Param("department") String department);
}
