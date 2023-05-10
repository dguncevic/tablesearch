/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.filip.tablehw;

import java.util.Objects;

/**
 *
 * @author 19par
 */
public class City {
    private int id;
    private String cityName;
    private String cityState;
    private String zip;
    
    public String getCityState() {
        return cityState;
    }

    public void setCityState(String cityState) {
        this.cityState = cityState;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final City other = (City) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.cityName, other.cityName)) {
            return false;
        }
        return Objects.equals(this.zip, other.zip);
    }

    @Override
    public String toString() {
        return cityName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return cityName;
    }

    public void setName(String name) {
        this.cityName = name;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    
}
