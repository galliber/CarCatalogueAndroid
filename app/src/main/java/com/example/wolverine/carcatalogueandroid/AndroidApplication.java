package com.example.wolverine.carcatalogueandroid;


import com.example.wolverine.carcatalogueandroid.http.HttpRequester;
import com.example.wolverine.carcatalogueandroid.http.OkHttpHttpRequester;
import com.example.wolverine.carcatalogueandroid.parsers.Base.JsonParser;
import com.example.wolverine.carcatalogueandroid.parsers.GsonParser;
import com.example.wolverine.carcatalogueandroid.repositories.CarHttpRepository;
import com.example.wolverine.carcatalogueandroid.repositories.PersonalCarHttpRepository;
import com.example.wolverine.carcatalogueandroid.repositories.base.Repository;

import java.util.HashMap;
import java.util.Map;

public class AndroidApplication {
    private static Map<String, Repository> repositoryMap;
    private static Map<String, JsonParser> jsonParserMap;
    private static Repository personalRepository;
    private static HttpRequester httpRequester;

    static {
        repositoryMap=new HashMap<>();
        jsonParserMap=new HashMap<>();
    }

    public static <T> Repository<T> getCarRepository(Class<T> klass, Class<T[]> arrKlass){
        String klassKey=klass.getSimpleName();
        if(!repositoryMap.containsKey(klassKey)){
            String serverUrl=getServerBaseUrl();
            HttpRequester httpRequester=getHttpRequester();
            JsonParser jsonParser=getJsonParser(klass, arrKlass);
            Repository<T> repository=new CarHttpRepository<>(httpRequester, serverUrl, jsonParser);
            repositoryMap.put(klassKey, repository);
        }
        return repositoryMap.get(klassKey);
    }

    public static <T> Repository<T> getPersonalCarRepository(Class<T> klass, Class<T[]> arrKlass){
        if(personalRepository==null){
            String serverUrl=getServerBaseUrl();
            HttpRequester httpRequester=getHttpRequester();
            JsonParser<T> jsonParser=getJsonParser(klass, arrKlass);
            personalRepository=new PersonalCarHttpRepository<>(httpRequester, serverUrl, jsonParser);
        }
        return personalRepository;
    }



    private static String getServerBaseUrl() {
        return Constants.SERVER_URL;
    }

    public static HttpRequester getHttpRequester() {
        if(httpRequester==null){
            httpRequester=new OkHttpHttpRequester();
        }
        return httpRequester;
    }

    public static <T> JsonParser<T> getJsonParser(Class<T> klass, Class<T[]> arrKlass) {
        String klassKey=klass.getSimpleName();
        if(!jsonParserMap.containsKey(klassKey)){
            JsonParser<T> jsonParser=new GsonParser<>(klass, arrKlass);
            jsonParserMap.put(klassKey, jsonParser);
        }
        return jsonParserMap.get(klassKey);
    }
}
