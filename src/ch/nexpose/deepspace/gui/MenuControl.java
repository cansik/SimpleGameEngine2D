package ch.nexpose.deepspace.gui;

import ch.nexpose.sge.SimpleGameEngine2D;
import ch.nexpose.sge.objects.Object2D;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by cansik on 05/05/14.
 */
public class MenuControl extends Object2D
{
    final int KEYBUFFER_SLEEP = 100;


    ArrayList<MenuItem> items;
    int selectedIndex = 0;
    int textSize = 20;

    public MenuControl(SimpleGameEngine2D engine)
    {
        super(engine);
        items = new ArrayList<MenuItem>();
    }

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
        if(getEngine().getInputTracker().isKeyPressed(KeyEvent.VK_UP))
        {
            getEngine().getInputTracker().setDetectionPaused(true);
            if(selectedIndex - 1 >= 0)
                selectedIndex--;
            sleep(KEYBUFFER_SLEEP);
            getEngine().getInputTracker().setDetectionPaused(false);
        }

        if(getEngine().getInputTracker().isKeyPressed(KeyEvent.VK_DOWN))
        {
            getEngine().getInputTracker().setDetectionPaused(true);
            if(selectedIndex + 1 < items.size())
                selectedIndex++;
            sleep(KEYBUFFER_SLEEP);
            getEngine().getInputTracker().setDetectionPaused(false);
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

    private  void sleep(int milliseconds)
    {
        try {
            Thread.sleep(milliseconds);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
