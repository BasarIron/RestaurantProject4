package com.grup7.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservationSystemGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Ana pencere oluştur
            JFrame frame = new JFrame("Rezervasyon Sistemi");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            // Ana panel oluştur
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());

            // Başlık ekle
            JLabel header = new JLabel("Rezervasyon Sistemine Hoş Geldiniz", JLabel.CENTER);
            header.setFont(new Font("Arial", Font.BOLD, 24));
            panel.add(header, BorderLayout.NORTH);

            // Tablo modelini oluştur
            DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Rezervasyon Kodu", "Müşteri Adı", "Masa Numarası", "Tarih"}, 0);
            JTable table = new JTable(tableModel);
            panel.add(new JScrollPane(table), BorderLayout.CENTER);

            // Butonlar için panel
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());
            JButton addButton = new JButton("Rezervasyon Ekle");
            JButton deleteButton = new JButton("Rezervasyon Sil");
            JButton viewLogsButton = new JButton("Logları Görüntüle");

            // Rezervasyon Ekle Butonu
            addButton.addActionListener(e -> {
                String reservationCode = JOptionPane.showInputDialog("Rezervasyon Kodunu Girin:");
                String customerName = JOptionPane.showInputDialog("Müşteri Adını Girin:");
                String tableNumber = JOptionPane.showInputDialog("Masa Numarasını Girin:");
                String date = JOptionPane.showInputDialog("Rezervasyon Tarihini Girin (YYYY-MM-DD):");

                if (reservationCode != null && customerName != null && tableNumber != null && date != null) {
                    tableModel.addRow(new Object[]{reservationCode, customerName, tableNumber, date});
                    JOptionPane.showMessageDialog(frame, "Yeni rezervasyon başarıyla eklendi!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Eksik bilgi girdiniz.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            });

            // Rezervasyon Sil Butonu
            deleteButton.addActionListener(e -> {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    tableModel.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(frame, "Rezervasyon başarıyla silindi!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Silmek için bir rezervasyon seçin.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            });

            // Logları Görüntüle Butonu
            viewLogsButton.addActionListener(e -> {
                String logs = "Sistem Logları:\n" +
                        "2025-05-13 19:37:18 - Rezervasyon Kapandı (Müşteri: Başar Demir, Masa: Masa-1)\n" +
                        "2025-05-14 08:50:40 - Sistem Başlatıldı.\n" +
                        "2025-05-14 09:00:19 - Rezervasyon Eklendi.\n";

                JTextArea logArea = new JTextArea(logs);
                logArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(logArea);

                JFrame logFrame = new JFrame("Sistem Logları");
                logFrame.setSize(600, 400);
                logFrame.add(scrollPane);
                logFrame.setVisible(true);
            });

            // Butonları ekle
            buttonPanel.add(addButton);
            buttonPanel.add(deleteButton);
            buttonPanel.add(viewLogsButton);
            panel.add(buttonPanel, BorderLayout.SOUTH);

            // Paneli pencereye ekle
            frame.add(panel);
            frame.setVisible(true);
        });
    }
}