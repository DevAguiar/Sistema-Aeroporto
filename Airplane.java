public class Airplane {
    private int id;
    private int fuel;
    private int waitTime;

    public Airplane(int id, int fuel) {
        this.id = id;
        this.fuel = fuel;
        this.waitTime = 0;
    }

    public int getId() {
        return id;
    }

    public int getFuel() {
        return fuel;
    }

    public void consumeFuel() {
        fuel--;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void incrementWaitTime() {
        waitTime++;
    }

    @Override
    public String toString() {
        return "Aviao[ID=" + id + ", Comb=" + fuel + ", Espera=" + waitTime + "]";
    }
}