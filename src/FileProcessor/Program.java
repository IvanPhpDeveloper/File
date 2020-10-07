package FileProcessor;

import java.io.File;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        List<File> allFiles= FileFinder.getAllFiles(new File(args[0]),3);
//        if (args.length < 0) {
//            System.err.println("Not enough arguments received.");
//             }


        FileCollect fileCollect=new FileCollect(new File("C:\\Users\\vanok"));
        File file=fileCollect.collect(allFiles);
        System.out.println(allFiles.size() );
        for(File f:allFiles){
            System.out.println(f.getName());
        }
        System.out.println("----------");
        System.out.println(file);



        FileSplit splitter=new FileSplit(new File("./result.bin"));
        splitter.split(new File(""));


    }
}
