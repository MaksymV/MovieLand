package com.volomak.movieland.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

/**
 * Created by grey4 on 16.06.2016.
 */
@Service
public class JsonConverter {

    JsonConverter(){
        GsonBuilder builder = new GsonBuilder();
        builder.create();
        builder.setPrettyPrinting();
        gson = builder.create();
    }
    private Gson gson;

    public String toJson(Object object){
        return gson.toJson(object);
    }

}