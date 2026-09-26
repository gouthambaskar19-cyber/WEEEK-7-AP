public interface Insurable {
    String getInsuranceInfo();
}
abstract class ServiceableVehicle {
    private double mileage;

    public ServiceableVehicle() {
        this.mileage = 0.0;
    }

    public abstract String performMaintenance();

    public double getMileage() {
        return mileage;
    }

    public void addMileage(double km) {
        if (km < 0) {
            return; // Reject negative distance
        }
        this.mileage += km;
    }

    public static String getInsuranceIfApplicable(ServiceableVehicle v) {
        if (v instanceof Insurable) {
            Insurable insurableVehicle = (Insurable) v;
            return insurableVehicle.getInsuranceInfo();
        }
        return "No insurance record exists";
    }
}
class Forklift extends ServiceableVehicle implements Insurable {
    private final String assetTag;

    public Forklift(String assetTag) {
        super();
        this.assetTag = assetTag;
    }

    public String getAssetTag() {
        return assetTag;
    }

    @Override
    public String performMaintenance() {
        return "Forklift " + assetTag + ": hydraulic and fork inspection complete";
    }

    @Override
    public String getInsuranceInfo() {
        return "Insured under fleet policy Asset " + assetTag;
    }
}
class HeavyDutyForklift extends Forklift {

    public HeavyDutyForklift(String assetTag) {
        super(assetTag);
    }

    @Override
    public String performMaintenance() {
        return super.performMaintenance() + " | high-pressure hydraulic check complete";
    }
}