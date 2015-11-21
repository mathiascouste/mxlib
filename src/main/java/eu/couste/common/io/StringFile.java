package eu.couste.common.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Class to serialize and deserialize a file
 * 
 * @version 0.1
 */
public class StringFile {

    private String filename;

    /**
     * Constructor
     * 
     * @param filename file name
     */
    public StringFile(String filename) {
        this.filename = filename;
    }

    /**
     * Returns the file content into a string
     * 
     * @return a string of the file content
     * @throws IOException
     */
    public String read() throws IOException {
        StringBuilder sb = new StringBuilder();
        List<String> lines = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader("ressources/" + this.filename));
        String line = br.readLine();

        while (line != null) {
            lines.add(line);
            line = br.readLine();
        }
        for (int i = 0; i < lines.size(); i++) {
            sb.append(lines.get(i));
            if (i != lines.size() - 1) {
                sb.append(System.lineSeparator());
            }
        }
        br.close();

        return sb.toString();
    }

    /**
     * Overwrites the file
     * 
     * @param text text to put in the file
     * @throws IOException
     */
    public void write(String text) throws IOException {
        this.write(text, false);
    }

    /**
     * Writes or appends the string in a file
     * 
     * @param text text to add in the file
     * @param append boolean, true if need append
     * @throws IOException
     */
    public void write(String text, boolean append) throws IOException {
        BufferedWriter bw = new BufferedWriter(
                new FileWriter("ressources/" + this.filename, append));
        bw.write(text);
        bw.close();
    }

    public String getFilename() {
        return filename;
    }

}
