package com.phuscduowng.lev3;

public class Story {

    public String name_e, name_v, content_e, content_v, img, id, vocabulary;

    public Story() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Story(String name_e, String name_v, String content_e, String content_v, String img, String id, String vocabulary) {
        this.name_e = name_e;
        this.name_v = name_v;
        this.content_e = content_e;
        this.content_v = content_v;
        this.img = img;
        this.id = id;
        this.vocabulary = vocabulary;
    }

    public String getName_e() {
        return name_e;
    }

    public void setName_e(String name_e) {
        this.name_e = name_e;
    }

    public String getName_v() {
        return name_v;
    }

    public void setName_v(String name_v) {
        this.name_v = name_v;
    }

    public String getContent_e() {
        return content_e;
    }

    public void setContent_e(String content_e) {
        this.content_e = content_e;
    }

    public String getContent_v() {
        return content_v;
    }

    public void setContent_v(String content_v) {
        this.content_v = content_v;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(String vocabulary) {
        this.vocabulary = vocabulary;
    }

}
