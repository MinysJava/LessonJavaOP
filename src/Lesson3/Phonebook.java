package Lesson3;

import java.util.*;

public class Phonebook {
       HashMap<String, HashSet> pBook = new HashMap<>();

    public Phonebook() {                        //создаем порвоначальную телефонную книгу
        pBook.put("Ivanov", new HashSet<>());
        pBook.get("Ivanov").add("1111");
        pBook.get("Ivanov").add("2222");

        pBook.put("Petrov",new HashSet<>());
        pBook.get("Petrov").add("3333");
        pBook.get("Petrov").add("4444");
        pBook.get("Petrov").add("5555");

        pBook.put("Sidorov",new HashSet<>());
        pBook.get("Sidorov").add("6666");
        }

    public void getPhoneNumber(String fam){                 // метод для вывода номера по фамилии(ключу)
        System.out.println(fam + ": " + pBook.get(fam));
    }

    public void addPhoneNumber (String fam, String phoneNumber){    //Метод добавления номена в телефонную книгу
        if (!pBook.containsKey(fam)){                               // Проверяем есть такая фамилия в телефонной книге
            pBook.put(fam, new HashSet<>());                        // если нет то создаем новую запись и кладем туда номер телефона
            pBook.get(fam).add(phoneNumber);
        }else {
            pBook.get(fam).add(phoneNumber);                        // если запись есть то просто добавляем ей новый номер телефона
        }
    }

    public void getPBook(){                 // метод выводит всю телефонную книгу
        System.out.println(pBook);
    }
}
