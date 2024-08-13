package com.sahil.onlineExamination.util;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@Component
public class TimerUtil implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        event.getSession().setMaxInactiveInterval(30 * 60); // 30 minutes
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        // Do nothing
    }
}
