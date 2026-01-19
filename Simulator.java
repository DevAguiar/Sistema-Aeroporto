public class Simulator {
    private Airport airport;

    public Simulator() {
        airport = new Airport();
    }

    public void run(int timeUnits) {
        for (int t = 1; t <= timeUnits; t++) {
            System.out.println("\n=== TEMPO " + t + " ===");

            int landingQty = (int)(Math.random() * 4);
            int takeoffQty = (int)(Math.random() * 4);

            airport.arrivalLanding(landingQty);
            airport.arrivalTakeoff(takeoffQty);

            airport.operateRunways();
            airport.printQueues();
        }
    }
}