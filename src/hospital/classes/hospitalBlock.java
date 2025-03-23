package hospital.classes;

class hospitalBlock {
      private String blockName;
      private int floorNumber;
      private String speciality;
      public hospitalBlock(String blockName, int floorNumber,String speciality) {
    	  this.blockName = blockName;
    	  this.floorNumber = floorNumber;
    	  this.speciality = speciality;
      };
     // setters and getters methods
     public void setBlockName(String blockName) {
    	 this.blockName = blockName;
     }
     public String getBlockName() {
    	 return blockName;
     }
     public int getFloorNumber() {
         return floorNumber;
     }

     public void setFloorNumber(int floorNumber) {
         this.floorNumber = floorNumber;
     }

     public String getSpecialty() {
         return speciality;
     }

     public void setSpecialty(String specialty) {
         this.speciality = specialty;
     }
     
     
     // public methods
     public String getFullLocation() {
    	 return blockName + ", Floor " + floorNumber + ", " + speciality;
     }
     public String toString() {
    	 return "Block Name: " + blockName + ", Floor: " + floorNumber + ", Specialty: " + speciality;
     }
}
