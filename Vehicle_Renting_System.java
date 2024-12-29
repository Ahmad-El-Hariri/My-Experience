import java.util.ArrayList;
public class Vehicle_Renting_System {

    public static void main(String[] args) {

        car c1 = new car("C001", "Toyota", 50, "Diesel");
        car c2 = new car("C002", "BMW", 40, "Gasoline");
        bike b1 = new bike("BK001", "Yamaha", 20, false);
        boat bo = new boat("BT001", "Sea Ray", 100, 40);

        ArrayList<vehicle> vehicles = new ArrayList<vehicle>();

        rentalservice r1 = new rentalservice(vehicles);

        r1.addvehicle(b1);
        r1.addvehicle(bo);
        r1.addvehicle(c1);
        r1.addvehicle(c2);

        r1.getVehicles().get(0).displayVehicleDetails();
        r1.getVehicles().get(1).displayVehicleDetails();
        r1.getVehicles().get(2).displayVehicleDetails();
        r1.getVehicles().get(3).displayVehicleDetails();

        r1.getVehicles().get(0).rentVehicle(8);
        r1.getVehicles().get(1).rentVehicle(4);
        r1.getVehicles().get(2).rentVehicle(8);

        r1.getVehicles().get(0).rentVehicle(1);
        r1.getVehicles().get(0).returnVehicle();

    }
}

abstract class vehicle implements rentable {

    private String vehicleId;
    private String brand;
    private double rentalPricePerDay;
    private boolean isRented = false;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public void setRentalPricePerDay(double rentalPricePerDay) {
        this.rentalPricePerDay = rentalPricePerDay;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean isRented) {
        this.isRented = isRented;
    }

    public vehicle(String vehicleId, String brand, double rentalPricePerDay) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.rentalPricePerDay = rentalPricePerDay;
    }

    public void rentVehicle(int numOfDays) {
        if (!isRented()) {
            System.out
                    .println("The vehicle  " + getVehicleId() + " is now rented for " + getRentalPricePerDay() + "$.");
        } else {
            System.out.println("You cannot rent the vehicle " + getVehicleId());
        }
    }

    public void returnVehicle() {

        if (isRented()) {
            setRented(false);
            System.out.println("The vehicle " + getVehicleId() + " is now available.");
        } else {
            System.out.println("The vehicle " + getVehicleId() + " is available already.");
        }
    }

    public double calculateRentalCost(int numberOfDays) {

        return getRentalPricePerDay() * numberOfDays;

    }

    public abstract void displayVehicleDetails();

}

class car extends vehicle {

    private String feultype;
    static int count = 1;

    public String getFeultype() {
        return feultype;
    }

    public void setFeultype(String feultype) {
        this.feultype = feultype;
    }

    public car(String vehicleId, String brand, double rentalPricePerDay, String feultype) {
        super(vehicleId, brand, rentalPricePerDay);
        this.feultype = feultype;
    }

    public void displayVehicleDetails() {
        System.out.println(
                "Car" + ((count > 1) ? "" : count) + " details: ID: " + getVehicleId() + " Brand: " + getBrand()
                        + " Feultype: " + getFeultype() +
                        " RentalPricePerDay: " + getRentalPricePerDay());
        count++;
    }

}

class bike extends vehicle {

    private boolean isElectric;
    static int count = 1;

    public boolean isElectric() {
        return isElectric;
    }

    public void setElectric(boolean isElectric) {
        this.isElectric = isElectric;
    }

    public bike(String vehicleId, String brand, double rentalPricePerDay, boolean isElectric) {
        super(vehicleId, brand, rentalPricePerDay);
        this.isElectric = isElectric;
    }

    public void displayVehicleDetails() {
        System.out.println("bike" + ((count > 1) ? "" : count) + " details: ID: " + getVehicleId() + " Brand: "
                + getBrand() + "Is electric: " + isElectric() +
                " RentalPricePerDay: " + getRentalPricePerDay());
        count++;
    }

}

class boat extends vehicle {

    private double maxspeed;
    static int count = 1;

    public double getMaxspeed() {
        return maxspeed;
    }

    public void setMaxspeed(double maxspeed) {
        this.maxspeed = maxspeed;
    }

    public boat(String vehicleId, String brand, double rentalPricePerDay, double maxspeed) {
        super(vehicleId, brand, rentalPricePerDay);
        this.maxspeed = maxspeed;
    }

    public void displayVehicleDetails() {
        System.out.println("boat" + ((count > 1) ? "" : count) + " details: ID: " + getVehicleId() + " Brand: "
                + getBrand() + " Max speed: " + getMaxspeed() +
                " RentalPricePerDay: " + getRentalPricePerDay());
        count++;
    }

}

class rentalservice {

    private ArrayList<vehicle> vehicles = new ArrayList<vehicle>();

    public rentalservice(ArrayList<vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void addvehicle(vehicle v) {
        vehicles.add(v);
    }

    public void rent(String id, int numOfDays) {
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getVehicleId().equalsIgnoreCase(id)) {
                vehicles.get(i).setRented(true);
            }
            if (i == vehicles.size() - 1) {
                System.out.println("Vehicle of id: " + id + "is not found.");
            }
        }
        if (numOfDays > 7) {

            for (int i = 0; i < vehicles.size(); i++) {
                if (vehicles.get(i).getVehicleId().equalsIgnoreCase(id)) {
                    vehicles.get(i).setRentalPricePerDay(vehicles.get(i).getRentalPricePerDay() * 0.7);
                }
            }

        }
    }

    public void Return(String id) {

        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getVehicleId().equalsIgnoreCase(id)) {
                vehicles.get(i).setRented(false);
            }
        }
    }

    public ArrayList<vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList<vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
