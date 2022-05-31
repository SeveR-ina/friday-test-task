package testData;

import lombok.Builder;
import lombok.Getter;

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
}
