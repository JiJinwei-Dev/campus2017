/*
 * dengjintao
 һ��ͳ��һ��Java�ļ�����Ч����������ҵ������EffectiveLines��
1����Ч����������
2�������Ǵ�����ж���ע�͵����*/
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EffectiveLines {
	static int code_count=0;//Java�ļ���Ч����
	static int zhushi_count=0;//����ע�������������Ƕ��У�
	static int space_count=0;//�ո�
	static boolean flag = false;//
	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("D:\\eclipse\\eclipse-jee-mars\\workspace\\EffectiveLines\\src\\EffectiveLines.java"));
			String line=null;
			while((line = br.readLine()) != null)
			{pattern(line);}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("��Ч���������� " + code_count);
	}

	private static void pattern(String line) {
		//ƥ�����
		String space = "\\s*";
		//ƥ�䵥��ע�͵��� /*..... */��/**.... */
		String zhushi1 = "\\s*/\\*{1,}.*(\\*/).*";
		//ƥ�䵥��ע����//....
		String zhushi2="\\s*//.*";
		if(line.matches(zhushi1)){
			zhushi_count++;
		}else if(line.matches(zhushi2)){
			zhushi_count++;
		}else if(line.matches(space)){
			space_count++;
		}else {
			code_count++;
		}
		
	}
}
