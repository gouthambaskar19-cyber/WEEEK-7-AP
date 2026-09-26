interface Exportable {
    String exportData();
}
class ExportTracker {
    private static int totalExports = 0;

    public static synchronized void incrementExportCount() {
        totalExports++;
    }

    public static int getTotalExports() {
        return totalExports;
    }
}
class ReportGenerator implements Exportable {
    private final String reportName;

    public ReportGenerator(String reportName) {
        if (reportName == null || reportName.trim().isEmpty()) {
            throw new IllegalArgumentException("Report name cannot be blank.");
        }
        this.reportName = reportName;
    }

    @Override
    public String exportData() {
        ExportTracker.incrementExportCount();
        return "Exported report: " + reportName;
    }

    public static int getTotalExports() {
        return ExportTracker.getTotalExports();
    }

    public static void exportAll(Exportable[] items) {
        if (items != null) {
            for (Exportable item : items) {
                if (item != null) {
                    System.out.println(item.exportData());
                }
            }
        }
    }
}
class UserProfile implements Exportable {
    private final String username;

    public UserProfile(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be blank.");
        }
        this.username = username;
    }

    @Override
    public String exportData() {
        ExportTracker.incrementExportCount();
        return "Exported profile: " + username;
    }
}