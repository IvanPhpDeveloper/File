package FileProcessor;


import java.io.*;
import java.nio.ByteBuffer;
import java.util.List;

public class FileCollect {
    private static File dest;
//    private final File dest;
 public FileCollect(File dest){
     this.dest=dest;
 }


    public File collect(List<File> allFiles) {
     try(OutputStream os=new BufferedOutputStream(new FileOutputStream(dest,true))) {
         for (File file : allFiles) {
                writeFileToFile(os,file);
         }
     } catch (FileNotFoundException e) {
         e.printStackTrace();
     } catch (IOException e) {
         throw new RuntimeException();
     }
        return dest;
    }

    private void writeFileToFile(OutputStream os,File file) throws IOException{
        String name=file.getName();
        byte[] fileNameLength=ByteBuffer.allocate(4).putInt(name.length()).array();
        byte [] fileName =name.getBytes();

        os.write(fileNameLength);
        os.write(fileName);

        try(BufferedInputStream is=new BufferedInputStream(new FileInputStream(file))){

            os.write(ByteBuffer.allocate(4).putInt(is.available()).array());//сколько доступно в файле информации
            byte [] content=new byte[256];
            while (is.available()>0) {
                is.read(content);
                os.write(content);
            }
        }
    }
}
