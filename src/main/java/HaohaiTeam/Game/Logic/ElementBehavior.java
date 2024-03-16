package HaohaiTeam.Game.Logic;

import HaohaiTeam.Game.Element.GameElement;
import HaohaiTeam.Game.Element.Transport.*;
public class ElementBehavior {
    public ElementBehavior() {
        // Constructor
    }

    public void updateBehavior(GameElement element) {
        // Method to update element behavior
        // 根据元素的类型执行不同的行为
        if (element instanceof Bike) {
            // 处理自行车的行为
        } else if (element instanceof Taxi) {
            // 处理出租车的行为
        } else if (element instanceof Luas) {
            // 处理轻轨的行为
        }
    }
}




