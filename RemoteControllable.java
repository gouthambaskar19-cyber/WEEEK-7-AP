public interface RemoteControllable {
    String connect(String appId);
}

interface EnergyTrackable {
    double getConsumptionWatts();
}

abstract class HomeDevice {
    private static int counter = 1000;
    private final String serialNumber;

    public HomeDevice() {
        counter++;
        this.serialNumber = "HD-" + counter;
    }

    public abstract String activate();

    public String getSerialNumber() {
        return serialNumber;
    }

    public static void connectAll(RemoteControllable[] items, String appId) {
        if (items != null) {
            for (RemoteControllable item : items) {
                if (item != null) {
                    System.out.println(item.connect(appId));
                }
            }
        }
    }

    public static double getConsumptionIfTrackable(HomeDevice d) {
        if (d instanceof EnergyTrackable) {
            EnergyTrackable trackable = (EnergyTrackable) d;
            return trackable.getConsumptionWatts();
        }
        return 0.0;
    }
}

class WashingMachine extends HomeDevice implements RemoteControllable, EnergyTrackable {
    private final double consumptionWatts;

    public WashingMachine(double consumptionWatts) {
        super();
        if (consumptionWatts <= 0) {
            throw new IllegalArgumentException("Consumption must be positive.");
        }
        this.consumptionWatts = consumptionWatts;
    }

    @Override
    public String activate() {
        return "Washing machine " + getSerialNumber() + " started a cycle";
    }

    @Override
    public String connect(String appId) {
        return getSerialNumber() + " connected to " + appId;
    }

    @Override
    public double getConsumptionWatts() {
        return consumptionWatts;
    }
}

class Refrigerator extends HomeDevice implements EnergyTrackable {
    private final double consumptionWatts;

    public Refrigerator(double consumptionWatts) {
        super();
        if (consumptionWatts <= 0) {
            throw new IllegalArgumentException("Consumption must be positive.");
        }
        this.consumptionWatts = consumptionWatts;
    }

    @Override
    public String activate() {
        return "Refrigerator " + getSerialNumber() + " cooling activated";
    }

    @Override
    public double getConsumptionWatts() {
        return consumptionWatts;
    }
}

class MobileApp implements RemoteControllable {
    private final String appName;

    public MobileApp(String appName) {
        if (appName == null || appName.trim().isEmpty()) {
            throw new IllegalArgumentException("App name cannot be blank.");
        }
        this.appName = appName;
    }

    @Override
    public String connect(String appId) {
        return appName + " connected to " + appId;
    }
}