import java.io.*;
import java.util.*;

class MicroDVD {

    void delay(final String in, final String out, int delay, int fps) throws Exception {
        BufferedReader fileReader = new BufferedReader(new FileReader(in));
        PrintWriter fileWriter = new PrintWriter(out);

        String textLine = fileReader.readLine();

        int addFrames = (int) (((double) delay * 0.001) * fps);

        int startFrameInt, endFrameInt, index, lineCounter = 1;

        char x;
        try {
            while (textLine != null) {
                index = 1;
                StringBuilder startFrameString = new StringBuilder();
                StringBuilder endFrameString = new StringBuilder();
                if (!textLine.matches("\\x7B(\\d+)\\x7D\\x7B(\\d+)\\x7D(.*)"))
                    throw new Exception("Incorrect format in line " + lineCounter + ": " + textLine);
                x = textLine.charAt(index);
                while (Character.isDigit(x)) {
                    startFrameString.append(x);
                    index++;
                    x = textLine.charAt(index);
                }
                index = index + 2;
                x = textLine.charAt(index);
                while (Character.isDigit(x)) {
                    endFrameString.append(x);
                    index++;
                    x = textLine.charAt(index);
                }
                startFrameInt = Integer.parseInt(startFrameString.toString());
                endFrameInt = Integer.parseInt(endFrameString.toString());
                if (startFrameInt > endFrameInt || startFrameInt <= 0)
                    throw new Exception("Incorrect frames in line " + lineCounter + ": " + textLine);
                fileWriter.println("{" + (startFrameInt + addFrames) + "}{" + (endFrameInt + addFrames) + "}" + textLine.substring(index + 1));
                textLine = fileReader.readLine();
                lineCounter++;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            fileReader.close();
            fileWriter.close();
        }
    }

}