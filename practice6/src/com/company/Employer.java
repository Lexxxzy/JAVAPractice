package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static com.company.Data.*;

/*Сотрудник
* имён, фамилий и отчества;
○ Дата рождения;
○ Должность (без генерального директора и т.п);
○ Город проживания;
○ Список заданий (тоже формируются случайным образом).
* */
public class Employer {
    private String Name;
    private String Surname;
    private String secondName;
    private String dateOfBirth;
    private String Position;
    private String City;
    private List<Task> Tasks = new ArrayList<>();

    //Конструктор для ввода вручную
    public Employer(String name, String surname, String secondName, String dateOfBirth, String position, String city) {
        Name = name;
        Surname = surname;
        this.secondName = secondName;
        this.dateOfBirth = dateOfBirth;
        Position = position;
        City = city;
        //Objects.requireNonNull(Tasks).add(tasks);
    }

    public Employer()
    {
        Name = manNames[new Random().nextInt(manNames.length)];
        Surname = Surnames[new Random().nextInt(Surnames.length)];
        secondName = secondNames[new Random().nextInt(secondNames.length)];
        dateOfBirth = String.valueOf(((int) (Math.random() * 30) + 1))
                + "." + ((int) (Math.random() * 12) + 1)
                + "." + (rnd(1980,2002));
        Position = jobs[new Random().nextInt(jobs.length)];
        City = Cities[new Random().nextInt(Cities.length)];
        new Task(this);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public List<Task> getTasks() {
        return Tasks;
    }

    public void setTasks(List<Task> tasks) {
        Tasks = tasks;
    }

    public int getSizeOfDoneTasks()
    {
        int i=0;
        for (Task t:Tasks) {
            if (!t.isStatus()) {
                i+=1;
            }
        }
        return i;
    }

    public void setTask(Task task) {
        Tasks.add(task);
    }

    public static int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    public void printInfo()
    {
        System.out.println(Name + "\t" + secondName+ "\t" + Surname + "\t\t\t" + Position);
    }

    public void printInfoWithTasks()
    {
        System.out.println(Name + "\t" + secondName+ "\t" + Surname + "\t\t\t" + Position + "\t\t\tКоличесвто выполненныйх заданий: " + getSizeOfDoneTasks());
    }

    public String getAllInfo()
    {
        return Name + "\t" + secondName+ "\t" + Surname + "\tДата рождения: " + dateOfBirth + "\tГород:"+City+"\tДолжность: " + Position + "\tВыполненные задания: " + getSizeOfDoneTasks() ;
    }

}
