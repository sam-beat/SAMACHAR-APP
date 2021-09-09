package com.example.samachar;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class NewsMmodal {
    private String status;
    private  int totalResults;

    private ArrayList<Articles> articles;

    public NewsMmodal(int totalResult, String status, ArrayList<Articles> articles) {
        this.totalResults = totalResult;
        this.status = status;
        this.articles = articles;
    }

    public int getTotalResult() {
        return totalResults;
    }

    public void setTotalResult(int totalResult) {
        this.totalResults = totalResult;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Articles> getArticles() {


        return articles;
    }

    public void setArticles(ArrayList<Articles> articles) {
        this.articles = articles;
    }
}
