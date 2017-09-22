import java.util.ArrayList;

/**
 * Created by Wes Groover on 9/21/2017.
 */
public class IOHandler {
    IOHandler(){}

    public String printFunction(ArrayList<String> s){
        String javaLine = "System.out.println(";
        for(int i = 2; i < s.size(); ++i){
            if(s.get(i).charAt(0) == '"'){
                javaLine += s.get(i);
            }
        }
        javaLine += " World!\");";
        return javaLine;
    }
}
