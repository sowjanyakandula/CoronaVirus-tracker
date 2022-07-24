package io.javapratice.CoronoVirustracker.models;

public class locationStats {

    private String state;
    private String country;
    private int latestTotalCases;
    private int difffromPrevday;

    public int getDifffromPrevday() {
        return difffromPrevday;
    }

    public void setDifffromPrevday(int difffromPrevday) {
        this.difffromPrevday = difffromPrevday;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestTotalCases() {
        return latestTotalCases;
    }

    public void setLatestTotalCases(int latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }

    @Override
    public String toString() {
        return "locationStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestTotalCases=" + latestTotalCases +
                '}';
    }
}
