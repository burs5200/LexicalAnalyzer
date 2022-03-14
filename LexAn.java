import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class LexAn{
    static LexAn te; 
    public static HashMap<String, LexToken> syTable = new HashMap<>(); 
    public static HashMap<String, String> int2Lexeme = new HashMap<>(); 
    public static boolean skip = false;
    public static String returnChar = ""; 
    static int id= 1; 
    public static boolean dot = true;
    class LexToken{
        String value; 
        String token; 
        String attributes; 

        
        LexToken(String lexeme , String token , String attributes){
            LexToken temp = findEntry(lexeme); 
            if (temp != null ){
                this.value = temp.value;
                this.token = temp.token; 
                this.attributes = temp.attributes; 

            }else {
                if (token == "ID"){
                    this.value = "" + id; 
                }else {
                    this.value = lexeme; 
                }
                
                this.token = token;
                this.attributes = attributes;
                addValues(this, lexeme);

            }
        }
        @Override
        public String toString(){
            String formattedOutput = "<" + this.token;
            if (this.attributes.contains("Variable")  || this.attributes.contains("Constant") || this.attributes.contains("Operators")|| this.attributes.contains("ERROR")) {
                formattedOutput = formattedOutput +" , "+  this.value; 
            }
            return formattedOutput + ">"; 
        }
    }

    public static LexToken CreateError(String word, Scanner reader){
        String errorWord = word; 
        return  te.new LexToken(errorWord, "ERROR", "ERROR");
        /*
        while (reader.hasNext()){
            String value = reader.next();
            switch(value){
                case "0": 
                case "1": 
                case "2": 
                case "3": 
                case "4": 
                case "5": 
                case "6": 
                case "7": 
                case "8": 
                case "9": 
                case "a":
                case "b":
                case "c":
                case "d":
                case "e":
                case "f":
                case "g":
                case "h":
                case "i":
                case "j":
                case "k":
                case "l":
                case "m":
                case "n":
                case "o":
                case "p":
                case "q":
                case "r":
                case "s":
                case "t":
                case "u":
                case "v":
                case "w":
                case "x":
                case "y":
                case "z": 
                    errorWord = errorWord + value; 
                    break; 
                default: 
                    returnChar = value;
                    return te.new LexToken(errorWord, "ERROR", "ERROR"); 
            }
        }
        return null;*/

    }

    public static void addValues(LexToken tok , String lexeme){
        syTable.put(lexeme, tok); 
        int2Lexeme.put("" + id ,lexeme);
        id ++; 
    }

    public static String getLexeme(String ID){
        return int2Lexeme.get(ID);
    }
    public static LexToken findEntry(String lexeme){
        LexToken temp = syTable.get(lexeme); 
        return temp;  
    }

    public static LexToken isId(String firstChar, Scanner reader){
        String word = firstChar; 
        while (reader.hasNext()){
            String value = reader.next(); 
            switch(value.toLowerCase()){
                case "0": 
                case "1": 
                case "2": 
                case "3": 
                case "4": 
                case "5": 
                case "6": 
                case "7": 
                case "8": 
                case "9": 
                case "a":
                case "b":
                case "c":
                case "d":
                case "e":
                case "f":
                case "g":
                case "h":
                case "i":
                case "j":
                case "k":
                case "l":
                case "m":
                case "n":
                case "o":
                case "p":
                case "q":
                case "r":
                case "s":
                case "t":
                case "u":
                case "v":
                case "w":
                case "x":
                case "y":
                case "z": 
                    word = word + value; 
                    break; 
                case "<": 
                case ">":
                case "=":
                case "-":
                case "+":
                case "%":
                case "*":
                case "/":
                case "(": 
                case ")":
                case " ":
                case "\t":
                case "\n": 
                case ".": 
                case "\r": 
                case ",":
                case ";":    
                    returnChar = value; 
                    return te.new LexToken(word, "ID", "Variable");
                default : 
                    word = word + value; 
                    return CreateError(word, reader); 
            }


        }
        return null; 
    }

    public static LexToken IsRelop(String firstChar, Scanner reader){
        String newToken = firstChar; 
        String value; 
        if (firstChar.equals("<")){
            if (reader.hasNext()){
                value = reader.next(); 
            
                switch(value.toLowerCase()){
                    case "=":
                        newToken = newToken + value; 
                        return te.new LexToken(newToken,"LE", ""); 
                        
                    case ">":
                        newToken = newToken + value; 
                        return te.new LexToken(newToken,"NE", ""); 
                        
                    default: 
                        returnChar = value; 
                        return te.new LexToken(newToken,"LT", ""); 
                        
    
                }
            }else {
                return te.new LexToken(newToken,"LT", "");                     
            }

        }else if(firstChar.equals("=")){
            if (reader.hasNext()){
                value = reader.next();
                switch(value.toLowerCase()){
                    case "=":
                        newToken = newToken + value; 
                        return te.new LexToken(newToken,"EQ", ""); 
                    default: 
                        returnChar = value; 
                        return te.new LexToken(newToken,"ASSIGN", ""); 
                }
            }else{
                return te.new LexToken(newToken,"ASSIGN", ""); 
            }

        }else if (firstChar.equals(">")){    
            if (reader.hasNext()){
                value = reader.next(); 
                switch(value.toLowerCase()){
                    case "=":
                        newToken = newToken + value; 
                        return te.new LexToken(newToken,"GE", ""); 
                        
                    default : 
                        returnChar = value; 
                        return te.new LexToken(newToken,"GT", ""); 
                                          
                }
            }else {
                return te.new LexToken(newToken,"GT", ""); 
            }
        }
        return null;
    }
    public static LexToken IsInterger( String firstChar, Scanner reader){
        
        boolean makeInt = false; 
        boolean digit = true; 
        boolean skip = false; 
        String newVar = firstChar;
        String value = ""; 
        while(digit && reader.hasNext()){
            value = reader.next();
            switch(value){
                case "0": 
                case "1": 
                case "2": 
                case "3": 
                case "4": 
                case "5": 
                case "6": 
                case "7": 
                case "8": 
                case "9": 
                    newVar = newVar + value ; 
                    break; 
                case ".": 
                    newVar = newVar + value ; 
                    digit = false;
                    break; 
                case "E":
                case "e":  
                    newVar = newVar + value ;
                    skip = true ;
                    digit = false;
                    break;
                case " ": 
                case "\n": 
                case "\t":
                case "": 
                case "\r":
                case "-":
                case "+":
                case "%":
                case "*":
                case "/":    
                case ")":
                case ">":
                case "=":
                case "<":
                case ";":
                
                case ",": 
                    returnChar = value; 
                    return te.new LexToken(newVar, "Integer", "Constant"); 
                
                default : 
                    newVar = newVar + value; 
                    return CreateError(newVar, reader); 
            }
        }

        if ( !skip){
            if (reader.hasNext()){ 
               value = reader.next();
                switch(value){
                    case "0": 
                    case "1": 
                    case "2": 
                    case "3": 
                    case "4": 
                    case "5": 
                    case "6": 
                    case "7": 
                    case "8": 
                    case "9": 
                        newVar = newVar + value ;
                        break; 
                    default: 
                        newVar = newVar + value; 
                        return CreateError(newVar, reader); 

                
            }
                
                
            }else {
                return CreateError(newVar, reader); 
            }
            digit = true; 
            while(digit && reader.hasNext()){
                value = reader.next();
                switch(value){
                    case "0": 
                    case "1": 
                    case "2": 
                    case "3": 
                    case "4": 
                    case "5": 
                    case "6": 
                    case "7": 
                    case "8": 
                    case "9": 
                        newVar = newVar + value ; 
                        break; 
                    case "E": 
                    case "e": 
                        newVar = newVar + value ;
                        digit = false;    
                        break;
                        
                    case " ": 
                    case "\n": 
                    case "\t":
                    case "": 
                    case "\r":
                    case "-":
                    case "+":
                    case "%":
                    case "*":
                    case "/":    
                    case ")":
                    case ">":
                    case "=":
                    case "<": 
                    case ";": 
                    case ",":   
                        returnChar = value;
                        return te.new LexToken(newVar, "DOUBLE", "Constant"); 
                    
                    default : 
                        newVar = newVar + value; 
                        return CreateError(newVar, reader); 
                }
            }
                        
        }// E arrows going in at this point 
        if (reader.hasNext()){        
            value = reader.next(); 
            skip = false; 
            switch(value){
                case "0": 
                case "1": 
                case "2": 
                case "3": 
                case "4": 
                case "5": 
                case "6": 
                case "7": 
                case "8": 
                case "9": 
                    skip = true; 
                    newVar = newVar + value ;
                    break; 
                case "+":
                    makeInt = true; 
                case "-":
                    newVar = newVar + value ;
                    break;
    
                default : 
                    newVar = newVar + value; 
                    return CreateError(newVar, reader); 
            }
        
        }else {
            return CreateError(newVar, reader); 
        }
        if (!skip ){
            if(reader.hasNext()){
                            value = reader.next();
                switch(value){
                    case "0": 
                    case "1": 
                    case "2": 
                    case "3": 
                    case "4": 
                    case "5": 
                    case "6": 
                    case "7": 
                    case "8": 
                    case "9": 
                        newVar = newVar + value ;
                        break; 
    
                    default : 
                        newVar = newVar + value; 
                        return CreateError(newVar, reader); 
                }
            }else {
                return CreateError(newVar, reader);  
            }

        }
        while(reader.hasNext()){
            value = reader.next();
            switch(value){
                case "0": 
                case "1": 
                case "2": 
                case "3": 
                case "4": 
                case "5": 
                case "6": 
                case "7": 
                case "8": 
                case "9": 
                    newVar = newVar + value ; 
                    break; 
                    
                case " ": 
                case "\n": 
                case "\t":
                case "": 
                case "\r":
                case "-":
                case "+":
                case "%":
                case "*":
                case "/":    
                case ")":
                case ">":
                case "=":
                case "<":
                case ";":
                case ",": 
                    returnChar = value; 
                        return te.new LexToken(newVar, "DOUBLE", "Constant"); 
                    
                    
                
                default : 
                    newVar = newVar + value; 
                    return CreateError(newVar, reader); 
            }
        }

        return null; 

    }
    public static LexToken getNextToken(Scanner reader){
        LexToken token; 
         
        String value = ""; 
        if (returnChar != "" ){
            value = returnChar; 
            returnChar = "" ;
        }else {
            value = reader.next();
        }
        
        token = null;
        switch(value.toLowerCase()){
            case "0": 
            case "1": 
            case "2": 
            case "3": 
            case "4": 
            case "5": 
            case "6": 
            case "7": 
            case "8": 
            case "9": 
                token = IsInterger(value,reader);
                break; 
            case "a":
            case "b":
            case "c":
            case "d":
            case "e":
            case "f":
            case "g":
            case "h":
            case "i":
            case "j":
            case "k":
            case "l":
            case "m":
            case "n":
            case "o":
            case "p":
            case "q":
            case "r":
            case "s":
            case "t":
            case "u":
            case "v":
            case "w":
            case "x":
            case "y":
            case "z":
                token = isId(value, reader);
                break; 

            case "<": 
            case ">":
            case "=":
                token = IsRelop(value, reader);
                break; 
            case "-":
            case "+":
            case "%":
            case "*":
            case "/":
                token = te.new LexToken(value, "", "");
                break; 
            case "(":
                token = te.new LexToken("(", "", "");
                break; 
            case ")":
                token = te.new LexToken(")", "", "");
                break; 
            
            case " ":
                token = te.new LexToken(" ", "", "");
                break; 
            case "\t":
                token = te.new LexToken("\t", "", "");
                break; 
            case "\n": 
                token = te.new LexToken("\n", "", "");
                break; 
            case ".": 
                token = te.new LexToken(".", "", "");
                dot = false;
                break;
            case "\r": 
                skip = true; 
                break;
            case ",":
                token  = te.new LexToken(",","",""); 
                break; 
            case ";": 
                token = te.new LexToken(";", "", "");
                break;
            default:
                System.out.println("DEFAULT");
                te.new LexToken(value,"ERROR", "ERROR"); 
                break; 




        }
        return token;
    }

    public static void main(String[] args) {
        //ADD keywords to symbol table 
        te = new LexAn();
        te.new LexToken("def", "DEF", "Keyword");
        te.new LexToken("def", "DEF", "Keyword");
        te.new LexToken("fed", "FED", "Keyword");
        te.new LexToken("int", "INT", "Keyword");
        te.new LexToken("double", "DOUBLE", "Keyword");
        te.new LexToken("if", "IF", "Keyword");
        te.new LexToken("fi", "FI", "Keyword");
        te.new LexToken("then", "THEN", "Keyword");
        te.new LexToken("else", "ELSE", "Keyword");
        te.new LexToken("while", "WHILE", "Keyword");
        te.new LexToken("do", "DO", "Keyword");
        te.new LexToken("od", "OD", "Keyword");
        te.new LexToken("print", "PRINT", "Keyword");
        te.new LexToken("return", "RETURN", "Keyword");
        te.new LexToken("or", "OR", "Keyword");
        te.new LexToken("and", "AND", "Keyword");
        te.new LexToken("not", "NOT", "Keyword");

        //syntax
        te.new LexToken(";", "SEMICOLON", "Syntax");
        te.new LexToken(",", "COMMA", "Syntax");
        te.new LexToken(")", "RIGHTBRACK", "Syntax");
        te.new LexToken("(", "LEFTBRACK", "Syntax");
        te.new LexToken(".", "DOT", "Syntax");
        te.new LexToken("=", "ASSIGN", "Relop");
        //relop
        te.new LexToken("<", "LT", "Relop");
        te.new LexToken(">", "GT", "Relop");
        te.new LexToken("==", "EQ", "Relop");
        te.new LexToken("<=", "LE", "Relop");
        te.new LexToken(">=", "GE", "Relop");
        te.new LexToken("<>", "NE", "Relop");

        //Operations
        te.new LexToken("+", "Operator", "Operators");
        te.new LexToken("-", "Operator", "Operators");
        te.new LexToken("*", "Operator", "Operators");
        te.new LexToken("/", "Operator", "Operators");
        te.new LexToken("%", "Operator", "Operators");

        //white space 
        te.new LexToken("\t", "TAB", "White Space"); 
        te.new LexToken("\n", "NEWLINE", "White Space"); 
        te.new LexToken(" ", "SPACE", "White Space"); 
        // 
        //HTML Header
        String header = "<!DOCTYPE html>\n"
        +"<head>\n"
        +"</head>\n"
        +"<body>\n";
        System.out.println(header);
        
        ArrayList<LexToken> TokenList = new ArrayList<>(); 

        
        //html comment section
        System.out.println("<!--"); 
        Scanner reader = new Scanner(System.in);
        reader.useDelimiter("");
        LexToken token; 
          
        while ((reader.hasNext()|| returnChar != "")&& dot){
            token = getNextToken(reader); 
 
            if (!skip){
                TokenList.add(token);
                System.out.print(token);//change this to having it added to the LexToken String
            }else {
                skip = false; 
            }

        }
        System.out.println("\n-->");
        System.out.println("<p>"); 
        int i =0; 
        int n = TokenList.size(); 
        while (i < n){
             
            
            LexToken tempToken = TokenList.get(i); 
            String attribute = tempToken.attributes; 
            if (attribute.contains("White Space")){
                switch(tempToken.value){
                    case "\n": 
                        System.out.print("<br>"); 
                        break; 
                    case " ":
                        System.out.print(" "); 
                        break; 
                    case "\t": 
                        System.out.print("&emsp;"); 
                }

                
            }else {
                System.out.print("<font size ='5' color=");
                String attributes = tempToken.attributes;
                if (attributes.contains("ERROR")){
                    System.out.print("'red'");
                    System.out.print("><b"); 
                }else if (attributes.contains("Keyword") || attributes.contains("Operators")){
                    System.out.print("#9400D3");
                    
                }else if (attributes.contains("Variable")){
                    System.out.print("#54A9D7");
                    
                }else if (attributes.contains("Constant")){
                    System.out.print("#D68154");
                    
                }else if (attributes.contains("Relop")){
                    System.out.print("#AE2C80");
                    
                }
                System.out.print(">"); 
                //print value

                if (tempToken.token != "ID"){
                    System.out.printf("%s", tempToken.value );
                }else{
                    System.out.printf("%s", int2Lexeme.get(tempToken.value));
                }
                if (attributes.contains("ERROR")){
                    System.out.print("</b>"); 
                }
                System.out.print("</font>");
             }
             i++;

        }
        System.out.println("\n</p>"); 
        reader.close();
        //HTML Closing body
        System.out.println("</body>");
    }
}
