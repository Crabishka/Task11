import java.util.Scanner;


public class Main {

    /*
    ввод input и output file
    если нет input - ждать ввода в консоль
    если нет output - вывод в консоль
     */

    public static void main(String[] Args) throws Exception {
        Logic logic = new Logic();
        Scanner scanner = new Scanner(System.in);
        String oneLineString = "";
        if (Args.length == 0 ){
            oneLineString = scanner.nextLine();
            System.out.println(logic.solutionWithoutRegex(oneLineString));
            System.exit(1);
        }
        CmdParams params = CmdParams.parseArgs(Args);
        if (params.error) {
            System.out.print("Error in Input/Output stream");
        }
        if (params.help) {
            System.out.println("Usage:");
            System.out.println("Fast execute <input-file> <output-file>");
            System.out.println();
            System.out.println("--input <input-file>// add Input file");
            System.out.println("-i <input-file>");
            System.out.println();
            System.out.println("--output <output-file> // add Output file");
            System.out.println("-o <output-file>");
            System.out.println();
            System.out.println("If there are not any input-file");
            System.out.println("It will be expected in console");
            System.out.println("If there are not any output-file");
            System.out.println("Solution will be in console");
            System.out.println();
        }
        if (params.waiting){
            System.out.println("Enter you one-line string");
            oneLineString = scanner.nextLine();
        } // можно была сделать с else, но
        if (params.outputFile == null && !params.waiting){
            if (params.isItRegex) System.out.println(logic.regexSolution(FileFunction.readStringFromFile(params.inputFile)));
            else System.out.println(logic.solutionWithoutRegex(FileFunction.readStringFromFile(params.inputFile)));
        }
        else if (params.outputFile == null){
            if (params.isItRegex) System.out.println(logic.regexSolution(oneLineString));
            else System.out.println(logic.solutionWithoutRegex(oneLineString));
        }
        else if (!params.waiting){
            if (params.isItRegex) FileFunction.writeTextIntoFile(params.outputFile, logic.regexSolution(FileFunction.readStringFromFile(params.inputFile)));
            else FileFunction.writeTextIntoFile(params.outputFile, logic.solutionWithoutRegex(FileFunction.readStringFromFile(params.inputFile)));
        }
        else {
            if (params.isItRegex) FileFunction.writeTextIntoFile(params.outputFile, logic.regexSolution(oneLineString));
            else FileFunction.writeTextIntoFile(params.outputFile, logic.solutionWithoutRegex(oneLineString));
        }




    }

}


// работающие решение, но оно с регулярными выражениями


//2313 xjh x = x + 1 eorn kqx = ek nc y = y + 1 nfewj yr 1 few 2 32h c =c + 1
