package log;

import java.io.*;

public class Log {

    public void save(String login, String text) {
        File file = new File( "hw/hw1/server/src/log/" + login + ".csv");
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(text);

            System.out.println("LOG updated");
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String download(String login){

        StringBuilder history = new StringBuilder("");
        try {
            FileReader file = new FileReader("hw/hw1/server/src/log/" + login + ".csv");
            BufferedReader br = new BufferedReader(file);
            String line;
            while ((line = br.readLine()) != null){
                history.append(line).append("\n");
            }

        } catch (IOException e) {
            return "";
        }
        return String.valueOf(history);
    }
}
