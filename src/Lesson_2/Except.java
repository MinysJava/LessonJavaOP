package Lesson_2;

public class Except {
    static int row = 4;     // число строк в массиве
    static int cell = 4;    // число столбцов в массиве
    static int arrSize = 4; // размерность массива
    static String[][] arr = new String[row][cell];

    public static void main(String[] args) {
        try {
            summArryWhithException(arr);
        } catch (MyArraySizeException e) {      // ловим ошибку на рамер массива
            e.printStackTrace();
        }
    }

    private static void summArryWhithException(String arr[][]) throws MyArraySizeException {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < cell; j++) {
                if (i == 2 && j == 3){
                    arr[i][j] = "f";    // Если тут символ "f" заменить на число то массив получиться из чисел
                }else {
                    arr[i][j] = "1";
                }
            }
        }
        int result = 0;
        if (row == arrSize && cell == arrSize){     //условие на размерность массива
            for (int i = 0; i < row; i++){
                for (int j = 0; j < cell; j++){
                   try {
                       result = result + Integer.parseInt(arr[i][j]);      //ловим исключение на тип данных
                   } catch (NumberFormatException e) {
                       throw new MyArrayDataException(("Возникла ошибка в ячейке " + i + "x" + j),i, j);
                   }
                }
            }
            System.out.println("Сумма элементов в массиве равна: " + result);
        } else {
            throw new MyArraySizeException("Размер массива должен быть " + arrSize + "x" + arrSize); // если условие не вырно выводим исключение
        }
    }

    private static class MyArraySizeException extends Throwable {   // исключение на размерностьмассива
        public MyArraySizeException(String s) {
            System.out.println(s);
        }
    }

    private static class MyArrayDataException extends NumberFormatException{    //исключение на тип данных
        private int i,j;

        public MyArrayDataException( String msg, int i, int j) {
            super(msg);
            this.i = i;
            this.j = j;
        }
    }
}
