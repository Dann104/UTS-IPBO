package zoo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ZooFrame extends JFrame {

    // Menyimpan semua hewan
    private ArrayList<Animal> animals = new ArrayList<>();

    // Komponen input
    private JTextField txtName;
    private JTextField txtAge;
    private JComboBox<String> cmbAnimalType;
    private JTextField txtFurColor;
    private JCheckBox chkCanFly;

    // Area log
    private JTextArea txtLog;

    public ZooFrame() {

        setTitle("Digital Zoo Manager");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel utama
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        // =========================
        // PANEL FORM
        // =========================

        JPanel formPanel =
                new JPanel(new GridLayout(5, 2, 10, 10));

        // Name
        formPanel.add(new JLabel("Name:"));

        txtName = new JTextField();
        formPanel.add(txtName);

        // Age
        formPanel.add(new JLabel("Age:"));

        txtAge = new JTextField();
        formPanel.add(txtAge);

        // Animal Type
        formPanel.add(new JLabel("Animal Type:"));

        String[] animalTypes = {
                "Generic Mammal",
                "Generic Bird"
        };

        cmbAnimalType =
                new JComboBox<>(animalTypes);

        formPanel.add(cmbAnimalType);

        // Fur Color
        formPanel.add(new JLabel("Fur Color:"));

        txtFurColor = new JTextField();
        formPanel.add(txtFurColor);

        // Can Fly
        formPanel.add(new JLabel("Can Fly?"));

        chkCanFly = new JCheckBox();
        formPanel.add(chkCanFly);

        // =========================
        // TOMBOL
        // =========================

        JButton btnAddAnimal =
                new JButton("Add Animal");

        JButton btnViewAnimals =
                new JButton("View Animals");

        // =========================
        // PANEL LOG
        // =========================

        txtLog = new JTextArea();

        txtLog.setEditable(false);
        txtLog.setLineWrap(true);
        txtLog.setWrapStyleWord(true);

        JScrollPane scrollPane =
                new JScrollPane(txtLog);

        // =========================
        // EVENT TOMBOL
        // =========================

        btnAddAnimal.addActionListener(
                e -> addAnimal()
        );

        btnViewAnimals.addActionListener(
                e -> viewAnimals()
        );

        // =========================
        // PANEL TOMBOL
        // =========================

        JPanel buttonPanel =
                new JPanel();

        buttonPanel.add(btnAddAnimal);
        buttonPanel.add(btnViewAnimals);

        // =========================
        // PANEL ATAS
        // =========================

        JPanel topPanel =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        topPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        topPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        // =========================
        // GABUNGKAN PANEL
        // =========================

        panel.add(
                topPanel,
                BorderLayout.NORTH
        );

        panel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        add(panel);
    }

    // =====================================================
    // METHOD ADD ANIMAL
    // =====================================================

    private void addAnimal() {

        try {

            // Mengambil input
            String name =
                    txtName.getText().trim();

            int age =
                    Integer.parseInt(
                            txtAge.getText().trim()
                    );

            // Validasi nama
            if (name.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Nama hewan harus diisi!"
                );

                return;
            }

            // Mengambil jenis hewan
            String animalType =
                    (String)
                            cmbAnimalType.getSelectedItem();

            Animal animal;

            // =================================================
            // JIKA MAMMAL
            // =================================================

            if (animalType.equals("Generic Mammal")) {

                String furColor =
                        txtFurColor.getText().trim();

                if (furColor.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Fur Color harus diisi!"
                    );

                    return;
                }

                animal =
                        new Mammal(
                                name,
                                age,
                                furColor
                        );

            }

            // =================================================
            // JIKA BIRD
            // =================================================

            else {

                boolean canFly =
                        chkCanFly.isSelected();

                animal =
                        new Bird(
                                name,
                                age,
                                canFly
                        );
            }

            // =================================================
            // MASUKKAN KE ARRAYLIST
            // =================================================

            animals.add(animal);

            // =================================================
            // TAMPILKAN HASIL KE LOG
            // =================================================

            txtLog.append(
                    "Added a new "
                            + animalType
                            + "!\n"
            );

            txtLog.append(
                    "Info: "
                            + animal.getInfo()
                            + "\n"
            );

            txtLog.append(
                    "Sound: "
                            + animal.makeSound()
                            + "\n"
            );

            txtLog.append(
                    "--------------------------------\n"
            );

            // =================================================
            // BERSIHKAN INPUT
            // =================================================

            txtName.setText("");
            txtAge.setText("");
            txtFurColor.setText("");
            chkCanFly.setSelected(false);

        }

        catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Age harus berupa angka!"
            );
        }
    }

    // =====================================================
    // METHOD VIEW ANIMALS
    // =====================================================

    private void viewAnimals() {

        // Jika belum ada animal
        if (animals.isEmpty()) {

            txtLog.setText(
                    "Belum ada animal yang ditambahkan.\n"
            );

            return;
        }

        // Bersihkan log terlebih dahulu
        txtLog.setText(
                "========== ANIMAL LIST ==========\n\n"
        );

        int nomor = 1;

        // Ambil semua animal dari ArrayList
        for (Animal animal : animals) {

            txtLog.append(
                    nomor
                            + ". "
                            + animal.getInfo()
                            + "\n"
            );

            txtLog.append(
                    "Sound: "
                            + animal.makeSound()
                            + "\n"
            );

            txtLog.append(
                    "--------------------------------\n"
            );

            nomor++;
        }
    }

    // =====================================================
    // MAIN METHOD
    // =====================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            ZooFrame frame =
                    new ZooFrame();

            frame.setVisible(true);
        });
    }
}