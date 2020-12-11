package homework;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class ReflectionRunner {
    public static void main(String[] args) {
        Class reflected = Smartphone.class;
        System.out.println("SubClass name: " + reflected.getName());
        Method[] methods = reflected.getMethods();
        System.out.println("SubClass methods: ");
        for (Method method: methods) {
            System.out.println("\nMethod name: " + method.getName());
            System.out.print("Return type: " + method.getReturnType());
            int modifiers = method.getModifiers();
            System.out.println("Modifier: " + Modifier.toString(modifiers));
            Class[] parameterType = method.getParameterTypes();
            System.out.print("Parameters: ");
            for (Class parameter : parameterType) {
                System.out.print(parameter.getName() + " ");
            }
            System.out.println();
        }
        Class reflectedSuperclass = reflected.getSuperclass();
        System.out.println("\nSubclass extends the class: " + reflectedSuperclass.getName());
        Constructor baseConstructor = null;
        try {
            baseConstructor = reflectedSuperclass.getConstructor(PhoneTitle.class, double.class, double.class,
                    long.class, boolean.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Phone phone = null;
        try {
            assert baseConstructor != null;
            phone = (Phone) baseConstructor.newInstance(PhoneTitle.NOKIA, 500, 4.5, 350, true);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // За допомогою рефлексії обійдемо методи класу і
        // викличемо відмічені анотацією методи за допомогою invoke().
        methods = reflectedSuperclass.getDeclaredMethods();
        for(Method method: methods) {
            Annotation annotation = method.getAnnotation(OwnAnnotation.class);
            if (annotation != null && annotation.annotationType() == OwnAnnotation.class) {
                Class<?>[] parameterType  = method.getParameterTypes();
                Object[] params = new Object[parameterType.length];
                try {
                    method.invoke(phone, params);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        Class [] interfaces = reflectedSuperclass.getInterfaces();
        System.out.println("\nBaseClass interfaces: ");
        for(Class interfaceOdReflected: interfaces) {
            System.out.println(interfaceOdReflected.getName());
        }
    }
}
