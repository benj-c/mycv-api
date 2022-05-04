package com.mycv.util;

import com.mycv.model.entity.*;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.springframework.core.io.ByteArrayResource;
import org.w3c.dom.Document;

import java.io.*;
import java.util.List;

public class FileUtil {

    public static ByteArrayResource generatePdf(CvEntity entity) throws IOException {
        String html = "<!DOCTYPE html><html><head><style>* {margin: 0; padding: 0;}</style></head><body><div style=\"margin: 1rem;\">";

        html += setPersonalData(entity);
        html += setWorkExpData(entity.getWorkExperiencesById());
        html += setProfQualData(entity.getProfessionalQualificationsById());
        html += setEduData(entity.getEducationHistoriesById());
        html += setSpecSkillData(entity.getSpecificSkilsById());

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            final W3CDom w3cDom = new W3CDom();
            final Document w3cDoc = w3cDom.fromJsoup(Jsoup.parse(html));
            final PdfRendererBuilder pdfBuilder = new PdfRendererBuilder();
            pdfBuilder.useFastMode();
            pdfBuilder.withW3cDocument(w3cDoc, "/");
            pdfBuilder.toStream(outputStream);
            pdfBuilder.run();
            ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());
            return resource;
        }
    }

    private static String setPersonalData(CvEntity entity) {
        return "<table><tr><td style='width: 70%;'><h1 style='margin-bottom: 1rem;'>" +
                entity.getFirstName() + " " + entity.getSurname() + "</h1><p>" +
                entity.getSummery() +
                "</p></td><td>" +
                "<b>Email</b> " + entity.getEmail() + "</br>" +
                "<b>Contact Number</b> " + entity.getContactNumber() + "</br>" +
                "<b>Country</b> " + entity.getCountry() + "</br>" +
                "<b>City</b> " + entity.getCity() + "</br>" +
                "</td></tr></table>";
    }

    private static String setWorkExpData(List<WorkExperienceEntity> entities) {
        String s = "<div style=\"margin: 1rem 0;\"><h3 style=\"border-bottom: 1px solid #000; margin-bottom: 0.75rem;\">Work Experience</h3>";
        for (int i = 0; i < entities.size(); i++) {
            WorkExperienceEntity we = entities.get(i);
            s += "<div>\n" +
                    "<h3 style=\"margin: 0;padding: 0;\"><b>" + we.getJobTitle() + "</b></h3>\n" +
                    "<p>" + we.getEmployer() + ", " + we.getCountry() + ", " + we.getCountry() + "</p>\n" +
                    "<p>From: " + we.getStartDate() + ", To: " + (we.getCurrentJob() ? "Present" : we.getEndDate()) + "</p>\n" +
                    "<p></p>\n" +
                    "</div><br>";
        }
        return s;
    }

    private static String setProfQualData(List<ProfessionalQualificationEntity> entities) {
        String s = "<div style=\"margin: 1rem 0;\"><h3 style=\"border-bottom: 1px solid #000; margin-bottom: 0.75rem;\">Professional Qualifications</h3>";
        for (int i = 0; i < entities.size(); i++) {
            ProfessionalQualificationEntity p = entities.get(i);
            s += "<div>\n" +
                    "                    <h3 style=\"margin: 0;padding: 0;\"><b>" + p.getTitle() + "</b></h3>\n" +
                    "                    <p>" + p.getField() + "</p>\n" +
                    "                    <p>" + p.getDescription() + "</p>\n" +
                    "                    <p></p>\n" +
                    "                </div><br>";
        }
        return s;
    }

    private static String setEduData(List<EducationHistoryEntity> entities) {
        String s = "<div style=\"margin: 1rem 0;\"><h3 style=\"border-bottom: 1px solid #000; margin-bottom: 0.75rem;\">Educational Qualifications</h3>";
        for (int i = 0; i < entities.size(); i++) {
            EducationHistoryEntity e = entities.get(i);
            s += "<div>\n" +
                    "                    <h3 style=\"margin: 0;padding: 0;\"><b>" + e.getTitle() + "</b></h3>\n" +
                    "                    <p>" + e.getInstitutionName() + ", " + e.getLocation() + "</p>\n" +
                    "                    <p>" + e.getDegreeLevel().getLevel() + "</p>\n" +
                    "                    <p>" + e.getEducationStudyField().getTitle() + "</p>\n" +
                    "                    <p>From: " + e.getStartDate() + ", To: " + e.getEndDate() + "</p>\n" +
                    "                    <p></p>\n" +
                    "                </div><br>";
        }
        return s;
    }

    private static String setSpecSkillData(List<SpecificSkilEntity> entities) {
        String s = "<div style=\"margin: 1rem 0;\"><h3 style=\"border-bottom: 1px solid #000; margin-bottom: 0.75rem;\">Specific Skils</h3>";
        for (int i = 0; i < entities.size(); i++) {
            SpecificSkilEntity ss = entities.get(i);
            s += "<div>\n" +
                    "                    <h3 style=\"margin: 0;padding: 0;\"><b>" + ss.getTitle() + "</b></h3>\n" +
                    "                    <p>" + ss.getField() + "</p>\n" +
                    "                    <p>" + ss.getDescription() + "</p>\n" +
                    "                    <p></p>\n" +
                    "                </div><br>";
        }
        return s;
    }
}
