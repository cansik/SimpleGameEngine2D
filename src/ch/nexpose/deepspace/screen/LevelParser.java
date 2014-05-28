package ch.nexpose.deepspace.screen;

import ch.nexpose.deepspace.LevelGameStory;
import ch.nexpose.sge.story.StoryBoard;
import ch.nexpose.sge.ui.GameScene;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.File;

/**
 * Created by cansik on 27/05/14.
 */
public class LevelParser
{
    public StoryBoard ReadLevels(GameScene scene)
    {
        StoryBoard board = new StoryBoard();

        try
        {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(getClass().getResourceAsStream("/resources/Levels.xml"));

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("level");

            for (int temp = 0; temp < nList.getLength(); temp++)
            {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    LevelGameStory level = new LevelGameStory(scene, board, temp + 1);
                    Element eElement = (Element) nNode;

                    level.setScoreGoal(Integer.parseInt(eElement.getElementsByTagName("scoreGoal").item(0).getTextContent()));
                    level.setEnemyShootingChance(Integer.parseInt(eElement.getElementsByTagName("enemyShootingChance").item(0).getTextContent()));
                    level.setEnemySpawnChance(Integer.parseInt(eElement.getElementsByTagName("enemySpawnChance").item(0).getTextContent()));
                    level.setEnemySpeedingChance(Integer.parseInt(eElement.getElementsByTagName("enemySpeedingChance").item(0).getTextContent()));

                    board.addGameStory(level);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.toString(), "Exception", JOptionPane.PLAIN_MESSAGE);
        }
        return board;
    }
}
