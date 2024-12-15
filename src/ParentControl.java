import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalTime;
import java.util.HashMap;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * ParentControl class allows parents to set limitations, view statistics, and manage pet revival.
 */
public class ParentControl extends JLayeredPaneWithBackground {
    private static final long serialVersionUID = 1L;
    private JCheckBox enableLimitationsCheckBox;
    private JSpinner startTimeSpinner;
    private JSpinner endTimeSpinner;
    private JLabel totalPlayTimeLabel;
    private JLabel averagePlayTimeLabel;
    private JButton resetStatsButton;
    private JButton revivePetButton;
    private JComboBox<String> saveFilesComboBox;
    private JButton backButton;

    // Data persistence
    private boolean limitationsEnabled;
    private LocalTime allowedStartTime;
    private LocalTime allowedEndTime;
    private long totalPlayTime; // in milliseconds
    private int sessionCount;
    private HashMap<String, Game> savedGames;

    /**
     * Constructor for ParentControl.
     *
     * @throws IOException If there's an error loading resources.
     */
    public ParentControl() throws IOException {
        super("images/ParentalControl.png"); // Ensure this image exists
        this.setPreferredSize(new Dimension(700, 700));
        this.setLayout(null);
        this.savedGames = new HashMap<>();

        // Load saved data
        loadParentSettings();

        // Initialize UI components
        initializeComponents();

        // Start updating the save files combo box
        updateSaveFilesComboBox();
        startSaveFilesTimer();

        this.setVisible(true);
    }

    /**
     * Initializes all UI components.
     */
    private void initializeComponents() {
        // Back Button
        BufferedImage backImg = null;
        try {
            backImg = ImageIO.read(new File("images/buttons/Back.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        backButton = new JButton(new ImageIcon(backImg));
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setContentAreaFilled(false);
        backButton.setBounds(0, 0, 81, 81);
        backButton.addActionListener(e -> {
            // Implement navigation logic
            ParentControl.this.setVisible(false);
        });
        this.add(backButton);

        // Enable Limitations Checkbox
        enableLimitationsCheckBox = new JCheckBox("Enable Play Time Limitations");
        enableLimitationsCheckBox.setBounds(50, 130, 300, 25);
        enableLimitationsCheckBox.setSelected(limitationsEnabled);
        enableLimitationsCheckBox.addActionListener(e -> {
            limitationsEnabled = enableLimitationsCheckBox.isSelected();
            saveParentSettings();
        });
        this.add(enableLimitationsCheckBox);

        // Start Time Spinner
        JLabel startTimeLabel = new JLabel("Allowed Start Time:");
        startTimeLabel.setBounds(50, 170, 150, 25);
        this.add(startTimeLabel);

        SpinnerDateModel startTimeModel = new SpinnerDateModel();
        startTimeSpinner = new JSpinner(startTimeModel);
        JSpinner.DateEditor startTimeEditor = new JSpinner.DateEditor(startTimeSpinner, "HH:mm");
        startTimeSpinner.setEditor(startTimeEditor);
        startTimeSpinner.setBounds(200, 170, 100, 25);
        startTimeSpinner.setValue(java.sql.Time.valueOf(allowedStartTime));
        startTimeSpinner.addChangeListener(e -> {
            allowedStartTime = LocalTime.parse(startTimeEditor.getFormat().format(startTimeSpinner.getValue()));
            saveParentSettings();
        });
        this.add(startTimeSpinner);

        // End Time Spinner
        JLabel endTimeLabel = new JLabel("Allowed End Time:");
        endTimeLabel.setBounds(50, 210, 150, 25);
        this.add(endTimeLabel);

        SpinnerDateModel endTimeModel = new SpinnerDateModel();
        endTimeSpinner = new JSpinner(endTimeModel);
        JSpinner.DateEditor endTimeEditor = new JSpinner.DateEditor(endTimeSpinner, "HH:mm");
        endTimeSpinner.setEditor(endTimeEditor);
        endTimeSpinner.setBounds(200, 210, 100, 25);
        endTimeSpinner.setValue(java.sql.Time.valueOf(allowedEndTime));
        endTimeSpinner.addChangeListener(e -> {
            allowedEndTime = LocalTime.parse(endTimeEditor.getFormat().format(endTimeSpinner.getValue()));
            saveParentSettings();
        });
        this.add(endTimeSpinner);

        // Play Time Statistics
        JLabel statisticsLabel = new JLabel("Play Time Statistics:");
        statisticsLabel.setBounds(50, 260, 200, 25);
        this.add(statisticsLabel);

        totalPlayTimeLabel = new JLabel("Total Play Time: " + formatTime(totalPlayTime));
        totalPlayTimeLabel.setBounds(50, 290, 300, 25);
        this.add(totalPlayTimeLabel);

        averagePlayTimeLabel = new JLabel("Average Session Time: " + formatTime(calculateAveragePlayTime()));
        averagePlayTimeLabel.setBounds(50, 320, 300, 25);
        this.add(averagePlayTimeLabel);

        resetStatsButton = new JButton("Reset Statistics");
        resetStatsButton.setBounds(50, 350, 150, 30);
        resetStatsButton.addActionListener(e -> {
            totalPlayTime = 0;
            sessionCount = 0;
            saveParentSettings();
            updateStatisticsLabels();
        });
        this.add(resetStatsButton);

        // Revive Pet Section
        JLabel revivePetLabel = new JLabel("Revive Pet:");
        revivePetLabel.setBounds(50, 400, 200, 25);
        this.add(revivePetLabel);

        saveFilesComboBox = new JComboBox<>();
        saveFilesComboBox.setBounds(50, 430, 200, 25);
        saveFilesComboBox.addActionListener(e -> {
            String selectedSave = (String) saveFilesComboBox.getSelectedItem();
            if (selectedSave != null && !selectedSave.equals("Select a save file") && savedGames.containsKey(selectedSave)) {
                revivePetButton.setEnabled(true);
            } else {
                revivePetButton.setEnabled(false);
            }
        });
        this.add(saveFilesComboBox);

        revivePetButton = new JButton("Revive Selected Pet");
        revivePetButton.setBounds(270, 430, 150, 30);
        revivePetButton.setEnabled(false);
        revivePetButton.addActionListener(e -> reviveSelectedPet());
        this.add(revivePetButton);
    }

    /**
     * Starts the timer to update the save files combo box periodically.
     */
    private void startSaveFilesTimer() {
        new Timer(1000, e -> updateSaveFilesComboBox()).start();
    }

    /**
     * Updates the saveFilesComboBox with the current list of save files.
     */
    private void updateSaveFilesComboBox() {
        File saveDir = new File("saves");
        String[] saveFiles = saveDir.list((dir, name) -> name.toLowerCase().endsWith(".dat"));

        if (saveFiles != null) {
            // Save the current selection
            String currentSelection = (String) saveFilesComboBox.getSelectedItem();
            saveFilesComboBox.removeAllItems();

            // Add default option
            saveFilesComboBox.addItem("Select a save file");

            // Update the savedGames map
            savedGames.clear();

            for (String fileName : saveFiles) {
                String displayName = fileName.substring(0, fileName.lastIndexOf('.'));
                saveFilesComboBox.addItem(displayName);
                // Load the Game object for potential revival
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("saves/" + fileName))) {
                    Game game = (Game) ois.readObject();
                    savedGames.put(displayName, game);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            // Restore the selection if possible
            if (currentSelection != null && ((DefaultComboBoxModel<String>) saveFilesComboBox.getModel()).getIndexOf(currentSelection) != -1) {
                saveFilesComboBox.setSelectedItem(currentSelection);
            } else {
                saveFilesComboBox.setSelectedIndex(0);
                revivePetButton.setEnabled(false);
            }
        } else {
            saveFilesComboBox.removeAllItems();
            saveFilesComboBox.addItem("No save files available");
            savedGames.clear();
            revivePetButton.setEnabled(false);
        }
    }

    /**
     * Loads parent settings from a file.
     */
    private void loadParentSettings() {
        File settingsFile = new File("parent_settings.dat");
        if (settingsFile.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(settingsFile))) {
                limitationsEnabled = ois.readBoolean();
                allowedStartTime = (LocalTime) ois.readObject();
                allowedEndTime = (LocalTime) ois.readObject();
                totalPlayTime = ois.readLong();
                sessionCount = ois.readInt();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                setDefaultSettings();
            }
        } else {
            setDefaultSettings();
        }
    }

    /**
     * Saves parent settings to a file.
     */
    private void saveParentSettings() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("parent_settings.dat"))) {
            oos.writeBoolean(limitationsEnabled);
            oos.writeObject(allowedStartTime);
            oos.writeObject(allowedEndTime);
            oos.writeLong(totalPlayTime);
            oos.writeInt(sessionCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets default settings if no saved settings are found.
     */
    private void setDefaultSettings() {
        limitationsEnabled = false;
        allowedStartTime = LocalTime.of(0, 0);
        allowedEndTime = LocalTime.of(23, 59);
        totalPlayTime = 0;
        sessionCount = 0;
    }

    /**
     * Updates the statistics labels to reflect current data.
     */
    private void updateStatisticsLabels() {
        totalPlayTimeLabel.setText("Total Play Time: " + formatTime(totalPlayTime));
        averagePlayTimeLabel.setText("Average Session Time: " + formatTime(calculateAveragePlayTime()));
    }

    /**
     * Formats time from milliseconds to HH:mm:ss format.
     *
     * @param millis Time in milliseconds.
     * @return Formatted time string.
     */
    private String formatTime(long millis) {
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        return String.format("%02d:%02d:%02d", hours, minutes % 60, seconds % 60);
    }

    /**
     * Calculates the average play time per session.
     *
     * @return Average play time in milliseconds.
     */
    private long calculateAveragePlayTime() {
        if (sessionCount == 0) return 0;
        return totalPlayTime / sessionCount;
    }

    /**
     * Revives the selected pet.
     */
    private void reviveSelectedPet() {
        String selectedSave = (String) saveFilesComboBox.getSelectedItem();
        if (selectedSave != null && !selectedSave.equals("Select a save file") && savedGames.containsKey(selectedSave)) {
            Game game = savedGames.get(selectedSave);
            // Revive pet
            game.getPet().revive();
            // Save the updated game
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("saves/" + selectedSave + ".dat"))) {
                oos.writeObject(game);
                JOptionPane.showMessageDialog(this, "Pet has been revived successfully!", "Revive Pet", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to save the revived pet.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No save file selected or save file not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Records play time statistics upon application close.
     *
     * @param sessionPlayTime The play time for the current session in milliseconds.
     */
    public void recordSessionPlayTime(long sessionPlayTime) {
        totalPlayTime += sessionPlayTime;
        sessionCount++;
        saveParentSettings();
    }

    /**
     * Checks if the current time is within the allowed play time range.
     *
     * @return True if allowed to play, false otherwise.
     */
    public boolean isPlayAllowed() {
        if (!limitationsEnabled) {
            return true;
        }
        LocalTime now = LocalTime.now();
        if (allowedStartTime.isBefore(allowedEndTime)) {
            // Normal time range
            return !now.isBefore(allowedStartTime) && !now.isAfter(allowedEndTime);
        } else {
            // Overnight time range
            return now.isAfter(allowedStartTime) || now.isBefore(allowedEndTime);
        }
    }
}