package com.github.jjfhj.tests;

public enum ProfileMenu {
    ORDER_STATUS("Статус заказа"),
    SIGN_IN("Войти"),
    COMPARISON("Сравнение"),
    FAVORITES("Избранное"),
    CART("Корзина");

    private String profileMenu;

    ProfileMenu(String profileMenu) {
        this.profileMenu = profileMenu;
    }

    public String getProfileMenu() {
        return profileMenu;
    }

    @Override
    public String toString() {
        return profileMenu;
    }
}
