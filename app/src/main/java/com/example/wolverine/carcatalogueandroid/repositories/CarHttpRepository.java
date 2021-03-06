package com.example.wolverine.carcatalogueandroid.repositories;

import com.example.wolverine.carcatalogueandroid.http.HttpRequester;
import com.example.wolverine.carcatalogueandroid.parsers.Base.JsonParser;
import com.example.wolverine.carcatalogueandroid.repositories.base.Repository;

import java.io.IOException;
import java.util.List;


public class CarHttpRepository<T> implements Repository<T> {
    private final HttpRequester mHttpRequester;
    private String mUrl;
    private JsonParser<T> mParser;

    public CarHttpRepository(HttpRequester httpRequester, String url, JsonParser parser) {
        this.mHttpRequester = httpRequester;
        this.mUrl = url;
        this.mParser = parser;
    }

    @Override
    public List<T> getAll() throws IOException {
        String jsonArr = mHttpRequester.get(mUrl + "/cars/getAll");
        List<T> list = mParser.fromJsonArray(jsonArr);
        return list;
    }

    @Override
    public void add(T item) throws IOException {
        String addUrl = mUrl + "/cars/addToAll";
        String body = mParser.toJson(item);
        mHttpRequester.post(addUrl, body);

    }

    @Override
    public void delete(int id) throws IOException {
        String deleteUrl = mUrl + "/cars/deleteFromAll/" + id;
        mHttpRequester.delete(deleteUrl);
    }

    @Override
    public T getById(int id) throws IOException {
        String jsonObj = mHttpRequester.get(mUrl + "/cars/getAll/" + id);
        T obj = mParser.fromJson(jsonObj);
        return obj;
    }
}
