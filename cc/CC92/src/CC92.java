import java.util.HashMap;
import java.util.LinkedList;



class Position{
    int x;
    int y;
    
    public Position(int _x, int _y){
        x = _x;
        y = _y;
    }
}

public class CC92{

    public static final int XDES = 10;
    public static final int YDES = 10;

    public static HashMap<Position, Boolean> obstacles = new HashMap<Position, Boolean>();
    
    public static void printPath(LinkedList<Position> path){
        for(Position p : path){
            System.out.print("(" + p.x + "," + p.y + ")");
            if(p.x != 10 || p.y != 10){
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    //collision check
    public static boolean whetherCollision(int x,int y){
        return obstacles.containsKey(new Position(x,y));
    }

    //recursive call
    public static void findPath(int x, int y, LinkedList<Position> path){
        path.add(new Position(x,y));

        if(x == XDES && y == YDES){
            printPath(path);
            return;
        }

        //check right
        if(x < XDES){
            if(!whetherCollision(x+1, y)){
                findPath(x+1, y, (LinkedList<Position>)path.clone());        
            }
            else 
                return;
        }

        if( y < YDES){
            if( ! whetherCollision(x,y + 1)){
                findPath(x, y + 1, (LinkedList<Position>)path.clone());
            }
        }
        return;
    }


    public static final void main(String[] args){
        //add obstacles
        for(int i = 1; i < 5; i ++)
            obstacles.put(new Position(i,i),true);

        findPath(0,0,new LinkedList<Position>());

    }    
}
