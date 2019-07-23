package Lesson3;

import java.util.*;

public class MassWord {

    public static void main(String[] args) {

        String catsNames[] = {
                "Васька", "Кузя", "Барсик", "Мурзик", "Леопольд", "Бегемот", "Рыжик", "Матроскин",
                "Васька", "Кузя", "Барсик", "Мурзик", "Васька", "Кузя", "Барсик", "Мурзик",
                "Леопольд", "Бегемот", "Рыжик", "Васька", "Кузя", "Барсик", "Мурзик", "Леопольд",
                "Бегемот", "Рыжик", "Матроскин", "Васька", "Кузя", "Барсик", "Васька", "Кузя", "Барсик",
                "Мурзик", "Леопольд", "Бегемот", "Рыжик", "Матроскин", "Васька", "Кузя", "Барсик",
                "Мурзик", "Васька", "Кузя", "Барсик", "Мурзик", "Леопольд"};

        Set<String> unicName = new TreeSet<String>();   // Выводим уникальные значения
        unicName.addAll(Arrays.asList(catsNames));
        System.out.println(unicName);

        Map<String, Integer> unic = new HashMap<>();        //Считаем количества каждого слова в массиве

        for (int i = 0; i < catsNames.length; i++){
            Integer res = unic.get(catsNames[i]);
            unic.put(catsNames[i], res == null ? 1 : res + 1);
        }

        System.out.println(unic);
    }
}
