package API.Serialization;

public class Car {
    String model;
    String make;
    int year;
    int price;

    /*
      public Pet(String name, String status,  int age){
        this.name=name;
        this.age = age;
        this.status = status;

     */
    public Car(String model, String make, int year,int price){
        this.model=model;
        this.make=make;
        this.year=year;
        this.price=price;
    }
    public void setModel(){
        this.model=model;

    }
    public String getModel(){
        return model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

