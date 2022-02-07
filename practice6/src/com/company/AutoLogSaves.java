package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class AutoLogSaves extends Thread{
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try (FileWriter logWriter = new FileWriter("log.txt");
                 BufferedWriter bufferedWriter = new BufferedWriter(logWriter)) {
                ArrayList<String> logBuffer = Report.getLogHistory();
                for (String s : logBuffer) {
                    s += "\n";
                    bufferedWriter.write(s);
                }
                bufferedWriter.flush();
                TimeUnit.SECONDS.sleep(5);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
