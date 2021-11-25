import java.io.StringReader;
import java.sql.SQLOutput;
import java.util.List;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.trees.Tree;

class Parser {

    private final static String PCG_MODEL = "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";

    private final TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(), "invertible=true");

    private final LexicalizedParser parser = LexicalizedParser.loadModel(PCG_MODEL);

    public Tree parse(String str) {
        List<CoreLabel> tokens = tokenize(str);
        return parser.apply(tokens);
    }

    private List<CoreLabel> tokenize(String str) {
        Tokenizer<CoreLabel> tokenizer =
                tokenizerFactory.getTokenizer(
                        new StringReader(str));
        return tokenizer.tokenize();
    }

    public static String[] addX(int n, String arr[], String x)
    {
        int i;

        // create a new array of size n+1
        String newarr[] = new String[n + 1];

        // insert the elements from
        // the old array into the new array
        // insert all elements till n
        // then insert x at n+1
        for (i = 0; i < n; i++)
            newarr[i] = arr[i];

        newarr[n] = x;

        return newarr;
    }

    public static void main(String[] args) {



        String str = "the cat hunted the mouse";
        Parser parser = new Parser();
        Tree tree = parser.parse(str);

        String[] tokens = {};
        int tamanho;

        List<Tree> leaves = tree.getLeaves();
        // Print words and Pos Tags
        for (Tree leaf : leaves) {
            Tree parent = leaf.parent(tree);

            if(parent.label().value().equals("DT")){
                tamanho = tokens.length;
                tokens = addX(tamanho, tokens, leaf.label().value() + " => Article");

            }
            if(parent.label().value().equals("NN")) {
                tamanho = tokens.length;
                tokens = addX(tamanho, tokens, leaf.label().value() + " => Noun");

            }
            if(parent.label().value().equals("VBD")){
                tamanho = tokens.length;
                tokens = addX(tamanho, tokens, leaf.label().value() +  " => Verb");
            }



        }
        tamanho = tokens.length;
        System.out.print("[");
        for (int i = 0; i < tamanho; i++){
            System.out.print(tokens[i]);
            if(i < tamanho-1)
            System.out.print(", ");
        }
        System.out.print("]");
        System.out.println();
    }
}

// Siglas abaixo:
//https://www.ling.upenn.edu/courses/Fall_2003/ling001/penn_treebank_pos.html