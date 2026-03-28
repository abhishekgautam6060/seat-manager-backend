package com.library.seatmanager.service;

import com.library.seatmanager.repository.SeatRepository;
import com.library.seatmanager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private SeatRepository seatRepo;

    @Autowired
    private StudentRepository studentRepo;



    public Map<String, Object> getDashboardStats() {
        Map<String, Object> map = new HashMap<>();

        long totalSeats = seatRepo.count();
        long filledSeats = studentRepo.countByActiveTrue();
        long vacantSeats = totalSeats - filledSeats;

        Integer totalCollection = studentRepo.sumTotalCollection();

        map.put("totalSeats", totalSeats);
        map.put("filledSeats", filledSeats);
        map.put("vacantSeats", vacantSeats);
        map.put("totalCollection", totalCollection == null ? 0 : totalCollection);

        return map;
    }
}

