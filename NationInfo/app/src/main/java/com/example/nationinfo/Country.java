package com.example.nationinfo;

public class Country {
    private String flag, name, map, area, population;

    public Country(String flag, String name, String map, String population, String area) {
        this.flag = flag;
        this.name = name;
        this.map = map;
        this.population = population;
        this.area = area;
    }

    public String getFlag() {
        return flag;
    }

    public String getName() {
        return name;
    }

    public String getMap() {
        return map;
    }

    public String getPopulation() {
        return population;
    }

    public String getArea() {
        return area;
    }

    @Override
    public String toString() {
        return "{" + "flag:" + flag + ",name:" + name + '\'' + ",map:" + map + '\'' + ",area:" + area + ",population:" + population + '}';
    }
}
