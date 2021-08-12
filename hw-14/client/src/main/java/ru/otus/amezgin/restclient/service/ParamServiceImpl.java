package ru.otus.amezgin.restclient.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ParamServiceImpl implements ParamService {

    private final String url;

    private final String userName;

    private final String userPass;

    private final long sessionTime;

    public ParamServiceImpl(@Value("${library.url}") String url,
                            @Value("${library.userName}") String userName,
                            @Value("${library.userPass}") String userPass,
                            @Value("${library.sessionTime}") long sessionTime) {
        this.url = url;
        this.userName = userName;
        this.userPass = userPass;
        this.sessionTime = sessionTime;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return userPass;
    }

    @Override
    public long getSessionTime() {
        return sessionTime;
    }
}
