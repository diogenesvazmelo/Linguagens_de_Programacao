
import lexical.LexicalAnalysis;
import lexical.TokenType;

import syntatic.SyntaticAnalysis;

public class mpasi {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java mpasi [miniPascal file]");
            return;
        }

        try (LexicalAnalysis l = new LexicalAnalysis(args[0])) {            
            
            // O código a seguir é dado para testar o interpretador.
            // TODO: descomentar depois que o analisador léxico estiver OK.
            SyntaticAnalysis s = new SyntaticAnalysis(l);
            s.start().execute();
            

            // // O código a seguir é usado apenas para testar o analisador léxico.
            // // TODO: depois de pronto, comentar o código abaixo.
            // Lexeme lex = l.nextToken();
            // while (checkType(lex.type)) {                
            //     System.out.printf("(\"%s\", %s)\n", lex.token, lex.type);
            //     lex = l.nextToken();
            // }

            // switch (lex.type) {
            //     case INVALID_TOKEN:
            //         System.out.printf("%02d: Lexema inválido [%s]\n", l.getLine(), lex.token);
            //         break;
            //     case UNEXPECTED_EOF:
            //         System.out.printf("%02d: Fim de arquivo inesperado\n", l.getLine());
            //         break;
            //     default: 
            //         // end of file message
            //         System.out.printf("(\"%s\", %s)\n", lex.token, lex.type);
            //         break;
            // }
        } catch (Exception e) {
            System.err.println("Internal error: " + e.getMessage());
        }
    }

    private static boolean checkType(TokenType type) {
        return !(type == TokenType.END_OF_FILE ||
                 type == TokenType.INVALID_TOKEN ||
                 type == TokenType.UNEXPECTED_EOF);
    }
}

