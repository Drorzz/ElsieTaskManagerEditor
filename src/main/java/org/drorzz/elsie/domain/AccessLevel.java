package org.drorzz.elsie.domain;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 10.12.13
 * Time: 16:59
 */
public enum AccessLevel {
    WORKER(0),
    MANAGER(1),
    DIRECTOR(2),
    CEO(3);

    private final int accessLVL;

    AccessLevel(int id) {
        accessLVL = id;
    }

    public int valueByInt(){
        return accessLVL;
    }
}
