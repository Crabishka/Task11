import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logic {

    public String solutionWithoutRegex(String str) {
        int indexOfEnd;
        int indexOfStart;
        int indexOfEqualSign;

    /*
    нашли равно
    пошли вперед (вправо)
    нашли первый непробельный символ и добавили его в secondWord
    потом добавляем в secondWord символы до пробела
    находим плюс (не нашли - выходим)
    находим единицу и запомнили ее индекс (не нашли - выходим)
    пошли назад (влево)
    нашли первый непробельный символ и добавили его в firstWord
    потом добавляем в firstWord символы до пробела и запоминаем индекс
    реверсим firstWord
    сравниваем
    если да, то мы меняем с стартового индекса до конечного на firstWord++
    уменьшаем длину на indexOfEnd - indexOfStart + 3
    еще надо i по-умному уменьшить (не надо)
    как-то сложно
     */
        // починить тесты с равно
        StringBuilder string = new StringBuilder(str);
        int length = string.length();
        boolean flag;
        int a = 5;
        a = a++ + a;
        for (int i = 0; i < length; i++) {
            if (string.charAt(i) == '=') { // нашли равно
                indexOfEqualSign = i;
                flag = false;
                StringBuilder firstWord = new StringBuilder();
                StringBuilder secondWord = new StringBuilder();
                int j = i + 1;
                while (string.charAt(j) == ' ' && j < length) {          // пошли вперед (вправо) и нашли первый непробельный символ
                    j++;
                }
                while ((string.charAt(j) != ' ') && (string.charAt(j) != '+') && j < length) {          // потом добавляем в secondWord символы до пробела
                    secondWord.append(string.charAt(j));
                    j++;
                }
                while (string.charAt(j) == ' ' && j < length) {          // находим плюс (не нашли - выходим)
                    j++;
                }
                if (string.charAt(j) != '+') continue;
                j++;
                while (string.charAt(j) == ' ' && j < length) {          // находим единицу (не нашли - выходим)
                    j++;
                }
                if (string.charAt(j) != '1') continue;
                else indexOfEnd = j + 1;
                j++;
                while (j < length && string.charAt(j) == ' ') {          // идем дальше единицы, в поисках закрывающего знака
                    j++;
                } // правильнее бы сделать метод, в котором бы хранился массив "закрывающих выражений", но пока не было времени
                if (j == length || string.charAt(j) == '\n' || string.charAt(j) == ' ' || string.charAt(j) == ';' || string.charAt(j) == ')' || string.charAt(j) == ';' || string.charAt(j) == ',')
                    flag = true;
                if (j != length && !flag) continue;
                j = i;
                while (string.charAt(j) == ' ' || string.charAt(j) == '=' && j > 0) {          // идем назад (влево) и нашли первый непробельный символ
                    j--;
                }
                while (j >= 0 && !(string.charAt(j) == '\n' || string.charAt(j) == ' ' || string.charAt(j) == ';' || string.charAt(j) == '(' || string.charAt(j) == ',')) {          // потом добавляем в firstWord символы до пробела
                    if (string.charAt(j) != '=') firstWord.append(string.charAt(j));
                    j--;
                }
                indexOfStart = j + 1;
                firstWord.reverse();
                if (firstWord.toString().equals(secondWord.toString())) {
                    string.replace(indexOfStart, indexOfEnd, firstWord.toString() + "++");
                    length += indexOfStart - indexOfEnd + secondWord.length() + 2;
                    i = i - (indexOfEqualSign - firstWord.length());
                }
                firstWord.setLength(0);
                secondWord.setLength(0);
            }

        }
        return string.toString();
    }

    public String regexSolution(String str) { // решение с регуляркой
        int shift = 0;
        Pattern pattern = Pattern.compile("\\S+\\s*[=]\\s*\\S+\\s*[+]\\s*[1][\\s;\n]");
        StringBuilder string = new StringBuilder(str);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            string.replace(matcher.start() - shift, matcher.end() - shift - 1, fixTheString(str.substring(matcher.start(), matcher.end())));
            shift += matcher.end() - matcher.start() - 4;
        }
        return string.toString();
    }

    public static String fixTheString(String str) {  // строка обязательно должна быть "правильной"
        StringBuilder result = new StringBuilder();
        String[] parts = str.split("=");
        result.append(parts[0].trim() + "++");
        return result.toString();

    }
}
