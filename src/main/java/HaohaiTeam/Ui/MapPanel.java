package HaohaiTeam.Ui;

import HaohaiTeam.game.Location;
import HaohaiTeam.Ui.Map;
import HaohaiTeam.game.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class MapPanel extends JPanel {
    private final Map gameMap;
    private final Player player;
    private Image treeImage;
    private Image buildingImage;

    public MapPanel(Map gameMap, Player player) {
        this.gameMap = gameMap;
        this.player = player;

        try {
            // 加载图像资源
            URL treeUrl = getClass().getResource("/UiPics/treeImage.png");
            URL buildingUrl = getClass().getResource("/UiPics/buildingImage.png");

            if (treeUrl != null && buildingUrl != null) {
                treeImage = ImageIO.read(treeUrl);
                buildingImage = ImageIO.read(buildingUrl);
            } else {
                // 图像资源加载失败时的处理（例如，打印错误信息或使用默认颜色）
                System.out.println("Pictures Error");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 绘制地图背景和网格线
//        g.setColor(Color.GREEN);
//        g.fillRect(0, 0, getWidth(), getHeight());

        // 假设我们想将网格线和玩家大小缩小为原来的一半
        int tileSizeHalf = gameMap.getTileSize() / 2;

        g.setColor(Color.BLACK);




        // 遍历数组，调用getTileType方法绘制不同的地城市元素
        for (int row = 0; row < gameMap.getHeight(); row++) {
            for (int col = 0; col < gameMap.getWidth(); col++) {
                Map.TileType tileType = gameMap.getTileType(col, row);
                int x = col * gameMap.getTileSize();
                int y = row * gameMap.getTileSize();
                switch (tileType) {
                    case EMPTY_SPACE:
                        g.setColor(Color.GREEN);
                        break;
                    case WALL:
                        g.setColor(Color.GRAY);
                        break;
                    case ROAD:
                        g.setColor(Color.BLACK);
                        break;
                    case TREE:
                        if (treeImage != null) {
                            g.drawImage(treeImage, x, y, gameMap.getTileSize(), gameMap.getTileSize(), this);
                        } else {
                            g.setColor(Color.BLACK); // 如果图像加载失败，则使用深绿色表示树木
                            g.fillRect(x, y, gameMap.getTileSize(), gameMap.getTileSize());
                        }
                        break;
                    case BUILDING:
                        if (buildingImage != null) {
                            g.drawImage(buildingImage, x, y, gameMap.getTileSize(), gameMap.getTileSize(), this);
                        } else {
                            g.setColor(Color.RED); // 如果图像加载失败，则使用红色表示建筑
                            g.fillRect(x, y, gameMap.getTileSize(), gameMap.getTileSize());
                        }
                        break;
                    case START_POINT:
                        g.setColor(Color.CYAN);
                        break;
                    case END_POINT:
                        g.setColor(Color.MAGENTA);
                        break;
                    default:
                        g.setColor(Color.WHITE); // 未知类型，默认为白色
                }


                // 绘制边界线，如果需要的话
                g.setColor(Color.LIGHT_GRAY);
                g.drawRect(x, y, gameMap.getTileSize(), gameMap.getTileSize());
            }
        }

        // 绘制玩家当前位置
        if (player != null) {
            int x = (int) (player.getPlayer_location().getX() * gameMap.getTileSize());
            int y = (int) (player.getPlayer_location().getY() * gameMap.getTileSize());
            g.setColor(Color.BLACK);
            g.fillOval(x, y, gameMap.getTileSize(), gameMap.getTileSize());
        }

    }

    // 假设您需要这个方法来更新玩家位置
    public void refreshPlayerLocation(Location newLocation) {
        // 更新玩家位置的内部状态（如果需要）
        // ...

        repaint(); // 重绘整个面板以显示新的玩家位置
    }
}