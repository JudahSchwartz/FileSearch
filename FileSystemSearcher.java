


import java.io.File;
import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.util.*;

public class FileSystemSearcher {
    public static void main(String[] args) {
        System.out.println("What is the name of the file you're looking for?");
        String fileName = new Scanner(System.in).nextLine();
        File depthSearch = findFileDepthSearch(new File("C:/"), fileName);
        String message = depthSearch==null?"Couldnt find file":depthSearch.getPath();
        System.out.println(message);
        File breadthSearch = findFileBreadthSearch(new File("C:/"),fileName);
        String message2 = breadthSearch==null?"Couldnt find file":breadthSearch.getPath();
        System.out.println(message2);
    }

    private static File findFileBreadthSearch(File dir,String fileName) {
        File[] files = dir.listFiles();
        List<File> currentGen = Arrays.asList( files);

        int i = 0;//10 is probably too many subfolders to check
        while(i++ < 10) {
            for (File f : currentGen) {
                if (f.getName().equalsIgnoreCase(fileName)) {
                    return f;
                }
            }
            ArrayList<File> nextGen = new ArrayList<>();
            for (File f : currentGen) {
                if (f.isDirectory()) {
                    File[] f2 = f.listFiles();
                    if( f2!= null) {
                        nextGen.addAll(Arrays.asList(f2));
                    }
                }
            }
            currentGen = nextGen;

        }
        return null;
    }

    //Throwing null pointer exception when access is denied to the directory i think.
    private static File findFileDepthSearch(File dir, String fileName) {
        File[] files = dir.listFiles();
        if(files==null)
        {
            return null;
        }
        for (File f : files) {
            if (f.getName().equalsIgnoreCase(fileName)) {
                return f;
            }
        }
        for (File f : files) {
            if (f.isDirectory()) {
                File res = findFileDepthSearch(f, fileName);
                if(res != null)
                {
                    return res;
                }
            }
        }
        return null;
    }
}
/*
public static void main(String... args) {
    File[] files = new File("C:/").listFiles();
    showFiles(files);
}

public static void showFiles(File[] files) {
    for (File file : files) {
        if (file.isDirectory()) {
            System.out.println("Directory: " + file.getName());
            showFiles(file.listFiles()); // Calls same method again.
        } else {
            System.out.println("File: " + file.getName());
        }
    }
}
 */