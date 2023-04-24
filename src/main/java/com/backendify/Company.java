package com.backendify;

import java.util.Objects;

public class Company {
    String id;
    String name;
    boolean active;
    String active_until;


    public Company() {
    }
    public Company(String id, String name, boolean active, String active_until) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.active_until = active_until;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setActive_until(String active_until) {
        this.active_until = active_until;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public String getActive_until() {
        return active_until;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return active == company.active && Objects.equals(id, company.id) && Objects.equals(name, company.name) && Objects.equals(active_until, company.active_until);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, active, active_until);
    }

}
