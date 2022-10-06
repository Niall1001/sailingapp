package com.unosquare.sailingapp.constant;

public final class AppConstants {
    private AppConstants(){}
    public static final int EMAIL_MIN_SIZE = 5;
    public static final int EMAIL_MAX_SIZE = 100;
    public static final String EMAIL_REGEX = "^([^ @])+@([^ \\.@]+\\.)+([^ \\.@])+$";
    public static final String SPECIAL_CHARACTERS = "@#$%^&+=!?";
    public static final int PASSWORD_MIN_SIZE = 8;
    public static final int PASSWORD_MAX_SIZE = 65;
    public static final int ALIAS_CURRENT_USER = 0;

    public static final String ACTIVE = "ACTIVE";
    public static final String ADMIN = "ADMIN";
    public static final String BOAT_OWNER = "BOAT_OWNER";
    public static final String CREW = "CREW";


    public static final String NOT_PERMITTED = "Not permitted";
}
