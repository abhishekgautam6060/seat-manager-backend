package com.library.seatmanager.repository;


import com.library.seatmanager.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat,Long> {

    long countByOccupiedTrue();

    Optional<Seat> findBySeatNumber(int seatNumber);

    List<Seat> findByLibraryId(Long libraryId);

    List<Seat> findByLibraryIdOrderBySeatNumberAsc(Long libraryId);

    Optional<Seat> findByLibraryIdAndSeatNumber(Long libraryId, int seatNumber);

    int countByLibraryId(Long libraryId);

    @Query("SELECT COUNT(s) > 0 FROM Student s WHERE s.library.id = :libraryId AND s.seatNumber = :seatNumber AND s.active = true")
    boolean isSeatOccupied(Long libraryId, Integer seatNumber);

}
