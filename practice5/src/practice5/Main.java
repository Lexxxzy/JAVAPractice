package practice5;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        ArrayList<Worker> workersList = new ArrayList<>(15);

        for (int i = 0; i < 15; i++) {
            workersList.add(new Worker());
        }

        Stream<Worker> streamWorkers = workersList.stream(); //делаем стрим

        System.out.println("Имя" +
                " \t| Фамилия" +
                "\t | Возраст"  + "(лет)" +
                "| Пол"   +
                " | Имя кота"  +
                " \t| Отдел на работе"  +
                "\t|\t Должность"  +
                " \t\t\t  |Зарплата" +
                "\t|Надбавка" +
                "|Время работы"  + "(лет)"); //красивый вывод в таблицу

        streamWorkers.forEach(Worker::printWorker);//выводим всех работников

        //задания
        System.out.println("\n\n|Работник с максимальной зарплатной|");
        workersList.stream().max(Comparator.
                                comparingInt(Worker::getSalary)).
                                get().
                                printWorker();

        System.out.println("\n\n|Работник с минимальной зарплатной|");
        workersList.stream().min(Comparator.
                                comparingInt(Worker::getSalary)).
                                get().
                                printWorker();

        System.out.println("\n\n|Сортировка по возрасту < 50 и есть кот|");
        workersList.stream().sorted(Comparator.comparing(Worker::getAge)).
                filter(worker -> worker.age < 50).
                filter(worker -> !worker.catName.equals("    ")).
                forEach(Worker::printWorker);

        System.out.println("\n\n|Сортировка по возрасту > 50 и есть кот|");
        workersList.stream().sorted(Comparator.comparing(Worker::getAge)).
                filter(worker -> worker.age > 50).
                filter(worker -> !worker.catName.equals("    ")).
                forEach(Worker::printWorker);


        System.out.println("\n\n|[Задание e.]|");
        Consumer<Worker> x2 = (worker) -> {
            if(worker.moreSalary!=0)
            {
                worker.moreSalary *= 2;
            }
        };
        //находим всех работников менеджоров и если надбавска не ноль то умножаем ее на 2
        List<Worker> workers_List=workersList.stream().
                filter(worker -> worker.work.equals("Managers")).
                peek(x2).
                collect(Collectors.toList());

        for (Worker w:workers_List) {
            if (w.moreSalary!=0)
            {
                w.printWorker();
            }
        }


        System.out.println("\n\n|[Задание f.]|");
        if(workersList.stream().anyMatch(worker -> (worker.moreSalary + worker.salary) * 70 > 100000))
        {
            workersList.stream().
                    filter(worker -> (worker.moreSalary+worker.salary) *70 > 100000). //так как зарплата в долларах - переводим в рубли
                    forEach(worker -> System.out.println(((worker.moreSalary+worker.salary) *70) + "руб." + " наносек"));
        }
        else System.out.println("Компания нищебродов");


        System.out.println("\n\n|[Задание g.]|");
        Map<String, Long> ByWorkGroup = workersList.stream().collect(
                Collectors.groupingBy(Worker::getWork, Collectors.counting()));

        for(Map.Entry<String, Long> item : ByWorkGroup.entrySet())
        {
            System.out.println(item.getKey() + " - " + item.getValue());
        }




/*      a. Вывести работника с максимальной зп среди всех;
        b. Вывести работника с минимальной зп среди всех;
        Кроссплатформенная среда исполнения программного обеспечения. Практика 4
        c. Сортировать работников по возрасту, выбрать только тех, у
        кого он меньше 50. Вывести на экран тех, у кого есть кот;
        d. Тоже самое, что в (c), но убрать из выборки всех, у кого
        меньше 50;
        e. Произвести выборку работников по одному отделу и для
        всех изменить премию умножив её значение (если она
        больше 0) на 2. Выборку собрать в коллекцию List и
        вывести на экран;
        f. Найти работника со значением суммы зарплаты + премии
        больше или равно 100к, вывод функции .get() произвести в
        объект класса Optional<Character>. Если такой объект
        существует, то вывести на экран его и добавить после
        суммы зарплаты+премии текст “наносек”. Если такого
        объекта нет, вывести на экран “Компания нищебродов”);
        g. Посчитать для каждого отдела количество работников.
        Вывести на экран в виде таблички.*/

    }
}
