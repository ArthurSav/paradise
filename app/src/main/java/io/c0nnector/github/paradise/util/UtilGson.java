package io.c0nnector.github.paradise.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Gson helper
 */
public class UtilGson {

    private static Gson gson;

    private static Gson prettyGson;

    public static Gson getGson(){
        if (gson == null) gson = new Gson();
        return gson;
    }

    /**
     * Enables pretty print
     * @return
     */
    public static Gson getPrettyGson(){
        if (prettyGson ==null) prettyGson = new GsonBuilder().setPrettyPrinting().create();
        return prettyGson;
    }

    /**
     * Converts a pojo into a gson string
     * @param pojoToSerialize
     * @return
     */
    public static String serialize(Object pojoToSerialize){
        return getPrettyGson().toJson(pojoToSerialize);
    }

    public static <T> T deserialize(String serializedObject, Class<T> cls){
        return getPrettyGson().fromJson(serializedObject, cls);
    }

    public static <T>String serialize(ArrayList<T> listToSerialize){
        return getPrettyGson().toJson(listToSerialize);
    }

    public static <T> List<T> deserialize(String arrayAsJsonString){

        Type type = new TypeToken<List<T>>(){}.getType();

        return getPrettyGson().fromJson(arrayAsJsonString, type);
    }
}
