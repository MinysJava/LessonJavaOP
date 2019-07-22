package Lesson3;

public class MainPhone {
    public static void main(String[] args) {
        Phonebook a = new Phonebook();

        a.getPBook();                                                //выводим телефонную книгу

        System.out.println();
        System.out.println("-------------------------------");
        System.out.println();

        a.getPhoneNumber("Ivanov");                          //выводим телефоны по фамилии
        a.getPhoneNumber("Sidorov");

        a.getPBook();

        System.out.println();
        System.out.println("-------------------------------");
        System.out.println();

        a.addPhoneNumber("Andreev", "7777");        //добавляем новый контакт
        a.addPhoneNumber("Ivanov", "131313");       // добавляем новый номертелефона к существующему контакту

       a.getPBook();
    }
}
