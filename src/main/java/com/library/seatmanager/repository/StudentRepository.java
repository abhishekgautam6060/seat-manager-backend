package com.library.seatmanager.repository;

import com.library.seatmanager.entity.Student;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student , Long> {

    Optional<Student> findBySeat_SeatNumberAndActiveTrue(int seatNumber);
    Optional<Student> findBySeatNumberAndActiveTrue(int seatNumber);


    // ✅ Active students of a library
    List<Student> findBySeat_Library_IdAndActiveTrue(Long libraryId);

    @Query("SELECT COALESCE(SUM(s.amountPaid), 0) FROM Student s WHERE s.active = true")
    Integer sumTotalCollection();

    @Query("""
            SELECT COALESCE(SUM(s.amountPaid), 0)
            FROM Student s
            WHERE s.library.id = :libraryId
              AND YEAR(s.bookingDate) = :year
              AND MONTH(s.bookingDate) = :month
            """)
    int sumMonthlyRevenue(
            @Param("libraryId") Long libraryId,
            @Param("year") int year,
            @Param("month") int month
    );

    long countByActiveTrue();
    List<Student> findByActiveTrue();


    List<Student> findByNameContainingIgnoreCaseAndActiveTrue(String name);

    List<Student> findByPhoneContainingAndActiveTrue(String phone);

    List<Student> findByActiveTrueAndEndDateBetween(
            LocalDateTime start,
            LocalDateTime end
    );

    List<Student> findByActiveTrueAndExpiryDateBetween(
            LocalDate start,
            LocalDate end
    );

    List<Student> findByActiveTrueAndExpiryDateBefore(
            LocalDate today
    );

    @Query("SELECT COALESCE(SUM(s.amountPaid),0) FROM Student s WHERE s.active = true")
    int sumAmountPaidByActive();

    @Query("SELECT COALESCE(SUM(s.amount),0) FROM Student s WHERE s.active = true")
    int sumMonthlyAmountByActive();


    long countByStudentTypeAndActiveTrue(Student.StudentType studentType);

    List<Student> findByStudentTypeAndActiveTrue(Student.StudentType studentType);

    List<Student> findByActiveTrueAndStudentType(Student.StudentType studentType);

    Optional<Student> findBySeat_Library_IdAndSeat_SeatNumberAndActiveTrue(
            Long libraryId,
            int seatNumber
    );

    long countBySeat_Library_IdAndStudentTypeAndActiveTrue(
            Long libraryId,
            Student.StudentType type
    );


    List<Student>
    findBySeat_Library_IdAndActiveTrueAndExpiryDateBetween(
            Long libraryId,
            LocalDate start,
            LocalDate end
    );

    List<Student>
    findBySeat_Library_IdAndActiveTrueAndExpiryDateBefore(
            Long libraryId,
            LocalDate date
    );


    List<Student> findByLibrary_IdAndStudentTypeAndActiveTrue(
            Long libraryId,
            Student.StudentType studentType
    );


    long countByLibrary_IdAndStudentTypeAndActiveTrue(
            Long libraryId,
            Student.StudentType studentType
    );



}
