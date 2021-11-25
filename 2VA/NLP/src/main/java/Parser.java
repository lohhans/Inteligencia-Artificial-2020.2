import java.io.StringReader;
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

    public static void main(String[] args) {
        String str = "Hi, my name is Armstrong.";
        Parser parser = new Parser();
        Tree tree = parser.parse(str);

        List<Tree> leaves = tree.getLeaves();
        // Print words and Pos Tags
        for (Tree leaf : leaves) {
            Tree parent = leaf.parent(tree);
            System.out.print(leaf.label().value() + "-" + parent.label().value() + " ");
        }
        System.out.println();
    }
}

// Siglas abaixo:
//https://www.ling.upenn.edu/courses/Fall_2003/ling001/penn_treebank_pos.html