package com.example.popularnetworklanguage;

/**
 * Created by Administrator on 2016/12/25.
 */

public class Words {
    private String name;
    private String explanation;

    public Words(String name,String explanation) {
        this.name=name;
        this.explanation=explanation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
