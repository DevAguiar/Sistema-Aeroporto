public class Runway {
    private int number;

    public Runway(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void operate(Airplane airplane, String type) {
        System.out.println("Pista " + number + " - " + type + ": " + airplane);
    }
}