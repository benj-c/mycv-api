package com.mycv.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class CvData {

    private int id;
    private String summery;
    private String first_name;
    private String surname;
    private String country;
    private String city;
    private String email;
    private String contact_number;
    private CvJobField cvJobField;
    private List<EduQualification> eduHistory;
    private List<ProfQualification> profQualifications;
    private List<SpecificSkill> specificSkills;
    private List<WorkExperience> workExperiences;

    @Data
    @AllArgsConstructor
    public static class CvJobField {
        private int id;
        private String title;
    }

    @Data
    @Builder
    public static class EduQualification {
        private Integer id;
        private String institutionName;
        private String location;
        private LocalDate from;
        private LocalDate to;
        private EduField eduField;
        private DegreeLevel degreeLevel;

        @Data
        @AllArgsConstructor
        public static class EduField {
            private int id;
            private String title;
        }

        @Data
        @AllArgsConstructor
        public static class DegreeLevel {
            private int id;
            private String title;
        }
    }

    @Data
    @Builder
    public static class ProfQualification {
        private Integer id;
        private String title;
        private String field;
        private String description;
    }

    @Data
    @Builder
    public static class SpecificSkill {
        private Integer id;
        private String title;
        private String description;
        private String field;
    }

    @Data
    @Builder
    public static class WorkExperience {
        private Integer id;
        private String jobTitle;
        private String country;
        private String employer;
        private String city;
        private Boolean isCurrentJob;
        private LocalDate startDate;
        private LocalDate endDate;
        private EmploymentType employmentType;

        @Data
        @AllArgsConstructor
        public static class EmploymentType {
            private int id;
            private String title;
        }
    }
}
