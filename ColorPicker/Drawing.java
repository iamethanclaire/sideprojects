import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.file.*;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class Drawing extends JPanel {
    private JTextField redInputField;
    private int redValue;
    private JButton redMinus;
    private JButton redPlus;
    private JTextField greenInputField;
    private int greenValue;
    private JButton greenMinus;
    private JButton greenPlus;
    private JTextField blueInputField;
    private int blueValue;
    private JButton blueMinus;
    private JButton bluePlus;
    private int saveRed;
    private int saveGreen;
    private int saveBlue;
    private JButton saveButton;
    private JButton resetButton;
    private JFrame frame;
    private LinkedHashMap<String, Color> colorMap = new LinkedHashMap<>();

    public Drawing(JFrame frame) {
        this.frame = frame;
        setLayout(null);

        redInputField = new JTextField(Integer.toString(redValue), 3);
        redInputField.setBounds(70, 182, 40, 25);
        add(redInputField);

        redMinus = new JButton("-");
        redMinus.setBounds(110, 182, 60, 25);
        add(redMinus);

        redPlus = new JButton("+");
        redPlus.setBounds(165, 182, 60, 25);
        add(redPlus);

        greenInputField = new JTextField(Integer.toString(greenValue), 3);
        greenInputField.setBounds(70, 222, 40, 25);
        add(greenInputField);

        greenMinus = new JButton("-");
        greenMinus.setBounds(110, 222, 60, 25);
        add(greenMinus);

        greenPlus = new JButton("+");
        greenPlus.setBounds(165, 222, 60, 25);
        add(greenPlus);

        blueInputField = new JTextField(Integer.toString(blueValue), 3);
        blueInputField.setBounds(70, 262, 40, 25);
        add(blueInputField);

        blueMinus = new JButton("-");
        blueMinus.setBounds(110, 262, 60, 25);
        add(blueMinus);

        bluePlus = new JButton("+");
        bluePlus.setBounds(165, 262, 60, 25);
        add(bluePlus);

        saveButton = new JButton("Save");
        saveButton.setBounds(45, 300, 100, 25);
        add(saveButton);

        resetButton = new JButton("Reset");
        resetButton.setBounds(145, 300, 100, 25);
        add(resetButton);

        try {
            java.util.List<String> lines = Files.readAllLines(Paths.get("colors.txt"));

            for (String line : lines) {
                String[] parts = line.split(" ");
                String name = parts[0];
                int r = Integer.parseInt(parts[1]);
                int g = Integer.parseInt(parts[2]);
                int b = Integer.parseInt(parts[3]);
                Color thisColor = new Color(r, g, b);
                colorMap.put(name, thisColor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] colorNames = colorMap.keySet().toArray(new String[0]);

        if (colorNames.length > 0) {
            Color firstColor = colorMap.get(colorNames[0]);
            redValue = firstColor.getRed();
            greenValue = firstColor.getGreen();
            blueValue = firstColor.getBlue();

            saveRed = redValue; // useful for reset button
            saveGreen = greenValue;
            saveBlue = blueValue;

            redInputField.setText(Integer.toString(redValue));
            greenInputField.setText(Integer.toString(greenValue));
            blueInputField.setText(Integer.toString(blueValue));

            updateTitle();
        }

        JList<String> colorList = new JList<>(colorNames);
        colorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        colorList.setSelectedIndex(0);
        JScrollPane scrollPane = new JScrollPane(colorList);
        add(scrollPane);
        scrollPane.setBounds(230, 20, 100, 200);

        colorList.addListSelectionListener(e -> {
            String selected = colorList.getSelectedValue();
            Color chosenColor = colorMap.get(selected);

            redValue = chosenColor.getRed();
            greenValue = chosenColor.getGreen();
            blueValue = chosenColor.getBlue();

            saveRed = redValue;
            saveGreen = greenValue;
            saveBlue = blueValue;

            redInputField.setText(Integer.toString(redValue));
            greenInputField.setText(Integer.toString(greenValue));
            blueInputField.setText(Integer.toString(blueValue));
        });

        redInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    redValue = Integer.parseInt(redInputField.getText());
                } catch (NumberFormatException ex) {
                    redInputField.setText(Integer.toString(redValue));
                }

                if (Integer.parseInt(redInputField.getText()) < 255) {
                    redInputField.setText(redInputField.getText());
                } else {
                    redInputField.setText("255");
                }
                redValue = Integer.parseInt(redInputField.getText());
                updateTitle();
            }
        });

        redMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (redValue - 5 > 0) {
                    redValue -= 5;
                } else {
                    redValue = 0;
                }
                redInputField.setText(Integer.toString(redValue));
                updateTitle();
            }
        });

        redPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (redValue + 5 < 255) {
                    redValue += 5;
                } else {
                    redValue = 255;
                }
                redInputField.setText(Integer.toString(redValue));
                updateTitle();
            }
        });

        greenInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    greenValue = Integer.parseInt(greenInputField.getText());
                } catch (NumberFormatException ex) {
                    greenInputField.setText(Integer.toString(greenValue));
                }

                if (Integer.parseInt(greenInputField.getText()) < 255) {
                    greenInputField.setText(greenInputField.getText());
                } else {
                    greenInputField.setText("255");
                }
                greenValue = Integer.parseInt(greenInputField.getText());
                updateTitle();
            }
        });

        greenMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (greenValue - 5 > 0) {
                    greenValue -= 5;
                } else {
                    greenValue = 0;
                }
                greenInputField.setText(Integer.toString(greenValue));
                updateTitle();
            }
        });

        greenPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (greenValue + 5 < 255) {
                    greenValue += 5;
                } else {
                    greenValue = 255;
                }
                greenInputField.setText(Integer.toString(greenValue));
                updateTitle();
            }
        });

        blueInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    blueValue = Integer.parseInt(blueInputField.getText());
                } catch (NumberFormatException ex) {
                    blueInputField.setText(Integer.toString(blueValue));
                }

                if (Integer.parseInt(blueInputField.getText()) < 255) {
                    blueInputField.setText(blueInputField.getText());
                } else {
                    blueInputField.setText("255");
                }
                blueValue = Integer.parseInt(blueInputField.getText());
                updateTitle();
            }
        });

        blueMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (blueValue - 5 > 0) {
                    blueValue -= 5;
                } else {
                    blueValue = 0;
                }
                blueInputField.setText(Integer.toString(blueValue));
                updateTitle();
            }
        });

        bluePlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (blueValue + 5 < 255) {
                    blueValue += 5;
                } else {
                    blueValue = 255;
                }
                blueInputField.setText(Integer.toString(blueValue));
                updateTitle();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveRed = redValue;
                saveGreen = greenValue;
                saveBlue = blueValue;
                Color newColor = new Color(saveRed, saveGreen, saveBlue);
                updateTitle();

                colorMap.put(colorList.getSelectedValue(), newColor);
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redValue = saveRed;
                greenValue = saveGreen;
                blueValue = saveBlue;

                redInputField.setText(Integer.toString(redValue));
                greenInputField.setText(Integer.toString(greenValue));
                blueInputField.setText(Integer.toString(blueValue));

                colorMap.put(colorList.getSelectedValue(), new Color(redValue, greenValue, blueValue));

                repaint();
                updateTitle();
            }
        });

        setPreferredSize(new Dimension(400, 400));
    }

    private void updateTitle() {
        boolean saved = (redValue == saveRed) &&
                (greenValue == saveGreen) &&
                (blueValue == saveBlue);
        if (frame != null) {
            frame.setTitle("Color Picker" + (saved ? "" : "*"));
        }
    }

    public void writeOnExit() {
        try (FileWriter writer = new FileWriter("colors.txt", false)) {
            for (Map.Entry<String, Color> entry : colorMap.entrySet()) {
                Color color = entry.getValue();
                writer.write(
                        entry.getKey() + " " + color.getRed() + " " + color.getGreen() + " " + color.getBlue() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color selected = new Color(redValue, greenValue, blueValue);
        repaint();

        g.setColor(selected);
        g.fillRect(20, 20, 200, 120);

        g.setColor(Color.BLACK);
        g.drawString("Red: ", 20, 200);
        g.drawString("Green: ", 20, 240);
        g.drawString("Blue: ", 20, 280);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Color Picker");

        Drawing drawingPanel = new Drawing(frame);

        frame.setContentPane(drawingPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                drawingPanel.writeOnExit();
                frame.dispose();
            }
        });
        frame.setVisible(true);
    }

}