package Algorithms;

import Test.AlgoResult;
import Test.SProcess;

import java.util.*;

public class FCFSNonPre implements SchedulerAlgorithm {

    private AlgoResult result;

    public FCFSNonPre(boolean showOutput) {
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
        int currentTime = 0;
        Queue<SProcess> ioQueue = new LinkedList<>();
        Queue<SProcess> readyQueue = new LinkedList<>(processes);

        while (!readyQueue.isEmpty() || !ioQueue.isEmpty()) {
            Queue<SProcess> tempQueue = new LinkedList<>();

            Iterator<SProcess> ioIterator = ioQueue.iterator();
            while (ioIterator.hasNext()) {
                SProcess process = ioIterator.next();
                if (process.getReturnTime() <= currentTime) {
                    readyQueue.add(process);
                    ioIterator.remove();
                }
            }

            if (!readyQueue.isEmpty()) {
                SProcess currentProcess = readyQueue.poll();
                int currentCPUBurst = currentProcess.getBurstTimes()[currentProcess.getCurrCPUindex()];
                currentProcess.setStartTime(currentTime);
                currentProcess.setStopTime(currentTime + currentCPUBurst);
                currentTime += currentCPUBurst;
                // Record the results of the current state of the algorithm
                result.getCPUactivity().add(new AlgoResult.Pair(currentProcess.getStartTime(),
                                                                currentProcess.getStopTime()));
                result.getExecutionOrder().add(currentProcess.getTitle());
                Queue<SProcess> currentReadyQueue = new LinkedList<>();
                for (SProcess process : readyQueue) {
                    int[] burstTimes = Arrays.copyOf(process.getBurstTimes(), process.getBurstTimes().length);
                    SProcess newProcess = new SProcess(process.getTitle(), burstTimes, process.getPriorityLevel());
                    newProcess.setCurrCPUindex(process.getCurrCPUindex());
                    currentReadyQueue.add(newProcess);
                }
                result.getReadyQueueActivity().add(currentReadyQueue);

                Queue<SProcess> currentIOQueue = new LinkedList<>();
                for (SProcess process : ioQueue) {
                    SProcess newProcess = new SProcess(process.getTitle(), new int[0], process.getPriorityLevel());
                    newProcess.setReturnTime(process.getReturnTime());
                    currentIOQueue.add(newProcess);
                }
                result.getIoQueueActivity().add(currentIOQueue);

                if (currentProcess.getCurrIOindex() >= currentProcess.getBurstTimes().length - 1) {
                    currentProcess.setExitTime(currentTime);
                } else {
                    int currentIOBurst = currentProcess.getBurstTimes()[currentProcess.getCurrIOindex()];
                    currentProcess.setReturnTime(currentTime + currentIOBurst);
                    currentProcess.setCurrCPUindex(currentProcess.getCurrCPUindex() + 2);
                    currentProcess.setCurrIOindex(currentProcess.getCurrIOindex() + 2);
                    ioQueue.add(currentProcess);
                }

            } else {
                int nextIOCompletionTime = Integer.MAX_VALUE;
                for (SProcess process : ioQueue) {
                    if (process.getReturnTime() < nextIOCompletionTime) {
                        nextIOCompletionTime = process.getReturnTime();
                    }
                }
                currentTime = nextIOCompletionTime;
            }
        }
        return result;
    }

    /**
     * Method to return the name of the scheduling algorithm
     *
     * @return Name of Scheduling algo
     */
    @Override
    public String getName() {
        return "First Come, First Serve Non-Preemptive";
    }

    public AlgoResult getResults(){
        return result;
    }
}

