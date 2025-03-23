package hospital.classes;

public class Inpatient extends patient{
     private String roomNumber;
     private String admissionDate;
     private double dailyCharge;
     private int numberOfDaysAdmitted;
     public Inpatient(String name, int age, String gender, hospitalBlock location, String roomNumber, String admissionDate, double dailyCharge, int numberOfDaysAdmitted) {
         super(name, age, gender, location);
         this.roomNumber = roomNumber;
         this.admissionDate = admissionDate;
         this.dailyCharge = dailyCharge;
         this.numberOfDaysAdmitted = numberOfDaysAdmitted;
     }
     // setters and getters methods
     public String getRoomNumber() {
         return roomNumber;
     }

     public void setRoomNumber(String roomNumber) {
         this.roomNumber = roomNumber;
     }

     public String getAdmissionDate() {
         return admissionDate;
     }

     public void setAdmissionDate(String admissionDate) {
         this.admissionDate = admissionDate;
     }

     public double getDailyCharge() {
         return dailyCharge;
     }

     public void setDailyCharge(double dailyCharge) {
         this.dailyCharge = dailyCharge;
     }

     public int getNumberOfDaysAdmitted() {
         return numberOfDaysAdmitted;
     }

     public void setNumberOfDaysAdmitted(int numberOfDaysAdmitted) {
         this.numberOfDaysAdmitted = numberOfDaysAdmitted;
     }
    // Overridden methods
    @Override
    public void displayInfo() {
    	super.displayInfo();
    	System.out.println("Room Number: " + roomNumber);
        System.out.println("Admission Date: " + admissionDate);
        System.out.println("Daily Charge: $" + dailyCharge);
        System.out.println("Number of Days Admitted: " + numberOfDaysAdmitted);
    }
	@Override
	public double calculateBill() {
		return dailyCharge * numberOfDaysAdmitted;
	}
	
	
	// public methods
}
