package com.example.NalogaApi.counter;

public class Counter {
    private int succesful;
    private int unsuccesful;

    public Counter() {
        this.succesful = 0;
        this.unsuccesful = 0;
    }

    public void addSuccesful(){
        succesful++;
    }

    public void unSuccesful(){
        unsuccesful++;
    }

    public int getSuccesful() {
        return succesful;
    }

    public int getUnsuccesful() {
        return unsuccesful;
    }

    @Override
    public String toString() {
        return "Counted calls:\n" +
                "succesful: " + succesful +
                ", unsuccesful: " + unsuccesful +
                "all calls: " + (succesful+unsuccesful);
    }
}
