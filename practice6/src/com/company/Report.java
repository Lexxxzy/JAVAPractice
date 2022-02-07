package com.company;

import java.util.ArrayList;

public class Report {
    private static ArrayList<String> logHistory = new ArrayList<>(); //храним записи

    public static void writeInfo(String message) {
        logHistory.add(message);
    }

    public static ArrayList<String> getLogHistory() {
        return logHistory;

    }
}