package com.phuscduowng.lev3;

public class Flashcards {
    private String image, word, pronun, mean, def, syn, ex;

    public Flashcards(String image, String word, String def, String syn, String pronun, String mean, String ex) {
        this.image = image;
        this.word = word;
        this.pronun = pronun;
        this.mean = mean;
        this.def = def;
        this.syn = syn;
        this.ex = ex;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPronun() {
        return pronun;
    }

    public void setPronun(String pronun) {
        this.pronun = pronun;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public String getEx() {
        return ex;
    }

    public void setEx(String ex) {
        this.ex = ex;
    }

    public String getSyn() {
        return syn;
    }

    public void setSyn(String syn) {
        this.syn = syn;
    }
}
