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
     * �ļ��ϴ�������
     * @param req
     * @return
     * @throws Exception
     */
    public ArticleBean fileUpLoad(HttpServletRequest req,String filePath,int type) throws Exception {
        ArticleBean articleBean = new ArticleBean();
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (!isMultipart) {
            throw new RuntimeException("��������Ƿ����enctype����");
        }
        String name = "";
        DiskFileItemFactory dfif = new DiskFileItemFactory();
        ServletFileUpload parser = new ServletFileUpload(dfif);
        List<FileItem> items = parser.parseRequest(req);
        if (items != null) {
            for (FileItem fileItem : items) {
                /*
                 * fileItem.isFormField()�жϵ�ǰ�Ƿ�Ϊ��ͨ�ı��ֶ�
                 * �������ͨ�ı��ֶΣ�����true
                 * ���ļ��ֶΣ�����false
                 */
                if (!fileItem.isFormField()) {
                    //getName() �����ļ���
                    String fileName = fileItem.getName();

                    //�û�ѡ���ļ��ϴ���ʱ��
                    if (fileName != null && !fileName.equals("")) {
                        /*
                         * ���е�FilenameUtils�ǹ�����
                         * FilenameUtils.getName(str)
                         * ��ʾ�������·�����ҵ��ļ������������׺��
                         */
                        fileName = FilenameUtils.getName(fileName);

                        //�����ļ�Ŀ¼
                        File fileDirectory = new File(filePath);
                        /*
                         * ����ļ�Ŀ¼�����ڣ��򴴽�
                         */
                        if (!fileDirectory.exists()) {
                            //�����ļ�Ŀ¼
                            fileDirectory.mkdirs();
                        }
                        //д���ļ��У���fileItem���ϴ����ļ�д��file��
                        /*
                         * fileDirectory:�ļ���Ŀ¼
                         * File.separator ����ʾ�ļ��ķָ�����/��
                         * fileName ���ļ���
                         */
                        fileItem.write(new File(fileDirectory + File.separator + fileName));
                        //ɾ�������ļ��ϴ������ɵ���ʱ�ļ���
                        fileItem.delete();
                        //��ȡ�ļ�����Է���·��
                        name = "img/" + fileName;
                    }
                }
            }

            //newһ��fileObject����
            articleBean = new ArticleBean();
            Time time=new Time();
            //Ϊ��ÿ��������������ֵ
            /*
             * getString(utf-8)��ȡ��ͨ���е�value������utf-8�ı�������ȡֵ
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
