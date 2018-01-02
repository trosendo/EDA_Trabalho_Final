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
        String visited = "";
        int ativos = 0;
        int indexToInsert = 0;
        int index = Math.abs(s.hashCode()) % arr.length;

        if(arr[(index + i) % arr.length] == null) {
            //System.out.println("\tinserting " + s.toString() + " in index " + (index + i));
            return (index + i) % arr.length;
        }



        /*while(arr[index] != null && ativos <= nElementos){
            System.out.println("\t\t\t\t" + index);
            if(arr[index].isAtivo()){
                ativos++;
                if(arr[index].getElemento().equals(s))
                    return index;
            } else if(!visited.contains(String.format("%d", index))){
                indexToInsert = index;
                visited += String.format("%d, ", index);
            }
            i++;
            index = (index + i) % arr.length;
        }*/


        //O PROBLEMA ESTÁ AQUI EM BAIXO!!!!!!!!!!!!!!!!!!!!!!
        while(arr[(index + i) % arr.length] != null) {
            if (arr[(index + i) % arr.length].isAtivo()) {
                if (arr[(index + i) % arr.length].getElemento().equals(s)) {
                    return (index + i) % arr.length;
                }
            } else if (!visited.contains(String.format("%d", index))) {
                indexToInsert = (index + i) % arr.length;
                visited += String.format("%d, ", index);
            }
            i++;
        }

        if(indexToInsert == 0) { //means while loop did nothing
            return (index + i) % arr.length;
        }

        return indexToInsert;

    }
}