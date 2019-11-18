import java.awt.*;
import java.awt.event.*;
import java.security.Guard;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;


public class GUI extends JFrame
{   
    private Map<TouristKey, Integer> journal;
    
    private DefaultTableModel tableModel;
    private JTable table1;
    Object[][] array = new String[0][0];
    // Заголовки столбцов
    private Object[] columnsHeader = new String[] {"#","Шифр маршрута", "Страна","Стоимость"};

    public GUI (TouristJournal turJornal, Set quantity) 
    {
        super("Туристические маршруты");

        array = turJornal.putTouristJournal();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Создание стандартной модели
        tableModel = new DefaultTableModel();
        // Определение столбцов
        tableModel.setColumnIdentifiers(columnsHeader);
        // Наполнение модели данными
        for (int i = 0; i < array.length; i++)
            tableModel.addRow(array[i]);

        // Создание таблицы на основании модели данных
        table1 = new JTable(tableModel);
        // Создание кнопки добавления строки таблицы
        JButton add = new JButton("Добавить");
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Номер выделенной строки
                int idx = table1.getSelectedRow();
                // Вставка новой строки после выделенной
                for (int n = idx+1;n<tableModel.getRowCount();n++) tableModel.setValueAt(n+2,n,0); 
                tableModel.insertRow(idx + 1, new String[] {
                                              Integer.toString(idx + 2),"", "",""});
            }
        });
        // Создание кнопки удаления строки таблицы
        JButton remove = new JButton("Удалить");
        remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                // Номер выделенной строки
                int idx = table1.getSelectedRow();
                // Удаление выделенной строки
                for (int n = idx+1;n<tableModel.getRowCount();n++) tableModel.setValueAt(n,n,0); 
                tableModel.removeRow(idx);
                }
            });

        //this.setBounds(500,500,500,500);
        this.setLocation(725,300); 
        JMenuBar menuBar = new JMenuBar();  
        JMenu file = new JMenu("Файл");     //Кнопка в бар меню
        menuBar.add(file);
        this.setJMenuBar(menuBar);
        this.revalidate();

        JMenu sort = new JMenu("Сортировка");     //Кнопка в бар меню
        menuBar.add(sort);
        this.setJMenuBar(menuBar);
        this.revalidate();
        
        JMenuItem standartSort = sort.add(new JMenuItem("Стандарт")); 
        
        standartSort.addActionListener(new ActionListener() {    
            @Override
            public void actionPerformed(ActionEvent e) {
                
                    tableModel.setRowCount(0);
                    array = turJornal.putTouristJournal();
                    for (int i = 0; i < array.length; i++)
                        tableModel.addRow(array[i]);
                    
                    table1 = new JTable(tableModel);
                    table1.setAutoCreateRowSorter(true);
                    Box contents = new Box(BoxLayout.Y_AXIS);
                    contents.add(new JScrollPane(table1));
                    getContentPane().add(contents);
                }         
            });

        JMenuItem scoresort = sort.add(new JMenuItem("Убывание стоимости")); 
        
        scoresort.addActionListener(new ActionListener() {    
            @Override
            public void actionPerformed(ActionEvent e) {
                
                    tableModel.setRowCount(0);
                    array = turJornal.sortScoreDesc().putTouristJournal();
                    for (int i = 0; i < array.length; i++)
                        tableModel.addRow(array[i]);
                    
                    table1 = new JTable(tableModel);
                    table1.setAutoCreateRowSorter(true);
                    Box contents = new Box(BoxLayout.Y_AXIS);
                    contents.add(new JScrollPane(table1));
                    getContentPane().add(contents);
                }         
            });
        
        JMenuItem drugayasort = sort.add(new JMenuItem("Убывание названия страны")); 
            
        drugayasort.addActionListener(new ActionListener() {    
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                        tableModel.setRowCount(0);
                        array = turJornal.sortIdAscTeamCodeDesc().putTouristJournal();
                        for (int i = 0; i < array.length; i++)
                            tableModel.addRow(array[i]);
                        
                        table1 = new JTable(tableModel);
                        table1.setAutoCreateRowSorter(true);
                        Box contents = new Box(BoxLayout.Y_AXIS);
                        contents.add(new JScrollPane(table1));
                        getContentPane().add(contents);
                    }         
                });
                
        JMenuItem echedrugayasort = sort.add(new JMenuItem("Убывание шифра маршрута")); 
            
        echedrugayasort.addActionListener(new ActionListener() {    
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                        tableModel.setRowCount(0);
                        array = turJornal.sortTeamAscMonthDesc().putTouristJournal();
                        for (int i = 0; i < array.length; i++)
                            tableModel.addRow(array[i]);
                        
                        table1 = new JTable(tableModel);
                        table1.setAutoCreateRowSorter(true);
                        Box contents = new Box(BoxLayout.Y_AXIS);
                        contents.add(new JScrollPane(table1));
                        getContentPane().add(contents);
                    }         
                });

        

        JMenuItem open = file.add(new JMenuItem("Открыть"));
        open.addActionListener(new ActionListener() {       //Действие открытия
			@Override
        public void actionPerformed(ActionEvent e) {
            
            TouristJournal turJornal = new TouristJournal("Файл");
            BufferedReader inp = null;
            String[] data = new String[0];
            String line;
        try{
            inp = new BufferedReader(new FileReader("base.txt"));
            while ((line = inp.readLine()) != null){
                line = line.trim();
                if(line.equals("")) continue;
                data = line.split("\\s+");
                if (data[0] == " "){data[0] = "0";}
                if (data[1] == " "){data[0] = "0";}
                if (data[2] == " "){data[0] = "0";}
                turJornal.addTourist(new TouristKey(Integer.parseInt(data[0]),data[1], Integer.parseInt(data[2])),Integer.parseInt(data[2]));
                
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        
            tableModel.setRowCount(0);
            array = turJornal.putTouristJournal();
            for (int i = 0; i < array.length; i++)
            tableModel.addRow(array[i]);
            table1 = new JTable(tableModel);
        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JScrollPane(table1));
        getContentPane().add(contents);
        
        }});


        JMenuItem save = file.add(new JMenuItem("Сохранить")); //Кнопка сохранения
        save.addActionListener(new ActionListener() {       //Действие сохранения
			@Override
        public void actionPerformed(ActionEvent e) {
            Writer writer =  null;
        try {
            writer = new BufferedWriter(new FileWriter("base.txt"));
            
            //writer.append((turJornal.returnTouristJournalarray()));
            //НЕ ЕБИ СЕБЕ МОЗГ, ПРОСТО НАПИШИ ЧТЕНИЕ ПО ЭЛЕМЕНТУ В ДВУМЕРНОМ МАССИВЕ С ЗАПИСЬЮ В ФАЙЛ!
            //Сделяль
            for ( int n = 0;n<tableModel.getRowCount(); n++) { // тут хр. массив [][] из которого берем данныt 
                StringBuilder sb = new StringBuilder(); 
                for (int _n=1;_n<tableModel.getColumnCount();_n++) { 
                    Object data = tableModel.getValueAt(n,_n); 
                    if (data == "") sb.append("0"); 
                    else sb.append(data.toString()); 
                        sb.append(" "); 
                } 
                    writer.append(sb); 
                    writer.append("\n"); 
                }
            writer.flush();
            
            writer.flush();
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        }
        });

        

        file.addSeparator();    //Разделитель
        JMenuItem exit = file.add(new JMenuItem("Выйти"));  //Кнопка выхода
        
        exit.addActionListener(new ActionListener() {       //Действие выхода
            @Override
        public void actionPerformed(ActionEvent e) {
        System.exit(0);     //Выход из программы
        }
        });

        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        JPanel statusPanel = new JPanel();
        statusPanel.setBorder((Border) new BevelBorder(BevelBorder.LOWERED));
        frame.add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setPreferredSize(new Dimension(frame.getWidth(), 16));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        String countries =  "Всего маршрутов: " + Integer.toString(turJornal.size())  + " | " + Integer.toString(quantity.size());
        JLabel statusLabel = new JLabel(countries);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        

        // Размещение таблиц в панели с блочным расположением
        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JScrollPane(table1));
        getContentPane().add(contents);

        JPanel buttons = new JPanel();
        buttons.add(add);
        buttons.add(remove);
        // Вывод окна на экран
        setSize(400, 300);
        setVisible(true);


        // Вывод окна на экран
        setContentPane(contents);
        getContentPane().add(buttons); 
        getContentPane().add(statusLabel);

        setSize(1000, 500);
        this.setVisible(true);  //Программа видна
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);  //Кнопка выхода из программы (X)

    }


    public static void main(String[] args){

        TouristJournal turJornal = new TouristJournal("Журнал путевок #1");
        BufferedReader inp = null;
        String[] data = new String[0];
        Set<String> quantity = new TreeSet<String>();
        String line;
        try{
            inp = new BufferedReader(new FileReader("input.txt"));
            while ((line = inp.readLine()) != null){
                line = line.trim();
                if(line.equals("")) continue;
                data = line.split("\\s+");
                turJornal.addTourist(new TouristKey(Integer.parseInt(data[0]),data[1], Integer.parseInt(data[2])),Integer.parseInt(data[2]));
                quantity.add(data[1]);
            }
        } catch (Exception e){
            System.out.println(e);
        }

        GUI start = new GUI(turJornal,quantity);
    }
}
