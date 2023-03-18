package controller;

import model.Client;
import model.Queue;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Queue> queues;
    private int nrQueues;

    public Scheduler(int nrQueues) {
        this.nrQueues = nrQueues;
        queues = new ArrayList<Queue>();
        for (int i = 0; i < nrQueues; i++) {
            Queue queue = new Queue(i + 1);
            queues.add(queue);
            Thread thread = new Thread(queue);
            thread.start();
        }
    }

    public synchronized void dispatchClient(Client client) throws InterruptedException {
        int minWaitingPerion = queues.get(0).getWaitingPeriod().get();
        for (Queue queue : queues) {
            if (queue.getWaitingPeriod().get() < minWaitingPerion) {
                minWaitingPerion = queue.getWaitingPeriod().get();
            }
        }
        for (Queue queue : queues) {
            if (queue.getWaitingPeriod().get() == minWaitingPerion) {
                queue.setTotalWaitindTime(minWaitingPerion);
                queue.setTotalServiceTime(client.getServiceTime());
                queue.addClient(client);
                break;
            }
        }
    }

    public List<Queue> getQueues() {
        return queues;
    }
}
