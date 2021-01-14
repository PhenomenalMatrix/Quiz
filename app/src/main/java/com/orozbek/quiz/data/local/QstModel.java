package com.orozbek.quiz.data.local;

public class QstModel {
    private String qstTitile;
    private String []variants;
    private boolean multy;

    public QstModel(String qstTitile, String[] variants, boolean multy) {
        this.qstTitile = qstTitile;
        this.variants = variants;
        this.multy = multy;
    }

    public String getQstTitile() {
        return qstTitile;
    }

    public void setQstTitile(String qstTitile) {
        this.qstTitile = qstTitile;
    }

    public String[] getVariants() {
        return variants;
    }

    public void setVariants(String[] variants) {
        this.variants = variants;
    }

    public boolean isMulty() {
        return multy;
    }

    public void setMulty(boolean multy) {
        this.multy = multy;
    }
}
