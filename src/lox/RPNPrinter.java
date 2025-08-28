package lox;

public class RPNPrinter implements Expr.Visitor<String> {
    void print(Expr expr) {
        System.out.println(expr.accept(this));
    }

    public String visitBinaryExpr(Expr.Binary expr) {
        return expr.left.accept(this) + " " +
                expr.right.accept(this) + " " +
                expr.operator.lexeme;
    }

    public String visitUnaryExpr(Expr.Unary expr) {
        return expr.right.accept(this) + " " +
                expr.operator.lexeme;
    }

    public String visitLiteralExpr(Expr.Literal expr) {
        if (expr.value == null)
            return "nil";
        return expr.value.toString();
    }

    public String visitGroupingExpr(Expr.Grouping expr) {
        return expr.expression.accept(this);
    }

    public String visitTernaryExpr(Expr.Ternary expr) {
        return expr.thenBranch.accept(this) + " " +
                expr.elseBranch.accept(this) + " " +
                expr.questionMark.lexeme + " " +
                expr.colon.lexeme;
    }

    public static void main(String[] args) {
        Expr expression = new Expr.Binary(
                new Expr.Unary(
                        new Token(TokenType.MINUS, "-", null, 1),
                        new Expr.Literal(123)),
                new Token(TokenType.STAR, "*", null, 1),
                new Expr.Grouping(
                        new Expr.Literal(45.67)));

        new RPNPrinter().print(expression);
    }

}