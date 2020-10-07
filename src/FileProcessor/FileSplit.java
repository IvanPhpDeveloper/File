package FileProcessor;

import java.io.*;
import java.nio.ByteBuffer;

public class FileSplit {

    private static File source;

    public FileSplit(File source) {
 this.source=source;
    }

    public void split(File deskDirectory) {
        if(deskDirectory.isFile()){
            throw new IllegalArgumentException("Destination is not a directory");
        }
        try(InputStream is=new BufferedInputStream(new FileInputStream(source))){
           while (is.available()>0){
               byte[] fileNameLength = is.readNBytes(4);
               int nameLength=ByteBuffer.wrap(fileNameLength).getInt();

               String fileName=new String(is.readNBytes(nameLength));

               int contentLength=ByteBuffer.wrap(is.readNBytes(4)).getInt();
//               byte [] content=is.readNBytes(contentLength);

               try(BufferedOutputStream os=new BufferedOutputStream(
                       new FileOutputStream(
                               deskDirectory.getAbsolutePath()+fileName))){
                   byte [] buffer=new byte[256];
                   while (contentLength>0) {
                       is.read(buffer);
                       os.write(buffer);
                       contentLength-=buffer.length;
                   }
               }

           }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
