package ch.nexpose.deepspace.gui;

import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.fx.audio.SoundPlayer;
import ch.nexpose.sge.objects.BaseObject2D;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by cansik on 05/05/14.
 */
public class MenuControl extends BaseObject2D
{
    ArrayList<MenuItem> items;
    int selectedIndex = 0;
    int textSize = 20;
    boolean keyInputUpHit;
    boolean keyInputDownHit;

    public MenuControl(SimpleGameEngine2D engine)
    {
        super(engine);
        items = new ArrayList<MenuItem>();
    }

    /**
     * Adds a new menu item to the menu.
     * @param item
     */
    public void addItem(MenuItem item)
    {
        items.add(item);
    }

    public int getSelectedIndex()
    {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex)
    {
        this.selectedIndex = selectedIndex;
    }

    public int getTextSize()
    {
        return textSize;
    }

    public void setTextSize(int textSize)
    {
        this.textSize = textSize;
    }

    public MenuItem getSelectedItem()
    {
        return items.get(this.selectedIndex);
    }

    @Override
    public void paint(Graphics2D g)
    {
        int space = 10;
        int rowIndex = 0;

        for(MenuItem item : items)
        {
            item.setSelected(selectedIndex == rowIndex);
            item.setColor(this.getColor());
            item.setFont(new Font(item.getFont().getName(), Font.PLAIN, textSize));

            item.setLocation(new Point(getLocation().x, getLocation().y + (rowIndex * (space + item.getSize().height))));
            item.paint(g);
            rowIndex++;
        }
    }

    @Override
    public void action()
    {
        if (getEngine().getInputTracker().isKeyPressed(KeyEvent.VK_UP))
        {
            if (!keyInputUpHit)
            {
                if(selectedIndex > 0)
                {
                    selectedIndex--;
                    SoundPlayer.playSound(getClass().getResourceAsStream("/resources/sounds/menu_click.wav"));
                }
                else
                {
                    selectedIndex = items.size() - 1;
                }
            }
            keyInputUpHit = true;
        }
        else
        {
            keyInputUpHit = false;
        }

        if (getEngine().getInputTracker().isKeyPressed(KeyEvent.VK_DOWN))
        {
            if (!keyInputDownHit)
            {
                if(selectedIndex < items.size() - 1)
                {
                    selectedIndex++;
                    SoundPlayer.playSound(getClass().getResourceAsStream("/resources/sounds/menu_click.wav"));
                }
                else
                {
                    selectedIndex = 0;
                }
            }
            else
            {

            }

            keyInputDownHit = true;
        }
        else
        {
            keyInputDownHit = false;
        }
    }

    @Override
    public Dimension getSize()
    {
        int maxWidth = 0;
        int maxHeight = 0;

        for(MenuItem item : items)
        {
            Dimension size = item.getSize();

            if(size.width > maxWidth)
                maxWidth = size.width;

            if(size.height > maxHeight)
                maxHeight = size.height;
        }

        return new Dimension(maxWidth, maxHeight);
    }
}
