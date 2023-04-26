package com.backendify.entity;

public class BackendTwoResponse {

    private String company_name;
    private String tin;
    private String dissolved_on;

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getDissolved_on() {
        return dissolved_on;
    }

    public void setDissolved_on(String dissolved_on) {
        this.dissolved_on = dissolved_on;
    }
}
