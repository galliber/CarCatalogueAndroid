package com.example.wolverine.carcatalogueandroid.repositories;

import com.example.wolverine.carcatalogueandroid.http.HttpRequester;
import com.example.wolverine.carcatalogueandroid.parsers.Base.JsonParser;
import com.example.wolverine.carcatalogueandroid.repositories.base.Repository;

import java.io.IOException;
import java.util.List;

public class PersonalCarHttpRepository<T> implements Repository<T> {
    private final HttpRequester mHttpRequester;
    private String mUrl;
    private JsonParser<T> mParser;

    public PersonalCarHttpRepository(HttpRequester requester, String url, JsonParser parser) {
        mHttpRequester = requester;
        mUrl = url;
        mParser = parser;
    }

    @Override
    public List<T> getAll() throws IOException {
        String jsonArr = mHttpRequester.get(mUrl + "/cars/getPersonal");
        List<T> list = mParser.fromJsonArray(jsonArr);
        return list;
    }

    @Override
    public void add(T item) throws IOException {
        String addUrl = mUrl + "/cars/addToPersonal";
        String body = mParser.toJson(item);
        mHttpRequester.post(addUrl, body);

    }

    @Override
    public void delete(int id) throws IOException {
        String deleteUrl = mUrl + "/cars/deleteFromPersonal/" + id;
        mHttpRequester.delete(deleteUrl);
    }

    @Override
    public T getById(int id) throws IOException {
        String jsonObj = mHttpRequester.get(mUrl + "/cars/getPersonal/" + id);
        T obj = mParser.fromJson(jsonObj);
        return obj;
    }
}
