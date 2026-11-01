package uml;

import java.time.LocalDate;

public class Document extends ObjectPlusPlus {
    private final int id;
    private final String type;
    private final String numberAndSeries;
    private final LocalDate issueDate;
    private final String country;
    private String additionalInformation;
    private static int nextId = 1;


    public Document(String type, String numberAndSeries, LocalDate issueDate, String country) {
        super();
        if (type == null) throw new IllegalArgumentException("Type is required!");
        if (numberAndSeries == null) throw new IllegalArgumentException("Number and/or series Type is required!");
        if (issueDate == null) throw new IllegalArgumentException("Issue date Type is required!");
        if (country == null) throw new IllegalArgumentException("Country Type is required!");

        this.id = nextId++;
        this.type = type;
        this.numberAndSeries = numberAndSeries;
        this.issueDate = issueDate;
        this.country = country;
    }

    public Document(String type, String numberAndSeries, LocalDate issueDate, String country, String additionalInformation) {
        this(type, numberAndSeries, issueDate, country);
        this.additionalInformation = additionalInformation;
    }

    public int getId() {
        return id;
    }
    public LocalDate getIssueDate() {
        return issueDate;
    }
    public String getAdditionalInformation() {
        return additionalInformation;
    }
    public String getCountry() {
        return country;
    }
    public String getNumberAndSeries() {
        return numberAndSeries;
    }
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "id=" + id + ", " + type + ", " + numberAndSeries;
    }
}
