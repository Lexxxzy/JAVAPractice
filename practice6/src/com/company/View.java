package com.company;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Stream;

import static com.company.Report.writeInfo;

public class View {
    public String helloText = "\n\n" + "Выберете, пожалуйста что хотите сделать (Введите цифру от 1 до 8): ";
    public String Menu = "\t1. Добавить нового сотрудника. \n " +
                         "\t2. Сгенерировать нового сотрудника \n" +
                         "\t3. Добавить задание. \n" +
                         "\t4. Просмотр списка выполненных и открытых заданий. \n" +
                         "\t5. Вывод топ-3 сотрудников по выполнению заданий в месяц. \n" +
                         "\t6. Вывод задания с наибольшим гонораром за выполнение. \n" +
                         "\t7. Cформировать отчёт о выполненных заданиях за месяц и сохраненить его в текстовый файл.\n" +
                         "\t8. Сохраненить список сотрудников в файл. \n" +
                         "\nВведите цифру: ";

    public static ArrayList<Employer> employers = new ArrayList<>();
    public static ArrayList<Task> tasks = new ArrayList<>();


    public void showEmployers()
    {
        System.out.println("\n\n№"+"    Имя" + "         Отчество   " + "        Фамилия     " + "        Должность  ");
        int i = 0;
        for (Employer e: employers) {
            System.out.print(i+ ". ");
            e.printInfo();
            i++;
        }
    }
    public void addEmployer()
    {
        Scanner employerScanner = new Scanner(System.in);
        System.out.print("Введите имя сотрудника: " );
        String name = employerScanner.nextLine();
        System.out.print("Введите фамилию сотрудника: " );
        String Surname = employerScanner.nextLine();
        System.out.print("Введите отчество сотрудника: " );
        String secondName = employerScanner.nextLine();
        System.out.print("Введите дату рождения сотрудника(в формате 01.01.1999): " );
        String dateOfBirth = employerScanner.nextLine();
        System.out.print("Введите должность сотрудника: " );
        String Position = employerScanner.nextLine();
        System.out.print("Введите город проживания сотрудника: " );
        String City = employerScanner.nextLine();

        Employer newEmployer = new Employer(name,Surname,secondName,dateOfBirth,Position,City);
        employers.add(newEmployer);
        System.out.println("Сотрудник добавлен! Нажмите 1 для выхода в меню или 2 для завершения работы программы: ");
        writeInfo("["+this.getClass().getSimpleName()+"][“addEmployer”] Пользователь ввёл" +
                "следующие данные:\n" + name + " "+Surname+ " "+secondName+" "+dateOfBirth+ " "+Position+" "+ City+ "\n\n\n");
        Scanner sc = new Scanner(System.in);
        int answer = sc.nextInt();
        if(answer==1) getMenu();
        else System.exit(0);
    }

    private void generateEmloyer()
    {
        Employer newEmployer = new Employer();
        employers.add(newEmployer);
        System.out.print("Сотрудник сгенерирован и добавлен! \nНажмите 1 для выхода в меню или 2 для завершения работы программы: ");
       Scanner sc = new Scanner(System.in);
        int answer = sc.nextInt();
        if(answer==1) getMenu();
        else System.exit(0);
    }

    private void addTask()
    {
        Scanner employerScanner = new Scanner(System.in);
        System.out.print("Введите название задания: " );
        String name = employerScanner.nextLine();
        System.out.print("Введите количество часов на выполнение этого задания: " );
        int hours = employerScanner.nextInt();
        System.out.print("Введите сколько будет надбавка за выполненное задание: " );
        int addOn = employerScanner.nextInt();
        System.out.print("Введите открыто ли это задание (false) или уже выполнено (true): " );
        boolean status = employerScanner.nextBoolean();
        showEmployers();
        System.out.print("Введите какому сотруднику пренадлежит задание (введите его номер): " );
        int addTaskToEmployer = employerScanner.nextInt();

        Task task = new Task(name,hours,addOn,status, employers.get(addTaskToEmployer));
        tasks.add(task);

        writeInfo("["+this.getClass().getSimpleName()+"][“addTask”] Пользователь ввёл " +
                "следующие данные:\n" + name  + " "+hours+ " "+addOn+" "+status+" "+addTaskToEmployer + "\n\n\n");
        System.out.print("Задание добавлено! \nНажмите 1 для выхода в меню или 2 для завершения работы программы: ");
        Scanner sc = new Scanner(System.in);
        int answer = sc.nextInt();

        if(answer==1) getMenu();
        else System.exit(0);
    }

    public void getTasks()
    {
        System.out.println("\t\t\t\tНазвание задания\t\t\t\t"+"\t\tCколько занимает часов\t"
                +"Надбавка за выполнение\t"+"Открыто ли задание\t\t\t" + "   Кто делает");
        Stream<Task> streamTasks = tasks.stream();
        streamTasks.forEach(Task::printTask);
        System.out.print("\nНажмите 1(для выхода в меню) или 2(для завершения работы программы): ");
        Scanner sc = new Scanner(System.in);
        int answer = sc.nextInt();

        if(answer==1) getMenu();
        else System.exit(0);
    }

    public void getWorkersTop()
    {
        Stream<Employer> employerStream = employers.stream();
        employerStream.sorted(Comparator.comparing(Employer::getSizeOfDoneTasks).reversed()).limit(3).forEach(Employer::printInfoWithTasks);
        System.out.print("\nНажмите 1(для выхода в меню) или 2(для завершения работы программы): ");
        Scanner sc = new Scanner(System.in);
        int answer = sc.nextInt();

        if(answer==1) getMenu();
        else System.exit(0);
    }

    public void getTopTask()
    {
        System.out.println("\t\t\t\tНазвание задания\t\t\t\t"+"\t\tCколько занимает часов\t"
                +"Надбавка за выполнение\t"+"Открыто ли задание\t\t\t" + "   Кто делает");
        Stream<Task> streamTasks = tasks.stream();
        streamTasks.sorted(Comparator.comparing(Task::getAddOn).reversed()).findFirst().get().printTask();
        System.out.print("\nНажмите 1(для выхода в меню) или 2(для завершения работы программы): ");
        Scanner sc = new Scanner(System.in);
        int answer = sc.nextInt();

        if(answer==1) getMenu();
        else System.exit(0);
    }

    public void doneTasksToFile()
    {
        try {
            OutputStream tasksFile = new FileOutputStream("doneTasks.txt");
            OutputStreamWriter donetasks = new OutputStreamWriter(tasksFile, StandardCharsets.UTF_8);
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tВЫПОЛНЕННЫЕ ЗАДАНИЯ (ОТЧЕТ)");
            System.out.println("\t\t\t\tНазвание задания\t\t\t\t"+"\t\tCколько заняло часов\t"
                    +"Надбавка за выполнение\t"+"Открыто ли задание\t\t\t" + "   Кто сделал");
            for (Task t : tasks) {
                if(!t.isStatus())
                {
                    t.printTask();
                    writeInfo("["+this.getClass().getSimpleName()+"][“doneTasksToFile()”] Пользователь получил " +
                            "следующие данные:\n" + t.getName() + " "+t.isStatus()+" "+t.getAddOn()+ " "+t.getEmployer().getAllInfo().strip() + "\n\n\n");
                    donetasks.append(t.getName()).append("\n");
                }
            }
            donetasks.close();

            System.out.print("\u001b[38;5;10mВсе выполненные задания успешно занесены в файл!\u001b[38;5;0m\nНажмите 1(для выхода в меню) или 2(для завершения работы программы):");
            Scanner sc = new Scanner(System.in);
            int answer = sc.nextInt();

            if(answer==1) getMenu();
            else System.exit(0);
        } catch (IOException e) {
            System.out.println("\u001b[38;5;196mОшибка при добавлении задания в файл! Задание НЕ были добавлены\u001b[38;5;0m");
            writeInfo("[“View”][“doneTasksToFile()”] Произошла ошибка, см.\n" +
                    "описание:"+ Arrays.toString(e.getStackTrace()) + "\n\n\n");
            e.printStackTrace();
        }

    }
    public void addWorkersToFile() {
        try {
            OutputStream workersFile = new FileOutputStream("workersList.txt");
            OutputStreamWriter workersList = new OutputStreamWriter(workersFile, StandardCharsets.UTF_8);
            for (Employer e:employers) {
                writeInfo("["+this.getClass().getSimpleName()+"][“addWorkersToFile()”] Пользователь получил" +
                        "следующие данные:\n" +e.getAllInfo() + "\n\n\n");
                workersList.append(e.getAllInfo()).append("\n");
            }
            workersList.close();
            writeInfo("["+this.getClass().getSimpleName()+"][“addWorkersToFile()”] Пользователь получил" +
                    "следующие данные:\n"+"\u001b[38;5;10mРаботники добавлены в файл!\u001b[38;5;0m\nНажмите 1(для выхода в меню) или 2(для завершения работы программы): ");
            System.out.print("\u001b[38;5;10mРаботники добавлены в файл!\u001b[38;5;0m\nНажмите 1(для выхода в меню) или 2(для завершения работы программы): ");
            Scanner sc = new Scanner(System.in);
            int answer = sc.nextInt();

            if(answer==1) getMenu();
            else System.exit(0);
        } catch (IOException e) {
            writeInfo("[“View”][“addWorkersToFile()”] Произошла ошибка, см.\n" +
                    "описание: "+ Arrays.toString(e.getStackTrace()) + "\n\n\n");
            System.out.println("\u001b[38;5;196mРаботники не были добавлены в файл! Произошла ошибка\u001b[38;5;0m");
            e.printStackTrace();
        }

    }

        public void getMenu()
    {
        System.out.println(helloText + "\n\n" + Menu);
        Scanner console = new Scanner(System.in);
        int Variants = console.nextInt();
        switch(Variants) {
            case 1: addEmployer();
            case 2: generateEmloyer();
            case 3: addTask();
            case 4: getTasks();
            case 5: getWorkersTop();
            case 6: getTopTask();
            case 7: doneTasksToFile();
            case 8: addWorkersToFile();
        }

    }
}
