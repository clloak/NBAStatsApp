package com.example.nbastatsapp;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class APIResponse<T> {
    @SerializedName("response")
    private List<T> response;

    public List<T> getResponse() {
        return response;
    }

    public void setResponse(List<T> response) {
        this.response = response;
    }
}
