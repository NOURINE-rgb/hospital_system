package hospital.classes;

public abstract class patient {
       protected String name;
       protected String gender;
       protected int age;
       protected hospitalBlock location;
       protected patient(String name, int age, String gender,hospitalBlock location) {
    	   this.name = name;
    	   this.gender = gender;
    	   this.age = age;
    	   this.location = location;
       }
       // setters and getters methods
       public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }

       public int getAge() {
           return age;
       }

       public void setAge(int age) {
           this.age = age;
       }

       public String getGender() {
           return gender;
       }

       public void setGender(String gender) {
           this.gender = gender;
       }

       public hospitalBlock getLocation() {
           return location;
       }

       public void setLocation(hospitalBlock location) {
           this.location = location;
       }

       
       
    //public methods
    public void displayInfo() {
    	System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("Location: " + location.getFullLocation());
    };
    public abstract double calculateBill();
}
