import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ParkingSystemGUI extends JFrame {
    private ArrayList<String> parkedCars;
    private int totalParkingSlots;
    private int availableParkingSlots;

    public ParkingSystemGUI() {
        initializeParkingSystem();
        createGUI();
    }

    private void initializeParkingSystem() {
        // Using JOptionPane for input in GUI application
        String input = JOptionPane.showInputDialog("Enter the total number of parking slots:");

        try {
            totalParkingSlots = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input for parking slots. Exiting program.");
            System.exit(1);
        }

        availableParkingSlots = totalParkingSlots;
        parkedCars = new ArrayList<>();
    }

    private void createGUI() {
        setTitle("Parking System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 3));

        JButton parkButton = new JButton("Park a Car");
        JButton removeButton = new JButton("Remove a Car");
        JButton viewButton = new JButton("View Parked Cars");
        JButton exitButton = new JButton("Exit");

        parkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parkCar();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeCar();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewParkedCars();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitProgram();
            }
        });

        panel.add(parkButton);
        panel.add(removeButton);
        panel.add(viewButton);
        panel.add(exitButton);

        add(panel);
        setVisible(true);
    }

    private void parkCar() {
        if (availableParkingSlots > 0) {
            String licensePlate = JOptionPane.showInputDialog("Enter the license plate number of the car to park:");
            parkedCars.add(licensePlate);
            availableParkingSlots--;

            JOptionPane.showMessageDialog(this, "Car parked successfully.  Available parking slots: " + availableParkingSlots);
        } else {
            JOptionPane.showMessageDialog(this, "Sorry, no available parking slots.");
        }
    }

    private void removeCar() {
        if (!parkedCars.isEmpty()) {
            String licensePlate = JOptionPane.showInputDialog("Enter the license plate number of the car to remove:");

            if (parkedCars.remove(licensePlate)) {
                availableParkingSlots++;
                JOptionPane.showMessageDialog(this, "Car removed successfully. Available parking slots: " + availableParkingSlots);
            } else {
                JOptionPane.showMessageDialog(this, "Car not found. Please check the license plate number.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "No cars are currently parked.");
        }
    }

    private void viewParkedCars() {
        if (!parkedCars.isEmpty()) {
            StringBuilder carsList = new StringBuilder("List of currently parked cars:\n");
            for (String licensePlate : parkedCars) {
                carsList.append(licensePlate).append("\n");
            }
            JOptionPane.showMessageDialog(this, carsList.toString());
        } else {
            JOptionPane.showMessageDialog(this, "No cars are currently parked.");
        }
    }

    private void exitProgram() {
        JOptionPane.showMessageDialog(this, "Exiting the program. Goodbye!");
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ParkingSystemGUI();
            }
        });
    }
}
