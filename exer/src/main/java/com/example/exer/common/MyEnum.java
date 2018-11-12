package com.example.exer.common;

public enum MyEnum {
    //水果名称  数量  描述  颜色
    FRINT("apple", "12", "苹果", "绿色");

    private final String frint;

    private final String sum;

    private final String desc;

    private final String yanse;

    MyEnum(String frint, String sum, String desc, String yanse) {
        this.frint = frint;
        this.sum = sum;
        this.desc = desc;
        this.yanse = yanse;
    }

    public static MyEnum getEnum(String enumCode) {

        for (MyEnum myEnum : values()) {
            if (myEnum.getFrint().equals(enumCode)) {
                return myEnum;
            }
        }
        return null;
    }

    public String getFrint() {
        return frint;
    }

    public String getSum() {
        return sum;
    }

    public String getDesc() {
        return desc;
    }

    public String getYanse() {
        return yanse;
    }
}
