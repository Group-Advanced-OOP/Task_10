import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

// iText Imports
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public LMSReportGenerator() {
    buildMainFrame();
}

private void buildMainFrame() {
    mainFrame = new JFrame("LMS Report Generator");
    mainFrame.setSize(900, 600);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setLayout(new BorderLayout(10, 10));
    mainFrame.getContentPane().setBackground(new Color(245, 245, 255));

    mainFrame.add(buildTopPanel(), BorderLayout.NORTH);
    mainFrame.add(new JScrollPane(buildTable()), BorderLayout.CENTER);

    mainFrame.setLocationRelativeTo(null);
    mainFrame.setVisible(true);
}

private JPanel buildTopPanel() {
    JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
    topPanel.setBackground(new Color(245, 245, 255));

    loadButton = buildLoadButton();
    exportButton = buildExportButton();
    exportButton.setEnabled(false);

    statusLabel = new JLabel("Ready");

    topPanel.add(loadButton);
    topPanel.add(exportButton);
    topPanel.add(statusLabel);

    return topPanel;
}

private JButton buildLoadButton() {
    JButton btn = new JButton("Load Data");
    btn.setFont(new java.awt.Font("Comic Sans MS", java.awt.Font.BOLD, 14));
    btn.setBackground(new Color(33, 150, 243));
    btn.setForeground(Color.WHITE);
    btn.setFocusPainted(false);
    btn.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
    btn.addActionListener(e -> loadDataFromDatabase());
    return btn;
}
