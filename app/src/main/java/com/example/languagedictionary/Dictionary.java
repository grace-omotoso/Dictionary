package com.example.languagedictionary;

public class Dictionary {
    private String foreign_text;
    private String english_translation;
    private String comment_text;
    private String language_text;


    public Dictionary(String foreign_text, String english_translation, String comment_text, String language_text) {
        this.foreign_text = foreign_text;
        this.english_translation = english_translation;
        this.comment_text = comment_text;
        this.language_text = language_text;
    }


    public String getForeign_text() {
        return foreign_text;
    }

    public void setForeign_text(String foreign_text) {
        this.foreign_text = foreign_text;
    }

    public String getEnglish_translation() {
        return english_translation;
    }

    public void setEnglish_translation(String english_translation) {
        this.english_translation = english_translation;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public String getLanguage_text() {
        return language_text;
    }

    public void setLanguage_text(String language_text) {
        this.language_text = language_text;
    }
}
