package cpm.wxw.test;

import java.util.Collection;

import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TypedDependency;
import edu.stanford.nlp.trees.international.pennchinese.ChineseGrammaticalStructure;

public class Test {

	public static void main(String[] args) {
		//模型的路径
		String modelPath = "";
		//要解析的句子
		String str = "";
		//加载模型
		LexicalizedParser lp = LexicalizedParser.loadModel(modelPath);
		//文本解析成树
		Tree t = lp.parse(str);
		//打印树
		System.out.println("树的结果是：");
		t.pennPrint();
		
		//根据一棵树得到中文语法结构
		ChineseGrammaticalStructure gs = new ChineseGrammaticalStructure(t);
		System.out.println("中文语法结构结果：");
		System.out.println(gs);
		
		//依存关系
		Collection<TypedDependency> td1 = gs.typedDependenciesCollapsed();
		System.out.println("依存关系分析的结果：");
		System.out.println(td1);
		
		String segsAndPoses = "";
		for (int i = 0; i < td1.size(); i++) {
			TypedDependency td = (TypedDependency) td1.toArray()[i];
			String segAndPos = td.dep().toString();
			segsAndPoses += segAndPos+" ";
		}
		System.out.println("分词和词性标注的结果：");
		System.out.println(segsAndPoses);
	}
}
