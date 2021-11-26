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

        String newarr[] = new String[n + 1];

        for (i = 0; i < n; i++)
            newarr[i] = arr[i];

        newarr[n] = x;

        return newarr;
    }

    public static void main(String[] args) {



        String str = "the cat hunts the mouse";
        Parser parser = new Parser();
        Tree tree = parser.parse(str);

        String[] tokens = {};
        int tamanho;

        List<Tree> leaves = tree.getLeaves();
        // Print words and Pos Tags
        for (Tree leaf : leaves) {
            Tree parent = leaf.parent(tree);

            if(parent.label().value().equals("CC")){
                tamanho = tokens.length;
                tokens = addX(tamanho, tokens, leaf.label().value() + " => Conjunction");

            }

            if(parent.label().value().equals("CD")){
                tamanho = tokens.length;
                tokens = addX(tamanho, tokens, leaf.label().value() + " => Cardinal number");

            }

            if(parent.label().value().equals("DT")){
                tamanho = tokens.length;
                tokens = addX(tamanho, tokens, leaf.label().value() + " => Article");

            }

            if(parent.label().value().equals("EX")){
                tamanho = tokens.length;
                tokens = addX(tamanho, tokens, leaf.label().value() + " => Existential");

            }

            if(parent.label().value().equals("FW")){
                tamanho = tokens.length;
                tokens = addX(tamanho, tokens, leaf.label().value() + " => Foreign word");

            }

            if(parent.label().value().equals("IN")){
                tamanho = tokens.length;
                tokens = addX(tamanho, tokens, leaf.label().value() + " => Preposition");

            }

            if(parent.label().value().equals("JJ") || parent.label().value().equals("JJR") || parent.label().value().equals("JJS")) {
                tamanho = tokens.length;
                tokens = addX(tamanho, tokens, leaf.label().value() + " => Adjective");

            }

            if(parent.label().value().equals("LS")){
                tamanho = tokens.length;
                tokens = addX(tamanho, tokens, leaf.label().value() + " => List marker");

            }

            if(parent.label().value().equals("MD")){
                tamanho = tokens.length;
                tokens = addX(tamanho, tokens, leaf.label().value() + " => Modal");

            }

            if(parent.label().value().equals("NN") || parent.label().value().equals("NNS")) {
                tamanho = tokens.length;
                tokens = addX(tamanho, tokens, leaf.label().value() + " => Noun");

            }

            if(parent.label().value().equals("NNP") || parent.label().value().equals("NNPS")) {
                tamanho = tokens.length;
                tokens = addX(tamanho, tokens, leaf.label().value() + " => Proper Noun");

            }

            if(parent.label().value().equals("PDT")){
                tamanho = tokens.length;
                tokens = addX(tamanho, tokens, leaf.label().value() + " => Predeterminer");

            }

            if(parent.label().value().equals("POS")){
                tamanho = tokens.length;
                tokens = addX(tamanho, tokens, leaf.label().value() + " => Possessive ending");

            }

            if(parent.label().value().equals("PRP") || parent.label().value().equals("PRP$")){
                tamanho = tokens.length;
                tokens = addX(tamanho, tokens, leaf.label().value() + " => Pronoun");

            }

            if(parent.label().value().equals("RB") || parent.label().value().equals("RBR") || parent.label().value().equals("RBS")){
                tamanho = tokens.length;
                tokens = addX(tamanho, tokens, leaf.label().value() + " => Adverb");

            }

            if(parent.label().value().equals("RP")){
                tamanho = tokens.length;
                tokens = addX(tamanho, tokens, leaf.label().value() + " => Particle");

            }

            if(parent.label().value().equals("SYM")){
                tamanho = tokens.length;
                tokens = addX(tamanho, tokens, leaf.label().value() + " => Symbol");

            }

            if(parent.label().value().equals("TO")){
                tamanho = tokens.length;
                tokens = addX(tamanho, tokens, leaf.label().value() + " => to");

            }

            if(parent.label().value().equals("UH")){
                tamanho = tokens.length;
                tokens = addX(tamanho, tokens, leaf.label().value() + " => Interjection");

            }

            if(parent.label().value().equals("VB") || parent.label().value().equals("VBD") || parent.label().value().equals("VBG") ||
                    parent.label().value().equals("VBN") || parent.label().value().equals("VBP") || parent.label().value().equals("VBZ")){
                tamanho = tokens.length;
                tokens = addX(tamanho, tokens, leaf.label().value() +  " => Verb");
            }

            if(parent.label().value().equals("WDT")){
                tamanho = tokens.length;
                tokens = addX(tamanho, tokens, leaf.label().value() + " => Wh-article");

            }

            if(parent.label().value().equals("WP")){
                tamanho = tokens.length;
                tokens = addX(tamanho, tokens, leaf.label().value() + " => Wh-pronoum");

            }

            if(parent.label().value().equals("WP$")){
                tamanho = tokens.length;
                tokens = addX(tamanho, tokens, leaf.label().value() + " => Possessive wh-pronoum");

            }

            if(parent.label().value().equals("WRB")){
                tamanho = tokens.length;
                tokens = addX(tamanho, tokens, leaf.label().value() + " => Wh-adverb");

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