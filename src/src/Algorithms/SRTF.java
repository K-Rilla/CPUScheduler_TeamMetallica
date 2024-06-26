package Algorithms;

import Test.AlgoResult;
import Test.SProcess;

import java.util.ArrayList;

public class SRTF implements SchedulerAlgorithm{

    AlgoResult result;

    public SRTF(boolean showOutput) {
        this.result = new AlgoResult(getName(), showOutput);
    }
    /**
     * Method to run a list of processes through a scheduling algorithm
     *
     * @param processes List of processes
     * @return An AlgoResult object, a summary of the findings
     */
    @Override
    public AlgoResult runAlgo(ArrayList<SProcess> processes) {
        return null;
    }

    /**
     * Method to return the name of the scheduling algorithm
     *
     * @return Name of Scheduling algo
     */
    @Override
    public String getName() {
        return null;
    }

    @Override
    public AlgoResult getResults(){
        return result;
    }
}
