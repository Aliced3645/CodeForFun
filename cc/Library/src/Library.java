import java.util.HashMap;

class Book{
    String name;
    String author;

    public Integer HashCode(){
        return 0;
    }

}

class ElectronoicBook extends Book{

}

class PaperBook extends Book{

}

class Position{
    int x;
    int y;
}

class Chair{
    boolean occupied;
    Table table;
}

class Table{
    //assume a table has four seats each
    Floor floor;
    Position position;
    Chair[] chairs;
}

class Floor{
    //a set of tables and seats
    Table[] tables;
    //a treeMap to search book by their name
    HashMap<String, Book> books;
}

interface LibraryServices{
    //interface provided to the users
    Book searchBookByName(String name);   
    
    //look up empty seat
    Chair getSeat();
    
}

class LibraryInfoCard{
    final String libraryName = "asdf";
    final String libraryHours = "asdfjlk";
}

public class Library extends LibraryServices{

    //Singleton
    private static Library instance = null;
    private Library(){
    };

    public static Library getLibrary(){
        if(Library.instance == null){
            Library.instance = new Library();
        }
        return instance;
    }


    //collection of floors information
    //each floor contains books/seats information
    Floor[] floors;
    

    //a static factory which generate LibraryInfo posters
    public static LibraryInfoCard provideInfoCards(){
        return new LibraryInfoCard();
    }
    
    //implementations....
    
}
