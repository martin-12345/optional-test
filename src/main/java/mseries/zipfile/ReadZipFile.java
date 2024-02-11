package mseries.zipfile;

import java.io.*;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

  public class ReadZipFile{
        public static void main(String args[]){
           String url="https://dds.cr.usgs.gov/srtm/version2_1/SRTM3/Eurasia/N50E000.hgt.zip";
           byte[] content=readZipFileFromRemote(url);
           System.out.println(content.length);  
        }  

        public static byte[] readZipFileFromRemote(String remoteFileUrl) {        

           StringBuilder sb = new StringBuilder();
           byte[] bytes;
           ByteArrayOutputStream bufferedSink = new ByteArrayOutputStream(1024);
         try {
             URL url = new URL(remoteFileUrl);
             InputStream in = new BufferedInputStream(url.openStream(), 1024);
             ZipInputStream stream = new ZipInputStream(in);
             byte[] buffer = new byte[1024];
             ZipEntry entry;
             while ((entry = stream.getNextEntry()) != null) {
                  int read;
                 while ((read = stream.read(buffer, 0, 1024)) >= 0) {
                      //sb.append(new  String(buffer, 0, read));
                      bufferedSink.write(buffer, 0, read);
                }
            }
     } catch (Exception e) {
        e.printStackTrace();
     }
    return bufferedSink.toByteArray();
  }

}