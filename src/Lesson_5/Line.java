package Lesson_5;

import java.util.ArrayList;

public class Line {

    static final int l = 4;                 // задаем число потоков
    static final int size = 100000;
    static final int h = size / l;
    static float[] arr = new float[size];

     static void oneLine() {            //метод для одного главного потока

        for (int i = 0; i < size; i++){
            arr[i] = 1;
        }

        long a = System.currentTimeMillis();

        for (int i = 0; i < size; i++){
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        System.out.println("Время работы одного потока: " + (System.currentTimeMillis() - a) + "мс");

         for(int i = 0; i < size; i++){         // выводим содерживое цикла для сравнения результата
             System.out.println(arr[i]);
         }
    }

    static void manyLine() {            // метод для многотопочности

        ArrayList<float[]> allArr = new ArrayList<float[]>();       //определяем массив в который потом сложим мини-массивы после разделение гоавного массива

        for (int i = 0; i < size; i++){     // заполняем массив
            arr[i] = 1;
        }

        long t = System.currentTimeMillis();

        for (int i = 0; i < l; i++){        //цикл для разбики главного массива на мини-массивы и дабовление их в массив-массиввов
            float a1[] = new float[h];
            System.arraycopy(arr,(h * i), a1, 0, h);
            allArr.add(a1);
        }

        Thread myThread[] = new Thread[l];
        for (int j = 0; j < l; j++){            // задаем потоки по количеству массивов
            Task e = new Task(j, allArr);   // передаем в класс Task, где проходят вычисления, массив-массивоа и номер мини-массива с которым будем работать
            myThread[j] = new Thread(e);
            myThread[j].start();
        }

        for (int j = 0; j < l; j++) {       //ждем пока все вычисления завершатся для всех потоков прежде чем склеивать массив
            try {
                myThread[j].join();
                } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < l; i++){            // склевиаем мини-массивы в один массив
            System.arraycopy(allArr.get(i),0, arr, (i * h), h);
        }

        System.out.println("Время работы " + l + " потоков: " + (System.currentTimeMillis() - t) + "мс");

        for(int i = 0; i < size; i++){       // выводим содерживое цикла для сравнения результата
            System.out.println(arr[i]);
        }
    }

     static class Task implements Runnable{     //класс для вычеслений

         private final int j;
         private final ArrayList<float[]> allArr;

         public Task(int j, ArrayList<float[]> arrayList){
             this.j = j;
             this.allArr = arrayList;
         }
        @Override
        public void run() {
            for (int i = 0; i < h; i++){        // вычесление с откорректированой формулой
                allArr.get(j)[i] = (float)(allArr.get(j)[i] * Math.sin(0.2f + (i + (h * j)) / 5) * Math.cos(0.2f + (i + (h * j)) / 5) * Math.cos(0.4f + (i + (h * j)) / 2));
            }
            allArr.set(j, arr);     // запичываем обработтаный массив в массив-массивов
        }
    }
}
