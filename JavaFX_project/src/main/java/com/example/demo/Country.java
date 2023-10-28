package com.example.demo;

public class  Country {
    private String names;
    private String flag;
    private String capitals;
    private String largestCity;
    private String neighbours;

    public Country(String names,  String capitals, String largestCity, String neighbours, String flag) {
        this.names = names;
        this.flag = flag;
        this.capitals = capitals;
        this.largestCity = largestCity;
        this.neighbours = neighbours;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCapitals() {
        return capitals;
    }

    public void setCapitals(String capitals) {
        this.capitals = capitals;
    }

    public String getLargestCity() {
        return largestCity;
    }

    public void setLargestCity(String largestCity) {
        this.largestCity = largestCity;
    }

    public String getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(String neighbours) {
        this.neighbours = neighbours;
    }

}
