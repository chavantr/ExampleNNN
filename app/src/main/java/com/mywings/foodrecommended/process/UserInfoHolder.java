package com.mywings.foodrecommended.process;

import com.mywings.foodrecommended.models.Food;
import com.mywings.foodrecommended.models.User;

public class UserInfoHolder {

    private User user;

    private Food food;

    public static UserInfoHolder getInstance() {
        return UserInfoHolderHelper.INSTANCE;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    private static class UserInfoHolderHelper {

        static UserInfoHolder INSTANCE = new UserInfoHolder();

    }

}
