package FileProcessor;


import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class FileFinder {
    private static List<String> ACCEPTED_EXTECTIONS=new ArrayList<>();
    private static  FileFilter FILTER;
    static{
        ACCEPTED_EXTECTIONS.add("png");
        ACCEPTED_EXTECTIONS.add("jpg");
        FILTER=new FileExtensionFilter();
    }


    static List<File> getAllFiles(File root, int fileCount){
        List<File> acceptedFiles=new ArrayList<>();


        File[] files = root.listFiles(FILTER);
        files=files==null?new File[0]:files;



        for(File file:files){
            if(acceptedFiles.size()>=fileCount){
                return acceptedFiles;
            }
            if(root.isDirectory()){
        acceptedFiles.addAll(getAllFiles(file,fileCount-acceptedFiles.size()));//
            }else{
            acceptedFiles.add(file);
            }

        }

        return acceptedFiles;
    }
}
