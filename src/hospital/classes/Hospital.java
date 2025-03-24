package hospital.classes;

import java.util.ArrayList;
import java.util.List;

class Hospital {
  private String name;
  private int maxPatients;
  private String state;
  private List<hospitalBlock> blocks = new ArrayList<>();
  public Hospital(String name,int maxPatient,String state) {
	  this.name = name;
      this.maxPatients = maxPatient;
      this.state = state;
}
  //setters and getters methods
  public String getName() {
      return name;
  }

  public void setName(String name) {
      this.name = name;
  }

  public int getMaxPatients() {
      return maxPatients;
  }

  public void setMaxPatients(int maxPatients) {
      this.maxPatients = maxPatients;
  }

  public String getState() {
      return state;
  }

  public void setState(String state) {
      this.state = state;
  }
  public void addBlock(hospitalBlock block) {
	  blocks.add(block);
  }
  public List<hospitalBlock> getBlocks(){
	  return blocks;
  }
  
  
  
  // public methods
  public void displayBlocksInfo() {
	  for(hospitalBlock block: blocks) {
		  System.out.println(block);
	  }
  }
  public String  getHospitalInfo(){
	  return "Hospital Name: " + name + ", Max Patients: " + maxPatients + ", State: " + state;
  }
}
