package hospital.classes;

public class OutPatient extends patient{
    private String appointmentDate;
    private double consultationFee;
	public OutPatient(String name, int age, String gender, hospitalBlock location,String appointmentDate,double consultationFee) {
		super(name, age, gender, location);
		this.appointmentDate = appointmentDate;
		this.consultationFee = consultationFee;
	}
	// setters and getters methods
	public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }
	
	
    // Overridden methods
	@Override
	    public void displayInfo() {
	        super.displayInfo();
	        System.out.println("Appointment Date: " + appointmentDate);
	        System.out.println("Consultation Fee: $" + consultationFee);
	    }
	@Override
	public double calculateBill() {
		return consultationFee;
	}

}
