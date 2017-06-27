//dengjintao
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class CountMostImport{
    String path;//�ļ�·��
    HashMap<String, Integer> count_Map;//ͳ�ƹ����У�������Ϊkey����import����Ϊvalue

    public  CountMostImport(String dir){
        this.path = dir;
        count_Map = new HashMap<String, Integer>();    
    }

    //get()�������Ƿ��ض�Ӧ������import����
    public int get(String class_Name){
        Integer value = count_Map.get(class_Name);
        if(value==null) {
        	return 0;
        }else{
            return value;	
        }
    }
    
    //process�������ļ�����ͳ��import�������
    public void process(File file){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = reader.readLine()) != null){
                line = line.trim();//ȥ�ո�
                if(line.startsWith("public")||line.startsWith("class")){//�������Java���벿��ֱ�Ӻ��ԣ���Ϊ���벿��û����import��������
                    break;
                }
                if(line.startsWith("import")){//ͳ��import�����map��
                    String className = line.substring(6, line.length()-1).trim();
                    Integer value = count_Map.get(className);
                    if(value==null){
                        count_Map.put(className, 1);
                    }else{
                        count_Map.put(className, value+1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
         catch (IOException e) {
            e.printStackTrace();
        }
    }

    //�ж�·�����ļ��л����ļ���������ļ�ֱ����process����ͳ�ƣ�������ļ��н��еݹ�
    public void if_It_Is_A_File(File file){
        if(!file.isDirectory()){
            process(file);
        }else{
            File [] files = file.listFiles();
            for(File f: files){
                if_It_Is_A_File(f);
            }
        }

    }

    //ʹ�����ȶ��У�ͳ��ǰʮ��import������
    public String ten_Most(){
    	
    	 Comparator<Entry<String,Integer>> compare;
		    compare = new Comparator<Entry<String,Integer>>() {//��дcompare����
		      public int compare(Entry<String,Integer> e1, Entry<String,Integer> e2) {
		        return e2.getValue() - e1.getValue();
		      }
		    };
        PriorityQueue<Entry<String,Integer>> pq=new
                PriorityQueue<Entry<String,Integer>>(compare);//���ȶ�����ʹ�ö����compare���󣬱�֤Entry��Integerֵ����ڶ��ж���
        for(Entry<String,Integer> item: this.count_Map.entrySet()){
            pq.offer(item);
        }
        StringBuilder result=new StringBuilder();
        result.append("importǰʮ����\n");
        for(int i=0;i<10;i++){
            Entry<String,Integer>  big=pq.poll();
            if(big==null)
                break;
            result.append("import  ");
            result.append(big.getKey());
            result.append("������ ��");
            result.append(big.getValue());
            result.append("\n");
        }
        return result.toString();
    }
    public static void main(String[] args) {
        String p="D:\\eclipse\\eclipse-jee-mars\\workspace\\EffectiveLines\\src\\EffectiveLines.java";
        File directory = new File(p);
        CountMostImport count=new CountMostImport(p);
        count.if_It_Is_A_File(directory);
        System.out.println(count.ten_Most());
    }


 
}

