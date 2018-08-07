import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/*定义一个工具类，该工具类可实现copy功能（不允许使用Files类）。
如果被copy的对象是文件，程序将指定文件复制到指定目录下；
如果被copy的对象是目录，程序应该将该目录及其目录下的所有文件复制到指定目录下。*/
public class TestCopyDemo1 {
    public static void main(String[] args) throws IOException {
            String file=("D:\\jhZhang\\Desktop\\MarkdownPreview\\samples\\sample.md");
        String catalog=("D:\\jhZhang\\Desktop");
        copycatalog(file,catalog);
        System.out.println("复制成功");
    }
    /**
     * [copyfile description]
     * @param  file        输入文件
     * @param  catalog     输出文件夹
     * @throws IOException [description]
     */
    public static void copyfile(String file,String catalog) throws IOException{
        FileOutputStream fos=new FileOutputStream(catalog+"\\"+
                file.substring(file.lastIndexOf("\\")+1));
        FileInputStream fis=new FileInputStream(file);
        int Read=0;
        byte [] bytes=new byte[1*1024*1024];
        while((Read=fis.read(bytes))>0){
            fos.write(bytes,0,Read);
        }

    }
    /**
     * @param  file        输入文件（文件或者目录）路径
     * @param  catalog     输出文件夹路径
     * @throws IOException [description]
     */
    public static void copycatalog(String file,String catalog) throws IOException{
        File file1=new File(file);
        if(file1.isFile()){
            copyfile(file,catalog);
        }else{
            File file2=new File(catalog+"\\"+file1.getName());
            if(!file2.exists()){
                file2.mkdirs();
            }
            File [] newfile=file1.listFiles();
            for(File file3:newfile){
                if(file3.isFile()){
                    copyfile(file3.getAbsolutePath(),file2.getAbsolutePath());
                }else{
                    copycatalog(file3.getAbsolutePath(),file2.getAbsolutePath());
                }
            }
        }
    }
}
