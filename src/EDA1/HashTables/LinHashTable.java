package EDA1.HashTables;

public class LinHashTable<T> extends HashTable<T> {

    public LinHashTable() {
        super();
    }

    public LinHashTable(int n) {
        super(n);
    }

    protected int procPos(T s){
        int i = 0;
        boolean visited = false;
        int ativos = 0;
        int indexToInsert = 0;
        int index = Math.abs(s.hashCode()) % arr.length;

        if(arr[(index + i) % arr.length] == null)
            return (index + i) % arr.length;

        while(arr[(index + i) % arr.length] != null && ativos != nElementos) {
            if (arr[(index + i) % arr.length].isAtivo()) {
                if (arr[(index + i) % arr.length].getElemento().equals(s))
                    return (index + i) % arr.length;
                ativos++;
            } else if (!visited) {
                indexToInsert = (index + i) % arr.length;
                visited = true;
            }
            i++;
        }

        return indexToInsert;
    }
}