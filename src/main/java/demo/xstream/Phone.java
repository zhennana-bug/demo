package demo.xstream;

public class Phone {

    private String brand;

    private String colour;

    public Phone(String brand, String colour) {
        this.brand = brand;
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "brand='" + brand + '\'' +
                ", colour='" + colour + '\'' +
                '}';
    }
}
