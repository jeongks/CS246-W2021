import java.awt.*;
import java.util.Random;

public class Wolf  extends Creature implements Movable, Aggressor, Aware, Spawner{
    Random _rand;
    Direction _preferredDirection;
    //int eatCount; my code before teacher's code
    boolean spawnable; //after studying teacher's code
    public Wolf(){
        _health =1;
        _rand = new Random();
        _preferredDirection = getRandomDirection();
        //eatCount = 0; before studying teacher's code
        spawnable = false; // after studying teacher's code
    }
    //spawned wolf. before studying teacher's code
//    public Wolf(int x, int y, Direction direction){
//        _health =1;
//        super.setLocation(new Point(x,y));
//        _preferredDirection = direction;
//        eatCount = 0;
//    }

    private Direction getRandomDirection(){
        switch (_rand.nextInt(4)){
            case 0:
                return Direction.Right;
            case 1:
                return Direction.Left;
            case 2:
                return Direction.Up;
            case 3:
                return Direction.Down;
            default:
                return Direction.Up;
        }
    }

    //Aware interface
    @Override
    public void senseNeighbors(Creature above, Creature below, Creature left, Creature right) {
        if(_preferredDirection == Direction.Up){
            if(above instanceof Animal)
                return;
            else if (right instanceof  Animal)
                _preferredDirection = Direction.Right;
            else if (below instanceof Animal)
                _preferredDirection = Direction.Down;
            else if (left instanceof  Animal)
                _preferredDirection = Direction.Left;
        }
        else if (_preferredDirection == Direction.Right){
            if(right instanceof Animal)
                return;
            else if (below instanceof  Animal)
                _preferredDirection = Direction.Down;
            else if (left instanceof Animal)
                _preferredDirection = Direction.Left;
            else if (above instanceof  Animal)
                _preferredDirection = Direction.Up;
        }
        else if(_preferredDirection == Direction.Down){
            if(below instanceof Animal)
                return;
            else if (left instanceof  Animal)
                _preferredDirection = Direction.Left;
            else if (above instanceof Animal)
                _preferredDirection = Direction.Up;
            else if (right instanceof  Animal)
                _preferredDirection = Direction.Right;
        }
        else if(_preferredDirection == Direction.Left){
            if(left instanceof Animal)
                return;
            else if (above instanceof  Animal)
                _preferredDirection = Direction.Up;
            else if (right instanceof Animal)
                _preferredDirection = Direction.Right;
            else if (below instanceof  Animal)
                _preferredDirection = Direction.Down;
        }
    }
    //Aggressor interface
    @Override
    public void attack(Creature target) {
        if (target instanceof Animal){
            target.takeDamage(5);
            spawnable = true;
        }
    }
    //Creature
    @Override
    Shape getShape() {
        return Shape.Square;
    }

    @Override
    Color getColor() {
        return new Color(100,100,100);
    }

    @Override
    Boolean isAlive() {
        return _health > 0;
    }
    //Movable interface
    @Override
    public void move() {
        switch (_preferredDirection){
            case Right:
                _location.x++;
                break;
            case Left:
                _location.x--;
                break;
            case Up:
                _location.y++;
                break;
            case Down:
                _location.y--;
                break;
        }
    }
    //Spawner interface
    @Override
    public Creature spawnNewCreature() {
        //before studying teacher's code
//        if (eatCount > 0) {
//            if (_preferredDirection == Direction.Up) {
//                eatCount--;
//                return new Wolf((int)(getLocation().getX()), (int)(getLocation().getY())-1,_preferredDirection);
//            } else if (_preferredDirection == Direction.Right) {
//                eatCount--;
//                return new Wolf((int)(getLocation().getX())-1, (int)(getLocation().getY()),_preferredDirection);
//            } else if (_preferredDirection == Direction.Down) {
//                eatCount--;
//                return new Wolf((int)(getLocation().getX()), (int)(getLocation().getY())+1,_preferredDirection);
//            } else if (_preferredDirection == Direction.Left) {
//                eatCount--;
//                return new Wolf((int)(getLocation().getX())+1, (int)(getLocation().getY()),_preferredDirection);
//            } else {
//                eatCount--;
//                return new Wolf((int)(getLocation().getX()), (int)(getLocation().getY())-1,_preferredDirection);
//            }
//            return new Wolf();
//        } else {
//            return null;
//        }
        if(!spawnable){
            return null;
        }
        Wolf baby = new Wolf();
        Point babyPoint = (Point)_location.clone();
        babyPoint.x--;
        baby.setLocation(babyPoint);

        spawnable = false;
        return baby;
    }
}
