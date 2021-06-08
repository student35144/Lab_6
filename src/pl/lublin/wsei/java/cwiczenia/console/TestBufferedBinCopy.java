package pl.lublin.wsei.java.cwiczenia.console;

import java.io.*;

public class TestBufferedBinCopy {
    public static void main(String[] args) throws IOException {

        BufferedInputStream in = null;
        BufferedOutputStream out = null;

        long startTime = System.nanoTime();

        try {
            in = new BufferedInputStream(new FileInputStream("sample_*1920x1280.tiff"));
            out = new BufferedOutputStream(new FileOutputStream("img_copy.tiff"));
            int c;

            while ((c = in.read()) != -1) {
                out.write(c);
            }
        }
        catch (IOException exception) {
            System.out.println("IOException: " + exception.getMessage());
            exception.printStackTrace();
        }
        finally {
            if (in != null)
                in.close();

            if (out != null)
                out.close();
        }

        long endTime = System.nanoTime();
        long timeElapsed = endTime-startTime;

        System.out.println("Czas wykonania w nanosekundach : " + timeElapsed);
        System.out.println("Czas wykonania w milisekundach : " + timeElapsed / 1000000);
    }
}
