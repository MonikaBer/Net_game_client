package client.view.gameWindow;

import client.model.network.packets.gameLayout.Bullet;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    private Graphics2D gameGraphics;
    private ArrayList<Gamer2D> gamers;
    private ArrayList<Bullet2D> bullets;
    private ArrayList<Color> colors;

    public GamePanel() {
        this.setPreferredSize(new Dimension(600, 600));
        this.gamers = new ArrayList<>();
        this.bullets = new ArrayList<>();
        this.setColors();
    }

    public void setColors() {
        this.colors = new ArrayList<>();
        this.colors.add(new Color(0,255,0));
        this.colors.add(new Color(255,0,0));
        this.colors.add(new Color(0,0,255));
        this.colors.add(new Color(255,255,0));
        this.colors.add(new Color(0,255,255));
        this.colors.add(new Color(255,0,255));
        this.colors.add(new Color(80,0,100));
        this.colors.add(new Color(10,80,0));
    }

    public void setGamerPosition(int gamerId, double x, double y) {
        int i = getGamerIndex(gamerId);
        if (i == -1) {
            this.gamers.add(new Gamer2D(gamerId));
            i = this.gamers.size() - 1;
        }
        this.gamers.get(i).setPosition(x, y);
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets.clear();
        for (int i = 0; i < bullets.size(); i++) {
            this.bullets.add(new Bullet2D(bullets.get(i).getX(), bullets.get(i).getY()));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        if (!this.bullets.isEmpty()) {
        }
        this.gameGraphics = (Graphics2D) g;
        super.paintComponent(gameGraphics);

        int colorNr = 0;
        for (int i = 0; i < this.gamers.size(); i++) {
            this.gameGraphics.setColor(this.colors.get(colorNr));
            this.gameGraphics.draw(this.gamers.get(i).getGraphicRepresentation());
            this.gameGraphics.fill(this.gamers.get(i).getGraphicRepresentation());
            colorNr++;
            if (colorNr > 7) colorNr = 0;
        }

        for (int i = 0; i < this.bullets.size(); i++) {
            this.gameGraphics.setColor(this.bullets.get(i).getColor());
            this.gameGraphics.draw(this.bullets.get(i).getGraphicRepresentation());
            this.gameGraphics.fill(this.bullets.get(i).getGraphicRepresentation());
        }
    }

    public void update(Graphics g) {
        paint(g);
    }

    public int getGamerIndex(int gamerId) {
        for (int i = 0; i < this.gamers.size(); i++) {
            if (gamerId == this.gamers.get(i).getGamerId()) {
                return i;
            }
        }
        return -1;
    }
}