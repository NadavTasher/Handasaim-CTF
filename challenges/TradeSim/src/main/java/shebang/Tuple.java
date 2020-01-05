package shebang;

public class Tuple<T, U> {
    private T left;
    private U right;
    public Tuple(T left, U right){
        this.left = left;
        this.right = right;
    }

    public T getLeft() {
        return left;
    }

    public U getRight() {
        return right;
    }

    public void setLeft(T left) {
        this.left = left;
    }

    public void setRight(U right) {
        this.right = right;
    }
}
