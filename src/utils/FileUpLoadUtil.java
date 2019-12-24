package utils;

import bean.ArticleBean;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@WebServlet("/fileUpload")
public class FileUpLoadUtil extends HttpServlet {
    /**
     * 文件上传工具类
     * @param req
     * @return
     * @throws Exception
     */
    public ArticleBean fileUpLoad(HttpServletRequest req,String filePath,int type) throws Exception {
        ArticleBean articleBean = new ArticleBean();
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (!isMultipart) {
            throw new RuntimeException("请检查表单中是否包含enctype属性");
        }
        String name = "";
        DiskFileItemFactory dfif = new DiskFileItemFactory();
        ServletFileUpload parser = new ServletFileUpload(dfif);
        List<FileItem> items = parser.parseRequest(req);
        if (items != null) {
            for (FileItem fileItem : items) {
                /*
                 * fileItem.isFormField()判断当前是否为普通的表单字段
                 * 如果是普通的表单字段，返回true
                 * 是文件字段，返回false
                 */
                if (!fileItem.isFormField()) {
                    //getName() 返回文件名
                    String fileName = fileItem.getName();

                    //用户选择文件上传的时候
                    if (fileName != null && !fileName.equals("")) {
                        /*
                         * 包中的FilenameUtils是工具类
                         * FilenameUtils.getName(str)
                         * 表示从输入的路径中找到文件名（包括其后缀）
                         */
                        fileName = FilenameUtils.getName(fileName);

                        //创建文件目录
                        File fileDirectory = new File(filePath);
                        /*
                         * 如果文件目录不存在，则创建
                         */
                        if (!fileDirectory.exists()) {
                            //创建文件目录
                            fileDirectory.mkdirs();
                        }
                        //写入文件中，将fileItem中上传的文件写入file中
                        /*
                         * fileDirectory:文件的目录
                         * File.separator ：表示文件的分隔符“/”
                         * fileName ：文件名
                         */
                        fileItem.write(new File(fileDirectory + File.separator + fileName));
                        //删除处理文件上传中生成的临时文件夹
                        fileItem.delete();
                        //获取文件的相对访问路径
                        name = "img/" + fileName;
                    }
                }
            }

            //new一个fileObject对象
            articleBean = new ArticleBean();
            Time time=new Time();
            //为了每个单独属性设置值
            /*
             * getString(utf-8)获取普通表单中的value，并以utf-8的编码来获取值
             */
            articleBean.setTitle(items.get(0).getString("utf-8"));
            if(type==0){
            	articleBean.setId(Integer.parseInt(items.get(1).getString("utf-8")));
            	articleBean.setBody(items.get(4).getString("utf-8"));
            	if(name==" "||name.length()==0){
            		articleBean.setImage(items.get(2).getString("utf-8"));
            	}else{
            		articleBean.setImage(name);
            	}
            	
            }else{
            	articleBean.setBody(items.get(3).getString("utf-8"));
            	articleBean.setImage(name);
            }
            
            
            articleBean.setNum(0);
            articleBean.setDate(time.getDay());
        }
        return articleBean;
    }
}
