package com.example.testjavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;


public class HelloController implements Initializable {
    @FXML
    private Label catAge;
    @FXML
    private Label catBreed;
    @FXML
    private Label catDate;
    @FXML
    private Label catName;
    @FXML
    private TextField nameOfCat;
    @FXML
    private TextField addMed1;
    @FXML
    private TextField addMed2;
    @FXML
    private TextField addMed3;
    @FXML
    private ComboBox<String> cbCats;
    @FXML
    private TableView<Med> table;
    @FXML
    private TableColumn tableDate;
    @FXML
    private TableColumn tableName;
    @FXML
    private TableColumn tableType;
    @FXML
    private Pane main;
    @FXML
    private TextField addAge;
    @FXML
    private TextField addBreed;
    @FXML
    private TextField addDate;
    @FXML
    private TextField addName;
    @FXML
    private Pane addPanel;
    @FXML
    private Pane addPanel2;

    //по дефолту добавляем двух кошек
    public Cat simka = new Cat("Симка","01.03.2021","1","Дворняга",
                          "21.02.2020","Прививка","Ринотрахеит");
    public Cat monya = new Cat("Моня","04.04.2005","16","Персидская",
                          "07.05.2005","Прививка","Калицивироз");
    //листы для хранения и автоматического обновление котов
    ObservableList<String> catsNames = FXCollections.observableArrayList();
    ObservableList<Cat> cats = FXCollections.observableArrayList(simka,monya);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //когда программа только запускается показывается только экран с кошками
        main.setVisible(true);
        addPanel2.setVisible(false);
        addPanel.setVisible(false);

        //инициализация таблицы и связь с классом Med
        tableDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        tableType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("Title"));

        //тест добавления прививки коту
        monya.addMed("12.05.2006","Прививка","От бешенства");

        //добавить кота в лист потом в комбобокс
        for (Cat c:cats) {
            catsNames.add(c.getName());
        }
        cbCats.setItems(catsNames);

        int i = 0;
        catAge.setText(cats.get(i).catAge);
        catBreed.setText(cats.get(i).Breed);
        catDate.setText(cats.get(i).DateOfBirth);
        catName.setText(cats.get(i).catName);
        table.getItems().addAll(cats.get(i).catMeds);
    }

    public ObservableList<String> getCatsNames() {
        return catsNames;
    }

    public void setCatsNames(ObservableList<String> catsNames) {
        this.catsNames = catsNames;
    }

    public ObservableList<Cat> getCats() {
        return cats;
    }

    public void setCats(ObservableList<Cat> cats) {
        this.cats = cats;
    }

    //метод кнопки по клику на имя кота из комбобокса
    public void Select(ActionEvent actionEvent) {
        //показывается только экран с информацией о коте
        main.setVisible(true);
        addPanel.setVisible(false);
        addPanel2.setVisible(false);
        table.getItems().clear();
        String s = cbCats.getSelectionModel().getSelectedItem().toString();

        //поиск нужного кота
        int i =0;
        for (Cat c:cats) {
            if (s.equals(c.catName)) break;
            i++;
        }

        //загрузка данных о коте
        catAge.setText(cats.get(i).catAge);
        catBreed.setText(cats.get(i).Breed);
        catDate.setText(cats.get(i).DateOfBirth);
        catName.setText(cats.get(i).catName);
        table.getItems().addAll(cats.get(i).catMeds);

    }

    //метод клика по кнопке "добавить кота" из меню
    @FXML
    void addCat(ActionEvent event) {
        main.setVisible(false);
        addPanel2.setVisible(false);
        addPanel.setVisible(true);
    }

    //метод клика по кнопке "добавить кота"
    @FXML
    void addCatToList(ActionEvent event) {
        //получение данных из полей ввода
        String name = addName.getText();
        String age = addAge.getText();
        String date = addDate.getText();
        String breed= addBreed.getText();

        //проверяем все ли поля заполнены
        if(name.equals("") || age.equals("") || date.equals("") || breed.equals("") || catsNames.contains(name)) return;
        cats.add(new Cat(name,date,age,breed,"-","-","-"));
        catsNames.add(name);
        addPanel.setVisible(false);
        cbCats.getItems().get(catsNames.size());
        main.setVisible(true);
    }

    //метод клика по кнопке "добавить прививку" в меню (видимость только этого экрана)
    @FXML
    void addMed(ActionEvent event) {
        main.setVisible(false);
        addPanel.setVisible(false);
        addPanel2.setVisible(true);
    }

    //добвление прививки опреденному коту
    @FXML
    public void addToMedList(ActionEvent actionEvent) {
        String name = addName.getText();
        String med1 = addMed1.getText();
        String med2 = addMed2.getText();
        String med3 = addMed3.getText();

        int j =-1;
        for (Cat c:cats) {
            if (name.equals(c.catName)) break;
            j++;
        }

        cats.get(j).addMed(med1,med2,med3);
        addPanel2.setVisible(false);
        cbCats.getItems().get(j);
        Select(actionEvent);
        main.setVisible(true);
    }
}
