public  class CmdParams {  // создаем класс, который хранит параметры

     String inputFile;
     String outputFile;
     public boolean error = false; // ошибка выводится из функции parseArgs
     boolean help;
     public boolean waiting = false;
     boolean isItRegex = false;
   //  boolean window;
/*
ввод input и output file
если нет input - ждать ввода в консоль
если нет output - вывод в консоль
-regex
 */
    public static CmdParams parseArgs(String[] args) {
        CmdParams params = new CmdParams(); // создает объект, чтобы изменять параметры
        if (args[0].endsWith(".txt") && args[1].endsWith(".txt")){
            params.inputFile = args[0];
            params.outputFile = args[1];
            return params;
        }
        int ArgsLength = args.length;
        for (int i = 0; i < ArgsLength; i++) {
            if (args[i].equals("-regex")) params.isItRegex = true;
            if (args[i].equals("-h") || args[i].equals("--help")) params.help = true;
            if (args[i].equals("-o") || args[i].equals("--output")) {
                if (i + 1 > ArgsLength || !args[i + 1].endsWith(".txt")) {
                    params.error = true;
                }
                params.outputFile = args[i + 1];
            }
            if (args[i].equals("-i") || args[i].equals("--input")) {
                if (i + 1 == ArgsLength || !args[i + 1].endsWith(".txt")) {
                    params.error = true;
                }
                params.inputFile = args[i + 1];
            }


        }
        if (params.inputFile == null){
            params.waiting = true;
        }

        return params; // возвращаем объект с параметрами
    }

}