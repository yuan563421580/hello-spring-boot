package com.yuansb.hello.spring.boot.factory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("all")
public class PropertyFactory {

    private static String NOW_PROFILES_ACTIVE;

    private static String SAY_INFO;

    public static String getNowProfilesActive() {
        return NOW_PROFILES_ACTIVE;
    }

    @Value("${now.profiles.active}")
    public void setNowProfilesActive(String nowProfilesActive) {
        NOW_PROFILES_ACTIVE = nowProfilesActive;
    }

    public static String getSayInfo() {
        return SAY_INFO;
    }

    @Value("${say.info}")
    public void setSayInfo(String sayInfo) {
        SAY_INFO = sayInfo;
    }

}
