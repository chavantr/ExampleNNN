package com.mywings.foodrecommended.models;

public class UserInfoHolder {

    private Calories calories;
    private double breakfast;
    private double lunch;
    private double dinner;
    private double snacks;

    public static UserInfoHolder getInstance() {
        return UserInfoHolderHelper.INSTANCE;
    }

    public Calories getCalories() {
        return calories;
    }

    public void setCalories(Calories calories) {
        this.calories = calories;
    }

    public double getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(double breakfast) {
        this.breakfast = breakfast;
    }

    public double getLunch() {
        return lunch;
    }

    public void setLunch(double lunch) {
        this.lunch = lunch;
    }

    public double getDinner() {
        return dinner;
    }

    public void setDinner(double dinner) {
        this.dinner = dinner;
    }

    public double getSnacks() {
        return snacks;
    }

    public void setSnacks(double snacks) {
        this.snacks = snacks;
    }

    private static class UserInfoHolderHelper {
        final static UserInfoHolder INSTANCE = new UserInfoHolder();
    }

}
