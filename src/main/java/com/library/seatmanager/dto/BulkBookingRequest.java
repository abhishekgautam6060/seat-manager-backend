package com.library.seatmanager.dto;

import java.util.List;

public class BulkBookingRequest {
    
    private Long libraryId;
    private List<StudentCreateRequest> students;

    public Long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Long libraryId) {
        this.libraryId = libraryId;
    }

    public List<StudentCreateRequest> getStudents() {
        return students;
    }

    public void setStudents(List<StudentCreateRequest> students) {
        this.students = students;
    }

    public BulkBookingRequest(Long libraryId, List<StudentCreateRequest> students) {
        this.libraryId = libraryId;
        this.students = students;
    }

    public BulkBookingRequest() {
    }
}
