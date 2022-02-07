package practice5;
/*Описать структуру данных класса Worker (работник) некой
        компании со следующими свойствами:
        ● Имя (отдельное поле)
        ● Фамилия (отдельное поле)
        ● Возраст
        ● Пол
        ● Имя кота (если есть)
        ● Название отдела
        ● Должность
        ● Зарплата
        ● Премия (если есть)
        ● Длительность работы в компании (в годах)*/


import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static practice5.Storage.*;

public class Worker {
    public String name;
    public String secondName;
    public int age;
    public char sex;
    public String catName;
    public String work;
    public String positionAtWork;
    public int salary;
    public int moreSalary;
    public int durationOfWork;

    public Worker()
    {
        this.age = 17 + (int) (Math.random() * 50 + 1);

        if ((Math.random() < 0.5))
        {
            this.name = womanNames[new Random().nextInt(womanNames.length)];
            this.secondName = secondNames[new Random().nextInt(secondNames.length)];
            this.sex = 'Ж';
        } else
        {
            this.name = manNames[new Random().nextInt(manNames.length)];
            this.secondName = secondNames[new Random().nextInt(secondNames.length)];
            this.sex = 'М';
        }

        if ((Math.random() > 0.5))
        {
            this.catName = CAT_NAMES[new Random().nextInt(CAT_NAMES.length)];
        }
        else
        {
            this.catName = "    ";
        }


        this.positionAtWork = jobs[new Random().nextInt(jobs.length)];

        switch (this.positionAtWork) {
            case "Software Developer  ","Data Scientist      " -> this.work = otdel[0];
            case "Information Security", "Security Guard      " -> this.work = otdel[2];
            case "Junior Manager      ", "Marketing Manager   " -> this.work = otdel[3];
            case "Mechanical Engineer " -> this.work = otdel[1];
        }

        this.salary = 150 + ((int) (Math.random() * 1500 + 1));

        if ((Math.random() < 0.5))
        {
            this.moreSalary = (int) (Math.random() * 100);
        }
        this.durationOfWork = (int) (Math.random() * 10) + 1;
    }

    public int getSalary()
    {
        return this.salary;
    }
    public int getAge()
    {
        return this.age;
    }
    public String getWork()
    {
        return this.work;
    }

    public void printWorker()
    {
        System.out.println( this.name + "\t\t" +
                this.secondName + "\t\t"+
                age + "(лет)" + "\t\t " +
                sex  + "\t\t"+
                catName + "\t\t\t"+
                work + "\t\t\t"+
                positionAtWork + "\t " +
                salary + "$" + "\t\t"+
                moreSalary + "$" +"\t\t\t"+
                durationOfWork + "(лет)");
    }

}
