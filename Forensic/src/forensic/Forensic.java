package forensic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class Forensic {
    public static void main(String[] args) throws IOException{
        File fileOrDir = new File("C:\\Users\\user\\Documents\\NetBeansProjects\\my-first-project\\Forensic\\testforensic");
        Forensic myFiles = new Forensic();
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập từ khóa tìm kiếm: ");
        String search = sc.nextLine();
        myFiles.traverseDepthFiles(fileOrDir, search);
    }
     
    public void traverseDepthFiles(final File fileOrDir, String search) throws FileNotFoundException, IOException {
        // check xem fileOrDir là file hay foder
        if (fileOrDir.isDirectory()) 
        {
            final File[] children = fileOrDir.listFiles();
            if (children == null) {
                return;
            }
            // sắp xếp file theo thứ tự tăng dần
            Arrays.sort(children, new Comparator<File>() {
                public int compare(final File o1, final File o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
            for (final File each : children) {
                // gọi lại hàm traverseDepthFiles()
                traverseDepthFiles(each, search);
            }
        } 
        else 
        {
            Reader r = new FileReader(fileOrDir.getAbsolutePath());
            int i = -1;
            String s = "";
            while((i = r.read()) != -1)
            {
                s += (char)i;
            }
            if (s.contains(search))
            {
                // in đường dẫn file ra màn hình
                System.out.println(fileOrDir.getAbsolutePath()); 
            }
            r.close();
        }
    }
}
