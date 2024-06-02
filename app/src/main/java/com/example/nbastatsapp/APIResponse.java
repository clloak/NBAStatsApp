package com.example.nbastatsapp;
import java.util.List;
public class APIResponse<T> {
    private List<T> response;

    public List<T> getResponse() {
        return response;
    }

    public void setResponse(List<T> response) {
        this.response = response;
    }
}
