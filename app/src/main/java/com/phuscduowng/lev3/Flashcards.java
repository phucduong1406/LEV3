package com.phuscduowng.lev3;

public class Flashcards {
    private String image, word, pronun, mean, def;

    public Flashcards(String image, String word, String def, String pronun, String mean) {
        this.image = image;
        this.word = word;
        this.pronun = pronun;
        this.mean = mean;
        this.def = def;
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
}
