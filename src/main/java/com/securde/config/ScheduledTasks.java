package com.securde.config;

import com.securde.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kevin on 7/10/2017.
 */

@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    UserService userService;

    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
        userService.lockTemporaryAccounts();
    }
}
