class CountsByHealth {

    private int healthyCount; // Tracks the number of healthy batteries
    private int exchangeCount; // Tracks the number of batteries eligible for exchange
    private int failedCount; // Tracks the number of failed batteries

    public CountsByHealth() {
        this.healthyCount = 0; // Initialize counts to zero
        this.exchangeCount = 0;
        this.failedCount = 0;
    }

    public int getHealthyCount() {
        return healthyCount; // Retrieve the healthy battery count
    }

    public void setHealthyCount(int healthyCount) {
        this.healthyCount = healthyCount; // Set the healthy battery count
    }

    public int getExchangeCount() {
        return exchangeCount; // Retrieve the exchange battery count
    }

    public void setExchangeCount(int exchangeCount) {
        this.exchangeCount = exchangeCount; // Set the exchange battery count
    }

    public int getFailedCount() {
        return failedCount; // Retrieve the failed battery count
    }

    public void setFailedCount(int failedCount) {
        this.failedCount = failedCount; // Set the failed battery count
    }

    public void incrementHealthyCount() {
        healthyCount++; // Increment the healthy battery count by 1
    }

    public void incrementExchangeCount() {
        exchangeCount++; // Increment the exchange battery count by 1
    }

    public void incrementFailedCount() {
        failedCount++; // Increment the failed battery count by 1
    }
}

class BatteryHealthAnalyzer {

    private static final int RATED_CAPACITY = 120; // Defines the rated capacity of a new battery

    public static CountsByHealth countBatteriesByHealth(int[] presentCapacities) {
        CountsByHealth counts = new CountsByHealth(); // Create a new CountsByHealth object

        for (int presentCapacity : presentCapacities) { // Iterate through each battery's present capacity
            double stateOfHealth = (double) presentCapacity / RATED_CAPACITY * 100; // Calculate state of health (SoH)

            if (stateOfHealth > 80) { // Healthy battery (SoH > 80%)
                counts.incrementHealthyCount();
            } else if (stateOfHealth >= 62) { // Exchange battery (62% <= SoH <= 80%)
                counts.incrementExchangeCount();
            } else {
                counts.incrementFailedCount(); // Failed battery (SoH < 62%)
            }
        }

        return counts; // Return the updated CountsByHealth object
    }

    public static void displayBatteryHealthCounts(CountsByHealth counts) {
        System.out.println("Battery Health Counts:"); // Print header
        System.out.println("Healthy: " + counts.getHealthyCount()); // Print healthy battery count
        System.out.println("Exchange: " + counts.getExchangeCount()); // Print exchange battery count
        System.out.println("Failed: " + counts.getFailedCount()); // Print failed battery count
    }

    public static void testBucketingByHealth() {
        System.out.println("Counting batteries by SoH...\n"); // Print message

        // Test regular battery capacities
        int[] presentCapacities = {113, 116, 80, 95, 92, 70};
        CountsByHealth counts = countBatteriesByHealth(presentCapacities);
        displayBatteryHealthCounts(counts);

        // Boundary condition tests
        System.out.println("\nTesting boundary conditions:");

        // Test battery at rated capacity (SoH = 100%)
        int[] boundaryCapacities = {120};
        counts = countBatteriesByHealth(boundaryCapacities);
        displayBatteryHealthCounts(counts);

        // Test battery slightly below exchange threshold (SoH = 61.67%)
        boundaryCapacities = new int[]{61};
        counts = countBatteriesByHealth(boundaryCapacities);
        displayBatteryHealthCounts(counts);

        // Test failed battery (SoH = 0%)
        boundaryCapacities = new int[]{0};
        counts = countBatteriesByHealth(boundaryCapacities);
        displayBatteryHealthCounts(counts);

        System.out.println("\nDone counting :)\n");
    }

    public static void main(String[] args) {
        testBucketingByHealth(); // Run the test suite
    }
}
