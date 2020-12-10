package homework;

import lombok.Data;

@Data
public class Phone implements Callable<Phone>, Messagable<Phone>{
    private PhoneTitle title;
    private double weight;
    private double diagonal;
    private long memorySize;
    private boolean camera;

    public Phone() {
    }

    public Phone(PhoneTitle title, double weight, double diagonal, long memorySize, boolean camera) {
        this.title = title;
        this.weight = weight;
        this.diagonal = diagonal;
        this.memorySize = memorySize;
        this.camera = camera;
    }

    @OwnAnnotation
    public Phone writeMessage(String message) {
        System.out.println("The owner wrote the message: " + message);
        return this;
    }

    public Phone call() {
        System.out.println("The owner called ");
        return this;
    }
}