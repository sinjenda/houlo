package com.sin.lifesim.work;

public class Structure {
    public String first;
    public String second;
    public String third;
    public String type;

    public Structure(String first, String second, String third) {
        this.first = first;
        this.second = second;
        this.third = third;

    }

    private void test() {
        // TODO: set type to other
        if (first == null) {
            type = "no transform";
        }
    }
}
