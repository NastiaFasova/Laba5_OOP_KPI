package homework;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class Smartphone extends Phone {
    private double processFrequency;
    private int coresNumber;
    private int RAMAmount;

    public Smartphone() {
    }

    public Smartphone(PhoneTitle title, double weight, double diagonal, long memorySize, boolean camera) {
        super(title, weight, diagonal, memorySize, camera);
    }

    @OwnAnnotation
    public Smartphone makePhoto() {
        System.out.println("The photo was done");
        return this;
    }
}
