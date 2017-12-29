package com.myapp.railwayapp.Infrastructure;

public class complainStr {

    private String Str;
    private int C;

    public complainStr() {
    }

    public complainStr(String str, int c) {
        Str = str;
        C = c;
    }

    public String getStr() {
        return Str;
    }

    public int getC() {
        return C;
    }

    public void setStr(String str) {
        Str = str;
    }

    public void setC(int c) {
        C = c;
    }
}
