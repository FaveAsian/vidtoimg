package me.favasian.vidtoimg;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gui {
    private JFrame window;
    public gui(){
        //create instance of Jframe
        window = new JFrame("Converter");

        //creates instance of gridbagconstraint
        GridBagConstraints gbc = new GridBagConstraints();

        //create instance of border
        Border blackline = BorderFactory.createLineBorder(Color.black);

        //create imageicon for button
        ImageIcon dots = new ImageIcon(getClass().getClassLoader().getResource("textures/dots.jpg"));

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
        centerPanel.setLayout(new GridBagLayout());

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

        JButton mp4Btn = new JButton(resizeIcon(dots, 30, 20));
        mp4Btn.setPreferredSize(new Dimension(30, 20));

        JButton folderBtn = new JButton(resizeIcon(dots, 30, 20));
        folderBtn.setPreferredSize(new Dimension(30, 20));

        //JTextField
        JTextField frameJump = new JTextField(5);
        frameJump.setBorder(blackline);

        //JTextArea
        JTextArea mp4Dir = new JTextArea(1,15);
        mp4Dir.setBorder(blackline);

        JTextArea folderDir = new JTextArea(1,15);
        folderDir.setBorder(blackline);

        /*add labels to directional panel*/
        //center && adds position for each component
        //gbc changes for addmp4
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(addmp4, gbc);
        //gbc changes for mp4Dir
        gbc.gridy++;
        centerPanel.add(mp4Dir, gbc);
        //gbc change for mp4 dots
        gbc.gridx = 1;
        centerPanel.add(mp4Btn, gbc);
        //gbc change for addFolder
        gbc.gridx = 0;
        gbc.gridy++;
        centerPanel.add(addFolder, gbc);
        //gbc change for FolderDir
        gbc.gridy++;
        centerPanel.add(folderDir, gbc);
        //gbc change for folder dot
        gbc.gridx = 1;
        centerPanel.add(folderBtn, gbc);
        //gbc changes for addJump
        gbc.gridx = 0;
        gbc.gridy++;
        centerPanel.add(addJump, gbc);
        //gbc changes for frameJump
        gbc.gridy++;
        centerPanel.add(frameJump, gbc);

        //north
        northPanel.add(title);

        //south
        southPanel.add(done);

        /*adds directional panel to main panel*/
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        //set textarea to uneditable
        mp4Dir.setEditable(false);
        folderDir.setEditable(false);

        window.setContentPane(mainPanel);
        window.setVisible(true);

        //add button actions
        folderBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }

    //Resizes imageicon
    private Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
