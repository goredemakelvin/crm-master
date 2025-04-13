package com.cicosy.crm.entity;

public enum LeadStage {

    ACCOUNT(1), CONTACT(2),OPPORTUNITY(3);

    private int stage;
    private LeadStage(int stage) {}
    public int getStage() {
        return stage;
    }
    public void setStage(int stage) {
        this.stage = stage;
    }
}
