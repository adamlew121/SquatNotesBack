package pl.edu.ug.squat_notes.domain;

import java.util.Date;

public class ChartPoint {
    private Date date;
    private Double weight;

    public ChartPoint() {
    }

    public ChartPoint(Date date, Double weight) {
        this.date = date;
        this.weight = weight;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
