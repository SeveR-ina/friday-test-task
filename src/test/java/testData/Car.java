package testData;

import lombok.Builder;
import lombok.Getter;

/**
 * Test data class
 */
@Getter
@Builder
public class Car {

    private String brand;
    private String model;
    private String shape;
    private String fuel;
    private String ps;
    private String tsnHsn;
    private String regDate;

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", tsnHsn='" + tsnHsn + '\'' +
                '}';
    }
}
