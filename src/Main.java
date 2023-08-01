// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine());

        for (int i = 0; i < t; i++) {
            String[] nm = reader.readLine().split(" ");
            int n = Integer.parseInt(nm[0]);
            int m = Integer.parseInt(nm[1]);

            char[][] field = new char[n][m];
            for (int j = 0; j < n; j++) {
                String row = reader.readLine();
                field[j] = row.toCharArray();
            }

            // Перед началом игры блоки падают вниз
            for (int j = 0; j < m; j++) {
                for (int k = n - 2; k >= 0; k--) {
                    if (field[k][j] == '*') {
                        int l = k + 1;
                        while (l < n && field[l][j] != '*') {
                            field[l][j] = field[l - 1][j];
                            field[l - 1][j] = '.';
                            l++;
                        }
                    }
                }
            }

            // Функция, реализующая алгоритм потопа
            waterFilling(field);

            // Вывод финального состояния поля
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    System.out.print(field[j][k]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }


    public static void waterFilling(char[][] field) {
        int n = field.length;
        int m = field[0].length;

        // Инициализируем массив, который будет хранить текущую позицию воды
        int[][] water = new int[n][m];

        // Перебираем каждую клетку поля сверху вниз
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                // Если клетка - каменный блок, пропускаем её
                if (field[i][j] == '*') continue;

                // Если клетка выше - пустая клетка или другая вода
                // то текущая клетка заполняется водой
                if ((i > 0 && (field[i - 1][j] == '.' || field[i - 1][j] == '~')) ||
                        (i < n - 1 && (field[i + 1][j] == '.' || field[i + 1][j] == '~')) ||
                        (j > 0 && (field[i][j - 1] == '.' || field[i][j - 1] == '~'))) {
                    water[i][j] = 1;
                }

                // Если текущая клетка заполнена водой, вокруг неё также заполняются водой
                if (water[i][j] == 1) {
                    if (i > 0 && field[i - 1][j] == '.' && water[i - 1][j] == 0) {
                        water[i - 1][j] = 1;
                    }
                    if (i < n - 1 && field[i + 1][j] == '.' && water[i + 1][j] == 0) {
                        water[i + 1][j] = 1;
                    }
                    if (j > 0 && field[i][j - 1] == '.' && water[i][j - 1] == 0) {
                        water[i][j - 1] = 1;
                    }
                }
            }
        }

        // Обновляем поле с учётом заполнения водой
        for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++){
                    if (field[i][j] == '*') {
                        for(int k = m-1; k > j; k--){
                            if(field[i][k] == '*'){
                                do {
                                    if(field[i][k - 1] == '.'){
                                        field[i][k - 1] = '~';
                                    }
                                    k--;
                                }
                                while(k>j+1);

                            }
                        }

                    }
                }
        }
}}





