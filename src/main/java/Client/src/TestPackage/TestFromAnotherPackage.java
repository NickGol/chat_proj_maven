package TestPackage;

import TestPackageMain.Test;

import java.util.UUID;

public class TestFromAnotherPackage extends Test {

    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        System.out.println( uuid.toString() );
        Test test = new Test();
        test.metod2();

        new Test().metod2();

        TestFromAnotherPackage testFromAnotherPackage = new TestFromAnotherPackage();
        testFromAnotherPackage.metod1();

    }





    @Override
    public void metod1() {
        System.out.println("New metod1");
        super.metod1();
    }

    private void newMetod() {
            super.metod1();
    }


}
