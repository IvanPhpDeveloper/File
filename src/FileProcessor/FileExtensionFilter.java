package FileProcessor;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class FileExtensionFilter implements  FileFilter {
    private final String DOT=".";
    private final List<String> extensions ;

    public FileExtensionFilter(String ... exts) {

        this.extensions= Arrays.stream(exts).
                map(String::toLowerCase).
                 collect(Collectors.toList());
    }
    public FileExtensionFilter(List<String> exts) {
        this.extensions= exts.stream().
                map(String::toLowerCase).
                collect(Collectors.toList());
    }



    @Override
    public boolean accept(File pathname) {
        if(pathname.isDirectory()){
            return true;
        }
        String name = pathname.getName();
        int dotIndex = name.lastIndexOf(DOT);
        if(dotIndex>=0){
               String ext=name.substring(dotIndex+1).toLowerCase();
               return  extensions.contains(ext);
        }else {
            return false;
        }
    }
}
