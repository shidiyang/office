package com.lu.office.service.utile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Constants {

    public static String EMAIL_HOST;
    public static String EMAIL_PASSWORD;
    public static String EMAIL_FROM;
    public static String EMAIL_USERNAME;


    Properties props = new Properties();
    InputStream inputStream = null;
    public Constants() {
        try {
            inputStream = getClass().getResourceAsStream("/mail.properties");
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        EMAIL_HOST = props.getProperty("mail.host");
        EMAIL_HOST = props.getProperty("mail.password");
        EMAIL_HOST = props.getProperty("mail.from");
        EMAIL_HOST = props.getProperty("mail.userName");
    }

}