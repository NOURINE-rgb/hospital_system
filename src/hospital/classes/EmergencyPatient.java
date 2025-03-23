package hospital.classes;

public class EmergencyPatient extends patient{
	private String severityLevel;
    private double emergencyTreatmentCost;
	public EmergencyPatient(String name, int age, String gender, hospitalBlock location, String severityLevel, double emergencyTreatmentCost) {
		super(name, age, gender, location);
		this.severityLevel = severityLevel;
        this.emergencyTreatmentCost = emergencyTreatmentCost;
	}
	// setters and getters methods
	   public String getSeverityLevel() {
	        return severityLevel;
	    }

	    public void setSeverityLevel(String severityLevel) {
	        this.severityLevel = severityLevel;
	    }

	    public double getEmergencyTreatmentCost() {
	        return emergencyTreatmentCost;
	    }

	    public void setEmergencyTreatmentCost(double emergencyTreatmentCost) {
	        this.emergencyTreatmentCost = emergencyTreatmentCost;
	    }
	    
	    
	// Overridden methods
	@Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Severity Level: " + severityLevel);
        System.out.println("Emergency Treatment Cost: $" + emergencyTreatmentCost);
    }
	@Override
	public double calculateBill() {
		return emergencyTreatmentCost;
	}
   
	// public methods
	
}
