package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;
    private int series;

    @OneToOne(mappedBy = "car")
    private User owner;

    public Car() {}

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public int getSeries() { return series; }
    public void setSeries(int series) { this.series = series; }
    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return series == car.series && model.equals(car.model);
    }

    @Override
    public int hashCode() {
        return 31 * model.hashCode() + series;
    }
}
