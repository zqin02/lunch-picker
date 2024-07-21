package com.example.lunch.bean;

public class ResultInfo implements BaseAction{
    private final static String PUBLISH_ACTION = "PUBLISH";
    private String resultSelection;

    public ResultInfo() {
    }

    public ResultInfo(String resultSelection) {
        this.resultSelection = resultSelection;
    }

    public String getResultSelection() {
        return resultSelection;
    }

    public void setResultSelection(String resultSelection) {
        this.resultSelection = resultSelection;
    }
    @Override
    public String getAction() {
        return PUBLISH_ACTION;
    }
}
