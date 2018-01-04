package com.wxw.parsetree;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Properties;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

/**
 * 斯坦福句法分析
 * @author 王馨苇
 *
 */
public class CoreNLPSyntactic {

    public static void main(String[] args) throws IOException
    {
        Properties props = new Properties();
        props.setProperty("annotators", "segment, ssplit, pos, parse");
        props.setProperty("customAnnotatorClass.segment", "edu.stanford.nlp.pipeline.ChineseSegmenterAnnotator");
        props.setProperty("segment.model", "edu/stanford/nlp/models/segmenter/chinese/ctb.gz");
        props.setProperty("segment.sighanCorporaDict", "edu/stanford/nlp/models/segmenter/chinese");
        props.setProperty("segment.serDictionary", "edu/stanford/nlp/models/segmenter/chinese/dict-chris6.ser.gz");
        props.setProperty("segment.sighanPostProcessing", "true");
        props.setProperty("ssplit.boundaryTokenRegex", "[.]|[!?]+|[。]|[！？]+");
        props.setProperty("pos.model", "edu/stanford/nlp/models/pos-tagger/chinese-distsim/chinese-distsim.tagger");
        props.setProperty("parse.model", "edu/stanford/nlp/models/lexparser/chineseFactored.ser.gz");

        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        
        String source = args[0];
        String encoding = args[1];
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(source), encoding));
        String sentence = null;
        String dest = args[2];
        PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(dest), encoding));
        while ((sentence = in.readLine()) != null)
        {
            Annotation document = new Annotation(sentence);
            
            pipeline.annotate(document);
            
            pipeline.prettyPrint(document, out);
        }
    }
}

