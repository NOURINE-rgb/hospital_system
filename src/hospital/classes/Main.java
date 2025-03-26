package hospital.classes;

public class Main {
	public static void main(String[] args) {
          Hospital hospital = new Hospital("Green valley hospital",500,"New York");
          hospitalBlock cardioBlock = new hospitalBlock("A", 1, "Cardiology");
          hospitalBlock pediatricBlock = new hospitalBlock("B", 2, "Pediatrics");
          hospitalBlock emergencyBlock = new hospitalBlock("E", 0, "Emergency");
          hospital.addBlock(emergencyBlock);
          hospital.addBlock(cardioBlock);
          hospital.addBlock(pediatricBlock);
          System.out.println(hospital.getHospitalInfo());
          hospital.displayBlocksInfo();
          patient inpatient = new Inpatient("Alice Smith", 50, "Female", cardioBlock, "A101", "2025-05-01", 300.0, 3);
          patient outpatient = new OutPatient("Bob Johnson", 35, "Male", pediatricBlock, "2025-05-02", 120.0);
          patient emergencyPatient = new EmergencyPatient("Chris Lee", 27, "Male", emergencyBlock, "High", 700.0);
          patient[] patients = {inpatient, outpatient, emergencyPatient};

          for (patient p : patients) {
              p.displayInfo();
              System.out.println("Bill: $" + p.calculateBill());
              System.out.println("---");
          }
    }
}

// and search by name