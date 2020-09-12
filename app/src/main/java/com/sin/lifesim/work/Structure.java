package com.sin.lifesim.work;

public class Structure {
    public TypZamestnani first;
    public TypZamestnani second;
    public TypZamestnani third;
    public int firstCourse;
    public int secondCourse;
    public int thirdCourse;
    public String type;

    public Structure(TypZamestnani first, TypZamestnani second, TypZamestnani third, int firstCourse, int secondCourse, int thirdCourse) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.firstCourse = firstCourse;
        this.secondCourse = secondCourse;
        this.thirdCourse = thirdCourse;
        test();
    }

    private void test() {
        if (first == null) {
            type = "no transform";
        } else {
            type = "transformable";
        }
    }
}
