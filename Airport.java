public class Airport {
    private Queue[] landingQueues;
    private Queue[] takeoffQueues;
    private Runway[] runways;

    private int nextLandingId = 1;
    private int nextTakeoffId = 2;

    public Airport() {
        landingQueues = new Queue[4];
        for (int i = 0; i < 4; i++)
            landingQueues[i] = new Queue();

        takeoffQueues = new Queue[3];
        for (int i = 0; i < 3; i++)
            takeoffQueues[i] = new Queue();

        runways = new Runway[3];
        for (int i = 0; i < 3; i++)
            runways[i] = new Runway(i + 1);
    }

    private Queue smallestQueue(Queue[] queues) {
        Queue smallest = queues[0];
        for (Queue q : queues) {
            if (q.size() < smallest.size())
                smallest = q;
        }
        return smallest;
    }

    public void arrivalLanding(int qty) {
        for (int i = 0; i < qty; i++) {
            int fuel = 1 + (int) (Math.random() * 20);
            Airplane airplane = new Airplane(nextLandingId, fuel);
            nextLandingId += 2;
            smallestQueue(landingQueues).enqueue(airplane);
        }
    }

    public void arrivalTakeoff(int qty) {
        for (int i = 0; i < qty; i++) {
            Airplane airplane = new Airplane(nextTakeoffId, -1);
            nextTakeoffId += 2;
            smallestQueue(takeoffQueues).enqueue(airplane);
        }
    }

    public void operateRunways() {
        Airplane[] emergencies = new Airplane[3];
        int emergencyCount = 0;

        for (Queue queue : landingQueues) {
            if (!queue.isEmpty()) {
                Airplane first = queue.peek();
                if (first.getFuel() <= 0 && emergencyCount < 3) {
                    emergencies[emergencyCount] = queue.dequeue();
                    emergencyCount++;
                }
            }
        }

        int runwayIndex = 0;
        for (int i = 0; i < emergencyCount; i++) {
            runways[runwayIndex].operate(emergencies[i], "Pouso de emergência");
            runwayIndex++;
        }


        for (int i = runwayIndex; i < 3; i++) {
            if (i == 2) {
                // Pista 3 -  decolagem
                Queue takeoffQueue = largestQueue(takeoffQueues);
                if (!takeoffQueue.isEmpty()) {
                    runways[i].operate(takeoffQueue.dequeue(), "Decolagem");
                }
            } else {
                // Pista 1 e 2 - flex
                Queue landingQueue = largestQueue(landingQueues);
                if (!landingQueue.isEmpty()) {
                    runways[i].operate(landingQueue.dequeue(), "Pouso");
                } else {
                    Queue takeoffQueue = largestQueue(takeoffQueues);
                    if (!takeoffQueue.isEmpty()) {
                        runways[i].operate(takeoffQueue.dequeue(), "Decolagem");
                    }
                }
            }
        }

        for (Queue q : landingQueues) {
            Node current = getHead(q);
            while (current != null) {
                current.airplane.consumeFuel();
                current.airplane.incrementWaitTime();
                current = current.next;
            }
        }
        for (Queue q : takeoffQueues) {
            Node current = getHead(q);
            while (current != null) {
                current.airplane.incrementWaitTime();
                current = current.next;
            }
        }
    }

    private Queue largestQueue(Queue[] queues) {
        Queue largest = queues[0];
        for (Queue q : queues) {
            if (q.size() > largest.size())
                largest = q;
        }
        return largest;
    }

    private Node getHead(Queue queue) {
        try {
            java.lang.reflect.Field headField = Queue.class.getDeclaredField("head");
            headField.setAccessible(true);
            return (Node) headField.get(queue);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void printQueues() {
        System.out.println("=== FILAS DE ATERRISSAGEM ===");
        for (int i = 0; i < landingQueues.length; i++)
            System.out.println("Fila A" + (i + 1) + ": " + landingQueues[i]);
        System.out.println("=== FILAS DE DECOLAGEM ===");
        for (int i = 0; i < takeoffQueues.length; i++)
            System.out.println("Fila D" + (i + 1) + ": " + takeoffQueues[i]);
    }
}