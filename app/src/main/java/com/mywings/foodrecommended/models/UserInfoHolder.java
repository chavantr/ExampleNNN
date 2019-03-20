package com.mywings.foodrecommended.models;

public class UserInfoHolder {


    private Calories calories;

    public static UserInfoHolder getInstance() {
        return UserInfoHolderHelper.INSTANCE;
    }

    public Calories getCalories() {
        return calories;
    }

    public void setCalories(Calories calories) {
        this.calories = calories;
    }

    private static class UserInfoHolderHelper {
        final static UserInfoHolder INSTANCE = new UserInfoHolder();
    }

}
