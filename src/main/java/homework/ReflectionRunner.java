package homework;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class ReflectionRunner {
    public static void main(String[] args) {
        Class reflected = Smartphone.class;
        System.out.println("SubClass name: " + reflected.getName());
        int classModifiers = reflected.getModifiers();
        System.out.println(Modifier.isPublic(classModifiers));
        Method[] methods = reflected.getMethods();
        System.out.println("\nSubClass methods:");
        //Список методів зі специфікаторами доступу і типами параметрів
        for(Method method: methods){
            System.out.print("Method " + method.getName());
            System.out.print(" Return Type: " + method.getReturnType());
            Class[] parameterType = method.getParameterTypes();
            System.out.print(" Parameters: ");
            for(Class parameter : parameterType){
                System.out.print(parameter.getName()+ " ");
            }
            System.out.println();
        }
        //Назва суперкласу
        Class reflectedSuperclass = reflected.getSuperclass();
        System.out.println("\nSubclass extends the class: " + reflectedSuperclass.getName());

        // Ініціалізуємо і викликаємо конструктор
        Constructor baseConstructor = null;
        try {
            baseConstructor = reflectedSuperclass.getConstructor(PhoneTitle.class, double.class, double.class,
                    long.class, boolean.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Phone phone = null;
        try {
            phone = (Phone) baseConstructor.newInstance(PhoneTitle.NOKIA, 500, 4.5, 350, true);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // За допомогою рефлексії обійдемо методи класу і
        // викличемо відмічені анотацією методи за допомогою invoke().
        methods = reflectedSuperclass.getDeclaredMethods();
        for(Method method: methods){
            Annotation annotation = method.getAnnotation(OwnAnnotation.class);
            if (annotation != null && annotation.annotationType() == OwnAnnotation.class){
                Class<?>[] pType  = method.getParameterTypes();
                Object[] params = new Object[pType.length];
                for (int i = 0; i < pType.length; i++) {
                    if(pType[i].toString().equals("String")){
                        params[i] = 0;
                    }
                }
                try {
                    method.invoke(phone, params);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        Class [] interfaces = reflectedSuperclass.getInterfaces();
        System.out.println("\nBaseClass interfaces:");
        for(Class interface_: interfaces){
            System.out.println(interface_.getName());
        }
    }
}
