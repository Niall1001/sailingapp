package com.unosquare.sailingapp.constant;

public final class AppConstants {
    private AppConstants(){}
    public static final int EMAIL_MIN_SIZE = 5;
    public static final int EMAIL_MAX_SIZE = 100;
    public static final String EMAIL_REGEX = "^([^ @])+@([^ \\.@]+\\.)+([^ \\.@])+$";
    public static final String SPECIAL_CHARACTERS = "@#$%^&+=!?";
    public static final int PASSWORD_MIN_SIZE = 8;
    public static final int PASSWORD_MAX_SIZE = 65;
    public static final String VALID_PASSWORD_MESSAGE = "Password must contain between 8 and 20 characters and at least"
            + " 2 of the following: Alphanumeric characters, one special character ( " + SPECIAL_CHARACTERS + " ),"
            + " one capital letter.";

    public static final String ACTIVE = "ACTIVE";
    public static final String ADMIN = "ADMIN";
    public static final String DEV = "DEV";
    public static final String LOCKED_OUT = "LOCKED_OUT";
    public static final String STAGED = "STAGED";
    public static final String SUSPENDED = "SUSPENDED";
}
