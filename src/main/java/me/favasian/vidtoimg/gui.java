package me.favasian.vidtoimg;

import javax.swing.*;
import java.awt.*;

public class gui {
    private JFrame window;
    public gui(){
        //create instance of Jframe
        window = new JFrame("Converter");

        //set window size and make window appear
        window.setSize(800,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        //main panel for the whole window
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        /*panels for the 5 directions*/
        JPanel centerPanel = new JPanel();
        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();

        //sets center layout to be grid layout
        centerPanel.setLayout(new GridLayout(6,1));

        /*Components*/
        //JLabels
        JLabel addmp4 = new JLabel("Select mp4 file to be tagged");
        addmp4.setFont(new Font("Courier", Font.PLAIN,12));
        addmp4.setHorizontalAlignment(JLabel.CENTER);

        JLabel addFolder = new JLabel("Select output folder");
        addFolder.setFont(new Font("Courier", Font.PLAIN,12));
        addFolder.setHorizontalAlignment(JLabel.CENTER);

        JLabel addJump = new JLabel("Enter how many frame you would like to jump");
        addJump.setFont(new Font("Courier", Font.PLAIN,12));
        addJump.setHorizontalAlignment(JLabel.CENTER);

        JLabel title = new JLabel("Convert video to images!");
        title.setFont(new Font("Courier", Font.BOLD,12));

        //JButtons
        JButton done = new JButton("Done");

        //JTextField
        JFormattedTextField frameJump = new JFormattedTextField();
        frameJump.setColumns(20);


        /*add labels to directional panel*/
        //center
        centerPanel.add(addmp4);
        centerPanel.add(addFolder);
        centerPanel.add(addJump);
        centerPanel.add(frameJump);

        //north
        northPanel.add(title);

        //south
        southPanel.add(done);

        /*adds directional panel to main panel*/
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        window.setContentPane(mainPanel);
        window.setVisible(true);
    }
}
