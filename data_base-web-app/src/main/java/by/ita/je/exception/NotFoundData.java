package by.ita.je.exception;

public class NotFoundData  extends RuntimeException {

    public NotFoundData(String name) {
        super("Такой записи для " + name + " в базе данных не существует");
    }
}
