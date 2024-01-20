import java.util.*;

public class RajusColang {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] script = new String[50];
        int scriptLines = 0;
        
        while (scanner.hasNextLine() && scriptLines < 50) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }
            script[scriptLines++] = line;
        }
       
        String[] variableNames=script[scriptLines-2].split(" ");
        int[] variableValues = new int[variableNames.length];
        String[] values=script[scriptLines-1].split(" ");
        for (int i = 0; i < values.length; i++) {
            variableValues[i] = Integer.parseInt(values[i]);
        }
       
        for(String line:script){
            System.out.println(line);
        }
        // Execute the script
        executeScript(script, scriptLines-2,variableNames, variableValues);

       
    }

    public static void executeScript(String[] script, int scriptLines, String[] variableNames, int[] variableValues) {
       
        Map<Boolean,String> map=new HashMap<>();
        map.put(true,"Yes");
        map.put(false,"No");
        String searchKey="is";
        for (int i=0;i<scriptLines;i++) {
            
            String[] tokens = script[i].split(" ");
            
            if (tokens[0].equals("is") && searchKey.equals("is")) {
                String variable1 = tokens[1];
                String operator = tokens[2];
                String variable2 = tokens[3];

                int value1 = getValue(variable1, variableNames, variableValues);
                int value2 = getValue(variable2, variableNames, variableValues);

                boolean condition=false;
                switch (operator) {
                    case "<":
                        condition = value1 < value2;
                        break;
                    case ">":
                        condition = value1 > value2;
                        break;
                    case "==":
                        condition = value1 == value2;
                        break;
                    case "!=":
                        condition = value1 != value2;
                        break;
                    default:
                        condition = false;  // Invalid operator
                }

                searchKey=map.get(condition);
                
            } 
            else if(tokens[0].equals("Yes") && searchKey.equals("Yes") && (i + 1) < scriptLines){
                String[] nextLine=script[i+1].split(" ");
                searchKey=nextLine[0];
            }
            else if(tokens[0].equals("No") && searchKey.equals("No") && (i + 1) < scriptLines){
                String[] nextLine=script[i+1].split(" ");
                searchKey=nextLine[0];
            }
            else if (tokens[0].equals("print") && searchKey.equals("print")) {
                
                int res=getValue(tokens[1], variableNames, variableValues);
                System.out.println(res);
                searchKey="si";
            } else if (tokens[0].equals("si") && searchKey.equals("si")) {
                searchKey="si";
            }
            
        }
    }

    public static int getValue(String variable, String[] variableNames, int[] variableValues) {
        for (int i = 0; i < variableNames.length; i++) {
            if (variableNames[i].equals(variable)) {
                return variableValues[i];
            }
        }
        return 0;  // Variable not found (should not happen in a valid script)
    }
}


