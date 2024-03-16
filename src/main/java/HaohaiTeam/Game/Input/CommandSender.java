package HaohaiTeam.Game.Input;

public class CommandSender {
    public CommandSender() {
        // Constructor
    }

    public void sendCommands(String command) {

        switch (command) {
            case "MOVE_UP":
                // 实现向上移动的逻辑
                System.out.println("Moving up");
                break;
            case "MOVE_DOWN":
                // 实现向下移动的逻辑
                System.out.println("Moving down");
                break;
            case "MOVE_LEFT":
                System.out.println("Moving left");
                // 实现向左移动的逻辑
                break;
            case "MOVE_RIGHT":
                System.out.println("Moving right");
                // 实现向右移动的逻辑
                break;
            case "JUMP":
                System.out.println("Jumping");
                // 实现跳跃的逻辑
                break;
            default:
                System.out.println("Command not recognized");
        }

    }
}
