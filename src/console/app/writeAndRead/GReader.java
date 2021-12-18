package console.app.writeAndRead;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

///home/sergik/Downloads/CrudApplication

public class GReader {
    Gson gson = new Gson();
    private final static String pathToFile = "/src/files/";

    public static List<String> read(String file) {
        List<String> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(GetPath(file)),
                        StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static void write(String file, String list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(GetPath(file), true))) {
            bw.write(list);
            bw.newLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeList(String file, List<String> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(GetPath(file), false))) {
            for (String str : list) {
                bw.write(str);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String GetPath(String file) {
        Path currentPath = Paths.get("").toAbsolutePath();
        return currentPath + pathToFile + file;
    }
}

