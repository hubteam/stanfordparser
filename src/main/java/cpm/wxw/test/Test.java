package cpm.wxw.test;

import java.util.Collection;

import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TypedDependency;
import edu.stanford.nlp.trees.international.pennchinese.ChineseGrammaticalStructure;

public class Test {

	public static void main(String[] args) {
		//ģ�͵�·��
		String modelPath = "";
		//Ҫ�����ľ���
		String str = "";
		//����ģ��
		LexicalizedParser lp = LexicalizedParser.loadModel(modelPath);
		//�ı���������
		Tree t = lp.parse(str);
		//��ӡ��
		System.out.println("���Ľ���ǣ�");
		t.pennPrint();
		
		//����һ�����õ������﷨�ṹ
		ChineseGrammaticalStructure gs = new ChineseGrammaticalStructure(t);
		System.out.println("�����﷨�ṹ�����");
		System.out.println(gs);
		
		//�����ϵ
		Collection<TypedDependency> td1 = gs.typedDependenciesCollapsed();
		System.out.println("�����ϵ�����Ľ����");
		System.out.println(td1);
		
		String segsAndPoses = "";
		for (int i = 0; i < td1.size(); i++) {
			TypedDependency td = (TypedDependency) td1.toArray()[i];
			String segAndPos = td.dep().toString();
			segsAndPoses += segAndPos+" ";
		}
		System.out.println("�ִʺʹ��Ա�ע�Ľ����");
		System.out.println(segsAndPoses);
	}
}
