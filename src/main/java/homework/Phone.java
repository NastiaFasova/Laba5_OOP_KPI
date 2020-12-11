package homework;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Phone implements Callable<Phone>, Messagable<Phone>{
    private PhoneTitle title;
    private double weight;
    private double diagonal;
    private long memorySize;
    private boolean camera;

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