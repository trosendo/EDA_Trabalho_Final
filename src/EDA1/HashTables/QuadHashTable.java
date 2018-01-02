package EDA1.HashTables;

public class QuadHashTable<T> extends HashTable<T> {

    public QuadHashTable(){
        super();
    }

    public QuadHashTable(int dim){
        super(dim);
    }

    protected int procPos(T s){
        int i = 0;
        boolean visited = false;
        int ativos = 0;
        int indexToInsert = 0;
        int index = Math.abs(s.hashCode()) % arr.length;
        int hashedIndex = index + (i*i);

        /*for(int x = 0; x < arr.length; x++){
            if(arr[x] != null)
                if(arr[x].getElemento().equals(s))
                    return x;
        }*/
        if(s.equals("10"))
            System.out.println("OLALALALALAL " + hashedIndex);

        if(arr[hashedIndex % arr.length] == null)
            return hashedIndex % arr.length;

        while(arr[hashedIndex % arr.length] != null && ativos != nElementos) {
            if (arr[hashedIndex % arr.length].isAtivo()) {
                if (arr[hashedIndex % arr.length].getElemento().equals(s))
                    return hashedIndex % arr.length;
                ativos++;
            } else if (!visited) {
                indexToInsert = hashedIndex % arr.length;
                visited = true;
            }
            i++;
            hashedIndex = index + (i*i);
        }

        return indexToInsert;
    }
}
