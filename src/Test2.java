public class Test2 {

    public enum RobotOrientation{
        Left, Right, Center
    }
    public static void main(String[] args){
        System.out.println(RobotOrientation.Right.ordinal());
    }
}
