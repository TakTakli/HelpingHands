package utils;

public class Medicine {
    private int id;
    private String type;
    private String name;
    private double dosageAmount;
    private String dosageUnit;
    private String time;
    private String status;

    public Medicine(int id, String type, String name, double dosageAmount, String dosageUnit, String time, String status) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.dosageAmount = dosageAmount;
        this.dosageUnit = dosageUnit;
        this.time = time;
        this.status = status;
    }

    // Getters
    public int getId() { return id; }
    public String getType() { return type; }
    public String getName() { return name; }
    public double getDosageAmount() { return dosageAmount; }
    public String getDosageUnit() { return dosageUnit; }
    public String getTime() { return time; }
    public String getStatus() { return status; }
}