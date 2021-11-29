package com.techelevator.model;

import java.util.Objects;

public class Site {

    private int siteId;
    private int campgroundId;
    private int siteNumber;
    private int maxOccupancy;
    private boolean accessible;
    private int maxRvLength;
    private boolean utilities;

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public int getCampgroundId() {
        return campgroundId;
    }

    public void setCampgroundId(int campgroundId) {
        this.campgroundId = campgroundId;
    }

    public int getSiteNumber() {
        return siteNumber;
    }

    public void setSiteNumber(int siteNumber) {
        this.siteNumber = siteNumber;
    }

    public int getMaxOccupancy() {
        return maxOccupancy;
    }

    public void setMaxOccupancy(int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }

    public int getMaxRvLength() {
        return maxRvLength;
    }

    public void setMaxRvLength(int maxRvLength) {
        this.maxRvLength = maxRvLength;
    }

    public boolean isUtilities() {
        return utilities;
    }

    public void setUtilities(boolean utilities) {
        this.utilities = utilities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Site site = (Site) o;
        return siteId == site.siteId && campgroundId == site.campgroundId && siteNumber == site.siteNumber && maxOccupancy == site.maxOccupancy && accessible == site.accessible && maxRvLength == site.maxRvLength && utilities == site.utilities;
    }

    @Override
    public int hashCode() {
        return Objects.hash(siteId, campgroundId, siteNumber, maxOccupancy, accessible, maxRvLength, utilities);
    }

    @Override
    public String toString() {
        return "Site{" +
                "siteId=" + siteId +
                ", campgroundId=" + campgroundId +
                ", siteNumber=" + siteNumber +
                ", maxOccupancy=" + maxOccupancy +
                ", accessible=" + accessible +
                ", maxRvLength=" + maxRvLength +
                ", utilities=" + utilities +
                '}';
    }
}
