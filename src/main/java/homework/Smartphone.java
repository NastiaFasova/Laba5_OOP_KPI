package homework;

import lombok.ToString;

@ToString
public class Smartphone extends Phone {
    private double processFrequency;
    private int coresNumber;
    private int RAMAmount;

    public Smartphone() {
    }

    public Smartphone(PhoneTitle title, double weight, double diagonal, long memorySize, boolean camera) {
        super(title, weight, diagonal, memorySize, camera);
    }

    public Smartphone(PhoneTitle title, double weight,
                      double diagonal, long memorySize,
                      boolean camera, double processFrequency,
                      int coresNumber, int RAMAmount) {
        super(title, weight, diagonal, memorySize, camera);
        this.processFrequency = processFrequency;
        this.coresNumber = coresNumber;
        this.RAMAmount = RAMAmount;
    }

    @OwnAnnotation
    public Smartphone makePhoto() {
        System.out.println("The photo was done");
        return this;
    }
}
