import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class Main {
    /**
     * 该函数把doc文件中的内容提取出来
     * @param filePath 传入文件路径
     * @return 返回doc内容
     */
    public static String getPhoneNum(File filePath) {
        String text = "";

        String fileName = filePath.getName().toLowerCase();// 得到名字小写
        try {
            FileInputStream in = new FileInputStream(filePath);
            if (fileName.endsWith(".doc")) { // doc为后缀的

                WordExtractor extractor = new WordExtractor(in);
                text = extractor.getText();
            }
            if (fileName.endsWith(".docx")) { // docx为后缀的

                XWPFWordExtractor docx = new XWPFWordExtractor(new XWPFDocument(in));
                text = docx.getText();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return text;
    }

    public static void main(String[] args) throws Exception {
        File fa = new File("text1.doc");
        File fb = new File("text2.doc");
        //打印这两个doc内容
//        System.out.println(getPhoneNum(fa));
//        System.out.println(getPhoneNum(fb));
        String stringA=getPhoneNum(fa);
        String stringB=getPhoneNum(fb);
        ArrayList<Character> lista = new ArrayList<>();//存放a.doc中所有字符（不重复）
        ArrayList<Character> listb = new ArrayList<>();//存放b.doc中所有字符（不重复）
        ArrayList<Character> same = new ArrayList<>();//存放a和b中相同的字符
        ArrayList<Character> diffa = new ArrayList<>();//存放a中不同的字符
        ArrayList<Character> diffb = new ArrayList<>();//存放a中不同的字符
        HashMap<Character,Integer> map = new HashMap<>();//相同字符的频率
        //将a.doc中字符提取出来，排除相同字符
        for(int i=0;i<stringA.length();i++){
            char a = stringA.charAt(i);
            if(a=='，'||a=='、'||a=='。'||a=='；'||a=='-'||a==' '||a=='“'||a=='”'||a=='\r'||a=='\n'){
                continue;
            }else {
                if(lista.contains(a)){
                    continue;
                }else{

                    lista.add(a);
                }
            }

        }
        //将b.doc中字符提取出来，排除相同字符
        for(int i=0;i<stringB.length();i++){
            char a = stringB.charAt(i);
            //去掉标点符号
            if(a=='，'||a=='、'||a=='。'||a=='；'||a=='-'||a==' '||a=='“'||a=='”'||a=='\r'||a=='\n'){
                continue;
            }else {
                if(listb.contains(a)){
                    continue;
                }else {
                    listb.add(a);
                }
                }

        }
        //提取lista和listb中相同的字符
        for(int i=0;i<lista.size();i++){
            char a = lista.get(i);
            for(int j=0;j<listb.size();j++){
                char b = listb.get(j);
                if(a==b) {
                    same.add(a);
                }

            }
        }
        //打印相同字符
        System.out.print("两篇文档中相同的字数为:"+same.size());
        System.out.println();
        System.out.print("两篇文档中相同的字如下：");
        System.out.println();
        for(int i=0;i<same.size();i++){
            System.out.print(same.get(i)+" ");

        }
        System.out.println();
        //找出lista不同的字符
        for(int i=0;i<lista.size();i++){
            char a =lista.get(i);
            int index=0;
            for(int j=0;j<same.size();j++){
                if (a==same.get(j)){
                    index=1;
                    continue;
                }
            }
            if(index==0) {
                diffa.add(a);
            }
        }
        //找出listb不同的字符
        for(int i=0;i<listb.size();i++){
            char a =listb.get(i);
            int index=0;
            for(int j=0;j<same.size();j++){
                if (a==same.get(j)){
                    index=1;
                    break;

                }
            }
            if(index==0) {
                diffb.add(a);
            }
        }
        //打印a不同的字符
     
        System.out.println("文档text1中不同的字数为:"+diffa.size());
        System.out.print("文档text1中不同的字如下：");
        System.out.println();
        for(int i=0;i<diffa.size();i++){
            System.out.print(diffa.get(i)+" ");
        }
        //打印b不同的字符
        System.out.println();
        System.out.println("文档text2中不同的字数为:"+diffb.size());
        System.out.print("文档text2中不同的字如下：");
        System.out.println();
        for(int i=0;i<diffb.size();i++){
            System.out.print(diffb.get(i)+" ");
        }
        //把same中的内容转化为hashmap
        for(int i=0;i<same.size();i++){
            char a = same.get(i);
            map.put(a,0);
        }
        //统计listA相同字符的频率
        for(int i=0;i<stringA.length();i++){
            char a =stringA.charAt(i);
            if(map.containsKey(a)){
                int num=map.get(a);
                map.put(a,num+1);
            }
        }
        //统计listB相同字符的频率
        for(int i=0;i<stringB.length();i++){
            char a =stringB.charAt(i);
            if(map.containsKey(a)){
                int num=map.get(a);
                map.put(a,num+1);
            }
        }
        //排序
        List<Map.Entry<Character,Integer>> list = new ArrayList(map.entrySet());
        Collections.sort(list, (o1, o2) -> (o2.getValue() - o1.getValue()));
        //如果相同字符小于10
        System.out.println();
        System.out.println();
        if(same.size()>=10){
            System.out.println("前十个高频率的字如下：");
            for(int i=0;i<10;i++){
                System.out.println((i+1)+": "+list.get(i).getKey()+" ");
            }
        }else{
            System.out.println("前十个高频率的字如下：");
            for(int i=0;i< same.size();i++){
                System.out.println((i+1)+": "+list.get(i).getKey()+" ");
            }
        }
    }
}
