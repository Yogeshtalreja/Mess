package com.example.mess.enums;

public enum UserTypeEnum {

    MEMBER(1),
    COOK(2);

    private int type;

    UserTypeEnum(int type) {
        this.type = type;
    }

    public int getType(){
        return this.type;
    }
}
