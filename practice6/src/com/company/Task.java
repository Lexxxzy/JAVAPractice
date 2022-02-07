package com.company;

import java.util.Random;

import static com.company.Data.jobs;
import static com.company.Data.*;
import static com.company.View.tasks;

/*
название, кол-во
часов для выполнения, суть, гонорар за выполнение, статус (закрыта/открыта), кому
назначена (объект класса Employer)
 */
public class Task {
    private String Name;
    private int Hours;
    private int addOn;
    private boolean Status;
    private Employer employer;

    public Task(String name, int hours, int addOn, boolean status, Employer employer) {
        Name = name;
        Hours = hours;
        this.addOn = addOn;
        Status = status;
        this.employer = employer;
        employer.setTask(this);
    }

    public Task(Employer employer)
    {
        if(employer.getPosition().equals("Разработчик сайта          "))
        {
            Name = tasksForWeb[new Random().nextInt(tasksForWeb.length)];
        }
        if(employer.getPosition().equals("Работник серверной         "))
        {
            Name = tasksForServer[new Random().nextInt(tasksForServer.length)];
        }
        if(employer.getPosition().equals("Информационная безопасность"))
        {
            Name = tasksForInfobez[new Random().nextInt(tasksForInfobez.length)];
        }
        if(employer.getPosition().equals("Менеджер                   "))
        {
            Name = tasksForManager[new Random().nextInt(tasksForManager.length)];
        }
        if(employer.getPosition().equals("Менеджер по маркетингу     "))
        {
            Name = tasksForMarketingManager[new Random().nextInt(tasksForMarketingManager.length)];
        }
        if(employer.getPosition().equals("Охранник                   "))
        {
            Name = taskOhrannik[new Random().nextInt(taskOhrannik.length)];
        }
        if(employer.getPosition().equals("Секретарь                  "))
        {
            Name = tasksForSekretar[new Random().nextInt(tasksForSekretar.length)];
        }
        if(employer.getPosition().equals("Креативный директор        "))
        {
            Name = tasksForCreative[new Random().nextInt(tasksForCreative.length)];
        }
        if(employer.getPosition().equals("Уборщик                    "))
        {
            Name = tasksForCleaner[new Random().nextInt(tasksForCleaner.length)];
        }
        if(employer.getPosition().equals("Дизайнер                   "))
        {
            Name = tasksForDesigner[new Random().nextInt(tasksForDesigner.length)];
        }
        Hours = new Random().nextInt(30+1);
        this.addOn = Employer.rnd(1000,10000);
        Status = new Random().nextBoolean();
        this.employer = employer;
        employer.setTask(this);
        tasks.add(this);
    }
    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public int getAddOn() {
        return addOn;
    }

    public void setAddOn(int addOn) {
        this.addOn = addOn;
    }

    public int getHours() {
        return Hours;
    }

    public void setHours(int hours) {
        Hours = hours;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public void printTask()
    {
        System.out.println(Name+ "\t|"+ Hours  + " ч.|\t\t\t|\t  "
                + addOn +" Руб.\t\t  |  \t\t" + Status + "\t\t|\t\t"
                + employer.getName() + " "+employer.getSurname() +"|");
    }
}
