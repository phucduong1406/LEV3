package com.phuscduowng.lev3;

public class Dictionary {

    public String word;
    public String pronun;
    public String mean;
    public String detail;
    public String topic;
    public String def;
    public String syn;
    public String ex;
    public Boolean favorite_word;
    public Boolean recent_word;


    public Dictionary() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Dictionary(String word, String def, String syn, String ex, String pronun, String mean, String detail, String topic, Boolean favorite_word, Boolean recent_word) {
        this.word = word;
        this.pronun = pronun;
        this.def = def;
        this.syn = syn;
        this.ex = ex;
        this.mean = mean;
        this.detail = detail;
        this.topic = topic;
        this.favorite_word = favorite_word;
        this.recent_word = recent_word;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Boolean getFavorite_word() {
        return favorite_word;
    }

    public void setFavorite_word(Boolean favorite_word) {
        this.favorite_word = favorite_word;
    }

    public Boolean getRecent_word() {
        return recent_word;
    }

    public void setRecent_word(Boolean recent_word) {
        this.recent_word = recent_word;
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
