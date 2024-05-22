package Test;

import java.util.ArrayList;

public class AlgoResult {

    String algorithmName;
    ArrayList<Pair> CPUactivity;
    ArrayList<String> executionOrder;

    public AlgoResult(String algorithmName){
        this.algorithmName = algorithmName;
        this.CPUactivity = new ArrayList<>();
        this.executionOrder = new ArrayList<>();
    }

    public String getAlgorithmName() {
        return algorithmName;
    }
    public ArrayList<Pair> getCPUactivity() {
        return CPUactivity;
    }

    public ArrayList<String> getExecutionOrder() {
        return executionOrder;
    }

    public static class Pair {
        private int startTime;
        private int stopTime;
        public Pair(int num1, int num2){
            this.startTime = num1;
            this.stopTime = num2;
        }

        public void setStartTime(int time) { this.startTime = time;}
        public int getStartTime() { return startTime;}
        public void setStopTime(int time) { this.stopTime = time;}
        public int getStopTime() { return stopTime;}
    }
}
