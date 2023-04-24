package com.backendify.entity;

public class BackendOneResponse {

    private String cn;
    private String created_on;
    private String closed_on;

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public String getClosed_on() {
        return closed_on;
    }

    public void setClosed_on(String closed_on) {
        this.closed_on = closed_on;
    }

    public BackendOneResponse(String cn, String created_on, String closed_on) {
        this.cn = cn;
        this.created_on = created_on;
        this.closed_on = closed_on;
    }


}
